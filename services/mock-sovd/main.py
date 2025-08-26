# SPDX-License-Identifier: Apache-2.0
from fastapi import FastAPI
from pydantic import BaseModel
from datetime import datetime

app = FastAPI(title="SOVD-Lab Mock SOVD Server")

class Entity(BaseModel):
    id: str
    type: str
    areas: list[str]
    capabilities: list[str]

ENTITIES = [
    Entity(id="vehicle-001", type="synthetic", areas=["powertrain","infotainment"], capabilities=["faults","ident","data"])
]

@app.get("/api/entities")
def list_entities():
    return [e.model_dump() for e in ENTITIES]

@app.get("/api/entities/{eid}/faults")
def list_faults(eid: str):
    return [{"code":"P0001","severity":"low","timestamp": datetime.utcnow().isoformat()+"Z"}]
