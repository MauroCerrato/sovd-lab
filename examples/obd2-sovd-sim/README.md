# OBD-II SOVD Simulation

This example simulates a diagnostic server and client using SOVD-style REST APIs mapped to UDS concepts.

## 🔧 Endpoints

- `GET /vehicle-001/api/vin` → returns synthetic VIN
- `GET /vehicle-001/api/dtc` → returns synthetic DTCs
- `GET /vehicle-001/api/software-version` → returns ECU software version

## 🚀 Run Locally

```bash
docker build -t obd2-sovd-sim .
docker run -p 8080:8080 obd2-sovd-sim
