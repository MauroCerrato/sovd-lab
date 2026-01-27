EU Action Pack: ALKS (UN-R 157) — Essentials with EU Implementing Focus

\#License and scope



\##License: Apache-2.0. This document contains paraphrased, non-excerpted notes. No paywalled content is quoted.

\##Purpose: Operational notes for VKO HAAD, centered on ALKS essentials and current EU implementing focus



\#Overview



# EU ALKS Essentials (UN-R 157) — VKO Guide (IP-safe)

## What the regulation expects (paraphrased)
- ADS performs entire DDT within ODD; HMI clarity; fallback via TOR/MRM/EM.
- DSSAD records key flags (ADS active, lane changes, TOR/MRM/EM).
- Cybersecurity (UN-R 155) and Software Updates/SUMS (UN-R 156) apply over lifecycle.

## What we evidence in sovd-lab (synthetic)
- JSON Schemas for TOR/MRM/LaneChange + DSSAD minimal set.
- Kotlin sentinel validates timing, durations, basic consistency.
- ISMR short-term + periodic report examples.

## Where to plug into VKO/SMS
- KPIs: hazard closure, update validation, anomaly latency, TOR efficacy.
- Credibility matrix for virtual↔track↔real-world correlation.

> Sources (UNECE, public):
> - R157 ALKS overview & annex structure, DSSAD references. [3](https://unece.org/sites/default/files/2023-12/R157e.pdf)  
> - 01 series: 130 km/h, lane change, DSSAD lane-change logging. [7](https://unece.org/sustainable-development/press/un-regulation-extends-automated-driving-130-kmh-certain-conditions)[8](https://unece.org/sites/default/files/2025-06/R157r1e.pdf)  
> - R155 CSMS, audit-based. [5](https://unece.org/transport/documents/2021/03/standards/un-regulation-no-155-cyber-security-and-cyber-security)  
> - R156 SUMS, RXSWIN, audit-based. [10](https://eur-lex.europa.eu/eli/reg/2021/388/oj/eng)



ALKS is a Level 3 function intended for specific operational design domains (ODD).



\##Key pillars:

* ODD: declared limits and assumptions that govern when ALKS may operate.
* Transition of Control (ToC): clear and timely takeover requests when conditions require the human driver to resume control.
* Minimum Risk Maneuver (MRM): safe fallback behavior if takeover is not timely or conditions degrade.
* Extensions (where applicable): speed range and lane-change functionality subject to additional conditions/approvals.
* Evidence: records sufficient to demonstrate correct behavior for approval and in-service purposes (DSSAD/SMS placeholders).



\#Key requirements (paraphrased bullets; verify specifics via your tracker)



\##ODD declaration

* Provide a clear ODD description and boundary conditions.
* Maintain supporting evidence for validity and updates over time.



\##ToC

* Provide timely, clear requests to resume control, with escalation if ignored.
* Indicate HMI channels in use and timing characteristics.



\##MRM

* Define triggers, behavior, and signaling for a safe fallback.
* Capture outcomes and transitions back to normal operation where applicable.



\##Extensions (speed and lane-change)

* Where permitted, ensure conditions and validations for extended speeds and lane-change maneuvers are documented and met.



\##Records and documentation

* Keep records showing the above behaviors occur as intended; align with DSSAD/SMS placeholder structures.



\#Compliance mapping

This matrix reflects my current understanding of each requirement context





Requirement	Homologation	Type Approval	SMS	DSSAD	In-Service	Evidence intent	Verification notes

ODD declaration	Context	Evidence of declared ODD	Governance	ODD state flag	Operational checks	ODD doc + versioning	Confirm current EU implementing checkpoints for ODD documentation.

ToC timing and HMI	N/A	Timing, cues, escalation	Ops procedures	ToC record	Event review	ToC JSON with timing fields	Verify timing thresholds and escalation expectations.

MRM behavior	N/A	Triggers + behavior	Safety mgmt	MRM record	Incident review	MRM JSON with triggers/outcome	Confirm signaling and stop behavior conditions.

Speed range extension	N/A	Speed conditions	Change mgmt	Mode flags	Field observation	Config/evidence linking	Verify speed-related implementing texts.

Lane-change function	N/A	Conditions + limits	Ops training	Maneuver record	Observation	Maneuver JSON or log	Verify implementing scope and constraints.

Records/logging	N/A	Evidence sufficiency	Retention rules	Record types	Reporting cadence	Event exports + checks	Confirm retention and review cadence expectations.



\#Gaps and verification list



* Identify which EU implementing items in your tracker specify: ODD boundaries, ToC timing windows, MRM triggers/behavior, and speed/lane-change constraints.
* Confirm national overlays (e.g., DE) requiring additional checks.
* Define retention period and privacy posture for synthetic records used in demos.
* Decide owner(s) for periodic “freshness” checks and change logs.



\#Deliverables and owners



* Master table updates (ALKS rows) — Owner: VKO.
* Evidence JSON schemas and examples (ToC, MRM) — Owner: sovd-lab Contributors.
* EU compliance mapping table in Action Pack — Owner: VKO; review in HAAD meetings sync.
* Open questions list for K-VKO/Legal — Owner: VKO.





\#Milestones

* Week 1: Essentials draft + 5–7 master-table entries + ToC/MRM schema stubs and demo JSON.
* Week 3: Expand compliance mapping after GSR crosswalk.
* Week 6+: Integrate with DSSAD/SMS checklist and in-service evidence cadence.





\#Overview

What ALKS is (L3), where it applies, and EU focus on implementing provisions (scope, speed, lane-change, ToC/MRM, documentation/evidence). Paraphrase-only; no excerpts.



\# Key regulatory requirements (paraphrased bullets)

\## ODD declaration and driver availability monitoring at L3.

\## ToC: clear, timely takeover requests; escalation to MRM when needed.

\## MRM: safe fallback behavior; clear HMI signaling.

\## Extensions (where applicable): speed range, lane changes subject to conditions.

\## Documentation/evidence: records sufficient to demonstrate correct ALKS behavior in approvals and in-service contexts.



\# Compliance mapping (matrix)

\## For each requirement, note Homologation/Type Approval/SMS/DSSAD/In-Service touchpoints and evidence intent (no quotes).



\# Gaps/Risks

\## Clarify specific EU implementing act checkpoints (verify via your tracker).

\## National overlay (e.g., DE StVO) interactions to be confirmed with Legal.

\## Evidence sufficiency and recency (freshness checks).



\# Deliverables \& owners

\## Master table rows (you); weekly VKO sync readiness (you, K-VKO).

\## Evidence JSON examples (sovd-lab).



\# Timeline/milestones

\## Week 1: essentials draft + 5–7 master-table rows.

\## Week 3: Compliance table expanded after GSR crosswalk.



\# Stakeholder references

\## Cluster 17 page and AI Context recap for governance/process.

\## Link to your Excel tracker (internal).

