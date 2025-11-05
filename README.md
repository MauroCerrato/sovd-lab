# SOVD-Lab

**Mission:** Make SOVD practical, open, and reusable—without leaking IP.

This lab provides small, runnable diagnostics scenarios that align with:
-  Eclipse **OpenSOVD** (Apache-2.0)
- Emerging **ISO 17978** standard 

All content is **IP-safe**:
- Code: Apache-2.0
- Docs: CC BY 4.0
- Synthetic data: CC0

---

## 🚀 Quick Start

### Option 1: REST Collections
Open `clients/rest-collections/sample.http` in VS Code (REST Client extension) and send requests.

### Option 2: CLI
```bash
python clients/cli/sovd_cli.py --host http://localhost:8080
```

### Option 3: Web Client
Open `clients/web/index.html` and invoke sample endpoints.


## Option4: 🐳 Multi-Node Diagnostics Simulation with Docker Compose

Run the full stack with Docker Compose:
```bash
git clone https://github.com/MauroCerrato/sovd-lab.git
cd sovd-lab
docker compose up --build
```

---

## 🧪 Architecture Diagram
The current diagnostics ecosystem using SOVD and OBD-II protocols is as of today realized in three services, 2 servers, 1 gateway and one client.

```code
+------------------+       +------------------+       +------------------+       +------------------+
|  mock-sovd       | <---> |  gateway         | <---> |  obd2-sovd-sim   | <---> |  go-capabilities |
+------------------+       +------------------+       +------------------+       +------------------+
        ↑                        ↑                          ↑                          ↑
        |                        |                          |                          |
    Docker Compose orchestrates all services with shared volumes and network, across Python and Go services
```

---

### 🔧 Services Overview

| Service           | Port | Description                                        |
|-------------------|------|----------------------------------------------------|
| mock-sovd         | 8080 | SOVD mock server for synthetic vehicle data        |
| gateway           | 8081 | API gateway routing anonymized partner APIs        |
| obd2-sovd-sim     | 8083 | Simulated OBD-II server with SOVD-like endpoints   |
| go-capabilities   | 8085 | Overall vehicles healthcheck and YML capabilities  |


### 🔁 Features

- ✅ Retry logic (2 attempts, 1.5s delay) for transient backend failures
- ✅ /health and /healthz endpoints for monitoring
- ✅ Aggregated SOVD capabilities description
- ✅ Error handling for unreachable or failing upstream services

### 📡 Example Requests
```bash
# SOVD entity list
curl http://localhost:8081/partner/quick-check

# OBD-II VIN
curl http://localhost:8083/vehicle-001/api/vin

# OBD-II DTCs
curl http://localhost:8083/vehicle-001/api/dtc

# Go service health
curl -s http://localhost:8085/healthz
curl -s http://localhost:8085/data/ident/vin
```

### 🔧 Error Scenarios

| Scenario                        | HTTP Status | Description                         |
|--------------------------------|-------------|--------------------------------------|
| SOVD API unreachable           | 502         | Network or connection error          |
| SOVD API returns error         | 4xx/5xx     | Propagated from upstream             |
| Unexpected internal exception  | 500         | Caught and logged                    |

How to simulate unreachable SOVD API:
```bash
docker stop sovd-lab_mock-sovd_1
curl http://localhost:8081/partner/quick-check
```

### 📡 Example expected error Response, in case the mock-sovd is still not available

```json
{
  "detail": "Gateway error: ConnectTimeout"
}
```

To recover, restart the backend container:
```bash
docker restart sovd-lab_mock-sovd_1
```

📡 Gateway Health Check

```bash
request:
curl http://localhost:8081/health
```

Response example:
```json
{
  "status": "ok",
  "service": "gateway",
  "version": "0.2"
}
```

---

## 📂 Folder structure
- `clients/` REST collections, CLI, and tiny web client
- `examples/` anonymized entities and workflows, includes SOVD to OBD2 server and client
- `specs/` OpenAPI overlays and JSON Schemas (non-normative)
- `tools/` mapping prototypes and validators
- `docs/` intros, quickstart, IP safety, standardization links, acronyms
- `data/` synthetic datasets only
- `services/` API gateway and mock-sovd server, NEW added go-capabilities

---

## ✅ KPIs
- Multi-client coverage (REST, CLI, web)
- Multi-language solution (Python, Kotlin, Rust, Go)
- No custom SOVD server/client beyond OpenSOVD
- Harmonization demos with anonymized content
- 100% SPDX headers; 0 license CI failures
- Time-to-first-response < 48h; ≥5 good-first-issues open

---

### 🔮 Next Goals

- Add retry/backoff improvements
- OpenTelemetry instrumentation
- Monitoring-first demo (time-series backend)
- More error scenarios

