\# DRAFT



This document will describe how to validate the haad event-schemas JSON files locally using a VSCode setup.



Validating HAAD Compliance JSON (Local and CI)

\#License: Apache-2.0. This guide validates synthetic JSON evidence against JSON Schemas.



\#Folder layout



* Schemas: docs/haad/compliance-mapping/event-schemas/\*.schema.json
* Evidence: docs/haad/compliance-mapping/evidence/\*.json



\#Option A: Node + ajv-cli (recommended)



1. Install Node.js 20+ (or use GitHub Codespaces / PlayWithDocker image with Node).

2\. Install CLI:

npm install -g ajv-cli@5 ajv-formats

3\. Validate all evidence files:

ajv validate -r docs/haad/compliance-mapping/event-schemas/.schema.json -d docs/haad/compliance-mapping/evidence/.json --spec=draft2020



\#Option B: Python + jsonschema



1. Ensure Python 3.11+ is available.

2\. Install:

pip install jsonschema

3\. Validate (per file):

python -m jsonschema -i docs/haad/compliance-mapping/evidence/2025-12-06\_toc\_demo.json docs/haad/compliance-mapping/event-schemas/controlTransitionRequest.schema.json



\#PlayWithDocker tip



You can run validation inside a container with Node:

docker run --rm -v "$PWD":/work -w /work node:20-alpine sh -lc "npm i -g ajv-cli@5 ajv-formats \&\& ajv validate -r docs/haad/compliance-mapping/event-schemas/.schema.json -d docs/haad/compliance-mapping/evidence/.json --spec=draft2020"

