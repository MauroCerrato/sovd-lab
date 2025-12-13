package com.sovdlab.events.domain.models

import java.time.Instant

/**
 * Represents a Safety Violation where the physical response of the vehicle
 * contradicts the commanded actuation.
 * Context: SOTIF / ISO 21448 - Unintended Behavior of the Intended Function.
 */
data class ActuationEffectMismatch(
    val eventId: String,
    val timestamp: String, // String for easier JSON serialization in prototype
    val vehicleState: VehicleStateContext,
    val violationDetails: MismatchDetails
)

enum class SeverityLevel {
    QM, ASIL_A, ASIL_B, ASIL_C, ASIL_D, SOTIF_CRITICAL
}

data class VehicleStateContext(
    val speedKph: Double,
    val driveMode: String,
    // Vital for reconstructing the incident in VKO/HAAD compliance checks
    val automatedDrivingActive: Boolean 
)

data class MismatchDetails(
    val severity: SeverityLevel,
    val commandedActuator: String,     // e.g., "BRAKE_ACTUATOR_FL"
    val detectedEffect: String,        // e.g., "YAW_RATE_POSITIVE" (Right Turn tendency)
    val expectedEffect: String,        // e.g., "YAW_RATE_NEGATIVE" (Left Turn tendency)
    val confidenceScore: Double,       // e.g., 0.98 (High certainty of mismatch)
    val description: String            // "Hydraulic Cross-Connection Suspected"
)
