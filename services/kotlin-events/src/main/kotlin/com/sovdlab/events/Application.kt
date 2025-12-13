package com.sovdlab.events

import com.sovdlab.events.domain.models.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.Instant
import java.util.UUID

fun main() {
    embeddedServer(Netty, port = 8082, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson()
    }

    routing {
        get("/") {
            call.respondText("SOVD-Lab SOTIF Sentinel is Active. Use POST /simulate/brake to test.")
        }

        // Endpoint to simulate a braking command
        // Query param: ?fault=hydraulic_swap (Injects the error)
        post("/simulate/brake") {
            val injectFault = call.request.queryParameters["fault"] == "hydraulic_swap"
            
            // 1. Trigger: ECU requests brake on Front Left (FL)
            val commandedWheel = "BRAKE_ACTUATOR_FL"
            val expectedYaw = "YAW_RATE_NEGATIVE" // Counter-clockwise (pulls left)

            // 2. Observation (Physics Simulation)
            val observedYaw = if (injectFault) {
                // THE ERROR: Hydraulic lines crossed, FR activates instead of FL
                "YAW_RATE_POSITIVE" // Clockwise (pulls right)
            } else {
                expectedYaw
            }

            // 3. Safety Check
            if (observedYaw != expectedYaw) {
                // SOTIF Violation Detected!
                val event = ActuationEffectMismatch(
                    eventId = UUID.randomUUID().toString(),
                    timestamp = Instant.now().toString(),
                    vehicleState = VehicleStateContext(
                        speedKph = 85.0,
                        driveMode = "ALKS_L3_MOTORWAY",
                        automatedDrivingActive = true
                    ),
                    violationDetails = MismatchDetails(
                        severity = SeverityLevel.SOTIF_CRITICAL,
                        commandedActuator = commandedWheel,
                        detectedEffect = observedYaw,
                        expectedEffect = expectedYaw,
                        confidenceScore = 0.99,
                        description = "CRITICAL: Yaw rate contradicts braking command. Suspected Hydraulic Cross-Connection (FL <-> FR)."
                    )
                )
                
                // Return the structured event (Compliance Evidence)
                call.respond(event)
            } else {
                call.respond(mapOf("status" to "OK", "message" to "Braking nominal. Physics align with command."))
            }
        }
    }
}
