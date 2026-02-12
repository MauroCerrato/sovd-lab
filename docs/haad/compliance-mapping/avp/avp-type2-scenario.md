# AVP Type-2 Scenario (Synthetic, ADS-GTR-Aligned)

## 1. Scenario Description
The AVP Type-2 system performs **driverless parking** in a **specific, approved parking facility** with:
- Infrastructure sensors (vision/LiDAR/beacons),
- Digital map of lanes, obstacles, markings,
- Defined drop-off / pick-up zones,
- Max speed ≤ 10 km/h (synthetic),
- Mixed or exclusive traffic depending on facility operator.

## 2. ODD Boundaries (Synthetic)
- Location: “Facility-ID-XYZ”
- Allowed zones: Level-0 parking deck, lanes A1–A8
- Speed limit: ≤ 10 km/h
- Weather constraints: Dry/wet, no ice accumulation
- Lighting: ≥ 50 lux
- Communications: ≥ 90% link availability with AVP server

## 3. Safety Behaviors
- Automatic obstacle stop (<1.2 m)
- Dead-end turnaround logic
- Weak-signal fallback → controlled stop and operator alert
- DDT fallback = MRM → controlled stop inside lane

## 4. DSSAD Expectations (Synthetic)
Store timestamped flags:
- `AVP_SessionStart`
- `AVP_PathFollowed`
- `AVP_ObstacleStop`
- `AVP_Abort`
- `AVP_SessionComplete`

## 5. Evidence Set (Synthetic)
- Path consistency RMSE < 0.25 m
- Obstacle reaction latency < 250 ms
- Communication health logs
- AVP-server decision trace (synthetic)
- Session summary for ISMR