# SOVD-Lab

**Mission:** Make SOVD practical, open, and reusable—without leaking IP.

This lab provides small, runnable diagnostics scenarios that align with **Eclipse OpenSOVD (Apache-2.0)** and the emerging **ISO 17978** standard. Code is Apache‑2.0; docs are CC BY 4.0; synthetic data is CC0.

## Quick Start
- Open `clients/rest-collections/sample.http` in VS Code (REST Client extension) and send the requests.
- Or run the CLI: `python clients/cli/sovd_cli.py --host http://localhost:8080`.
- Or open `clients/web/index.html` and invoke sample endpoints.

## Structure
- `clients/` REST collections, CLI, and tiny web client
- `examples/` anonymized entities and workflows
- `specs/` OpenAPI overlays and JSON Schemas (non-normative)
- `tools/` mapping prototypes and validators
- `docs/` intros, quickstart, IP safety, standardization links, acronyms
- `data/` synthetic datasets only

## KPIs
- Multi-client coverage (REST, CLI, web)
- No custom SOVD server/client beyond OpenSOVD
- Harmonization demos with anonymized content
- 100% SPDX headers; 0 license CI failures
- Time-to-first-response < 48h; ≥5 good-first-issues open

## Licenses
- Code: Apache-2.0
- Docs: CC BY 4.0
- Data: CC0

## Run with Docker Compose
```bash
docker compose up --build
curl http://localhost:8080/api/entities              # mock SOVD
curl http://localhost:8081/partner/quick-check       # anonymized partner APIs → SOVD mapping
```
