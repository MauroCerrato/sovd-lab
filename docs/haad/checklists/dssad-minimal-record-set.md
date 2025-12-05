DSSAD Minimal Record Set (ALKS-focused, synthetic)
#License: Apache-2.0. Paraphrased, no quotations. Verify with Legal/SMS before operational use.

#Intent

- Provide a minimal, synthetic structure to reason about what to record for ALKS ToC/MRM events so that approval/in‑service questions can be answered without proprietary data.


#Core fields (suggestions welcome!)

- Identity: event_id, session_id, timestamp_utc, vehicle_id (synthetic), sw_version, odometer_km.
- Classification: event_type (ToC/MRM), trigger/reason, outcome.
- Timing: time_to_handover_s (ToC), time_to_safe_stop_s (MRM if applicable).
- HMI: channels used (visual/auditory/haptic), hmi_signal_active (MRM).
- ODD/context: ODD-related flag(s) at event time (if represented synthetically).
- Compliance: regulation_refs (e.g., UN-R157, EU-TA), dssad.record_type, sms.category.
- Provenance: generator_service, run_env, commit_sha.

#Governance (paraphrased)

- Retention and privacy: keep synthetic; no VIN or personal data; document retention intent.
- Integrity: basic validation (schema), artifact hashing optional.
- Review cadence: monthly freshness check; link to master table entries and Action Pack updates.
