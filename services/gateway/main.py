# SPDX-License-Identifier: Apache-2.0
import os
import httpx
from fastapi import FastAPI

TARGET_BASE = os.environ.get("TARGET_BASE", "http://localhost:8080")
app = FastAPI(title="SOVD-Lab Gateway (anonymized partner APIs → SOVD shape)")

@app.get("/partner/quick-check")
async def partner_quick_check():
    async with httpx.AsyncClient(timeout=3.0) as client:
        r = await client.get(f"{TARGET_BASE}/api/entities")
        r.raise_for_status()
        data = r.json()
        return {"entities": data}
