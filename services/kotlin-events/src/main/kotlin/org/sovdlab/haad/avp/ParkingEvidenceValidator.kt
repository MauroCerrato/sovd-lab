package org.sovdlab.haad.avp

class ParkingEvidenceValidator {

    // Simple geometry plausibility: ensure slot fits a C‑segment vehicle footprint
    fun checkSlotGeometry(length: Double, width: Double): Boolean =
        length >= 4.5 && width >= 2.0

    // Clearance rule (synthetic): ensure ≥ 0.25 m lateral clearance
    fun checkClearance(width: Double): Boolean =
        width - 1.8 >= 0.25

    // Obstacle reaction (synthetic threshold)
    fun checkObstacleStop(distanceM: Double): Boolean =
        distanceM <= 1.2

}
