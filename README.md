# SOVD-Lab

**Mission:** Make SOVD practical, open, and reusable—without leaking IP.

This lab provides small, runnable diagnostics scenarios that align with **Eclipse OpenSOVD (Apache-2.0)** and the emerging **ISO 17978** standard. Code is Apache‑2.0; docs are CC BY 4.0; synthetic data is CC0.

## Quick Start
- Open `clients/rest-collections/sample.http` in VS Code (REST Client extension) and send the requests.
- Or run the CLI: `python clients/cli/sovd_cli.py --host http://localhost:8080`.
- Or open `clients/web/index.html` and invoke sample endpoints.
- or try the Multi-Node simulation

## 🐳 Multi-Node Diagnostics Simulation with Docker Compose

The current diagnostics ecosystem using SOVD and OBD-II protocols is as of today realized in three services, 2 servers, 1 gateway and one client.

## 🧪 Architecture Diagram
```code
+------------------+       +------------------+       +------------------+       +------------------+
|  mock-sovd       | <---> |  gateway         | <---> |  obd2-sovd-sim   | <---> |  go-capabilities |
+------------------+       +------------------+       +------------------+       +------------------+
        ↑                        ↑                          ↑                          ↑
        |                        |                          |                          |
    Docker Compose orchestrates all services with shared volumes and network, across Python and Go services
````

### 🔧 Services Overview

| Service           | Port | Description                                        |
|-------------------|------|----------------------------------------------------|
| mock-sovd         | 8080 | SOVD mock server for synthetic vehicle data        |
| gateway           | 8081 | API gateway routing anonymized partner APIs        |
| obd2-sovd-sim     | 8083 | Simulated OBD-II server with SOVD-like endpoints   |
| go-capabilities   | 8085 | Overall vehicles healthcheck and YML capabilities  |





## 🔁 Retry Logic and Health Check

The `gateway` now includes:

- ✅ Retry logic (2 attempts, 1.5s delay) for transient backend failures
- ✅ `/health` endpoint for monitoring of the gateway

The overall solution now has a new aggregated SOVD capabilities description and aggregated health endpoint, next steps -> add retry/backoff and OpenTelemetry instrumentation
- ✅ `/healthz` endpoint for monitoring of the overall capabilities


### 🔧 Error Scenarios

| Scenario                        | HTTP Status | Description                         |
|--------------------------------|-------------|--------------------------------------|
| SOVD API unreachable           | 502         | Network or connection error          |
| SOVD API returns error         | 4xx/5xx     | Propagated from upstream             |
| Unexpected internal exception  | 500         | Caught and logged                    |

### 📡 Example Error Response, in case the mock-sovd is still not available

```json
{
  "detail": "Gateway error: ConnectTimeout"
}
```

To recover, restart the backend container:

docker restart sovd-lab_mock-sovd_1

📡 Gateway Health Check

request:
curl http://localhost:8081/health

Response example:
```json
{
  "status": "ok",
  "service": "gateway",
  "version": "0.2"
}
```


## Folder structure
- `clients/` REST collections, CLI, and tiny web client
- `examples/` anonymized entities and workflows, includes SOVD to OBD2 server and client
- `specs/` OpenAPI overlays and JSON Schemas (non-normative)
- `tools/` mapping prototypes and validators
- `docs/` intros, quickstart, IP safety, standardization links, acronyms
- `data/` synthetic datasets only
- `services/` API gateway and mock-sovd server, NEW added go-capabilities

## KPIs
- Multi-client coverage (REST, CLI, web)
- Multi-language solution (Python, Kotlin, Rust, Go)
- No custom SOVD server/client beyond OpenSOVD
- Harmonization demos with anonymized content
- 100% SPDX headers; 0 license CI failures
- Time-to-first-response < 48h; ≥5 good-first-issues open

## Licenses
- Code: Apache-2.0
- Docs: CC BY 4.0
- Data: CC0

## 🚀 How to Run with Docker Compose
```bash
git clone https://github.com/MauroCerrato/sovd-lab.git
cd sovd-lab
docker compose up --build

### 📡 Example Requests
# SOVD entity list
curl http://localhost:8081/partner/quick-check

# OBD-II VIN
curl http://localhost:8083/vehicle-001/api/vin

# OBD-II DTCs
curl http://localhost:8083/vehicle-001/api/dtc

# new Go services
curl -s http://localhost:8085/healthz
curl -s http://localhost:8085/data/ident/vin


🧪 How to Test with error scenarios
# Simulate unreachable SOVD API
docker stop sovd-lab_mock-sovd_1

# Call the gateway
curl http://localhost:8081/partner/quick-check

# the expected response is an (well handled) Gateway error indication

```
