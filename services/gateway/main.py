```python
# SPDX-License-Identifier: Apache-2.0
import os
import httpx
from fastapi import FastAPI, HTTPException
from fastapi.responses import JSONResponse
from fastapi.middleware.cors import CORSMiddleware
import asyncio

TARGET_BASE = os.environ.get("TARGET_BASE", "http://localhost:8080")

app = FastAPI(title="SOVD-Lab Gateway (anonymized partner APIs → SOVD shape)")

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/partner/quick-check")
async def partner_quick_check():
    retries = 2
    delay = 1.5  # seconds
    for attempt in range(retries + 1):
        try:
            async with httpx.AsyncClient(timeout=3.0) as client:
                r = await client.get(f"{TARGET_BASE}/api/entities")
                r.raise_for_status()
                return {"entities": r.json()}
        except httpx.RequestError as e:
            if attempt < retries:
                await asyncio.sleep(delay)
            else:
                raise HTTPException(status_code=502, detail=f"Gateway error: {str(e)}")
        except httpx.HTTPStatusError as e:
            raise HTTPException(status_code=e.response.status_code, detail=f"Upstream error: {e.response.text}")
        except Exception as e:
            raise HTTPException(status_code=500, detail=f"Unexpected error: {str(e)}")

@app.get("/health")
def health_check():
    return {"status": "ok", "service": "gateway", "version": "0.2"}