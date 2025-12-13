# 🛡️ SOVD-Lab: Kotlin SOTIF Sentinel

A specialized microservice for **Safety of the Intended Functionality (SOTIF)** monitoring. 
It simulates the "Physics vs. Model" validation required by UN R157 (ALKS) and ISO 21448.

## 🎯 The "Hydraulic Swap" Scenario
This service demonstrates a critical failure mode:
1.  **Command:** ECU activates **Front Left** brake.
2.  **Fault:** Hydraulic lines are crossed (mechanic error). **Front Right** brake activates.
3.  **Detection:** IMU detects Positive Yaw (Right) instead of Negative Yaw (Left).
4.  **Result:** A `SOTIF_CRITICAL` event is generated for the DSSAD.

## 🐳 How to Run (Play with Docker)

### 1. Build the Docker Image
Navigate to the root `sovd-lab` directory or this folder.

```bash
# Create the Dockerfile first (see below)
docker build -t sovd-lab-kotlin-events .

# Run the containeraized event
docker run -d -p 8082:8082 --name sotif-sentinel sovd-lab-kotlin-events
```

# Trigger the Simulation
## Case A: Nominal Braking (Everything OK)

```bash

curl -X POST http://localhost:8082/simulate/brake
```

Response: {"status":"OK", ...}

## Case B: Inject "Hydraulic Swap" Fault (The Nightmare Scenario)

```bash

curl -X POST "http://localhost:8082/simulate/brake?fault=hydraulic_swap"
```

Response (The SOTIF Event):

```JSON

{
  "eventId": "...",
  "violationDetails": {
    "severity": "SOTIF_CRITICAL",
    "commandedActuator": "BRAKE_ACTUATOR_FL",
    "detectedEffect": "YAW_RATE_POSITIVE",
    "description": "CRITICAL: Yaw rate contradicts braking command..."
  }
}
```

