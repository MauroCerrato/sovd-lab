package org.sovdlab.haad.avp

class RcpBoundaryChecker {

    // R79‑RCP inspired: max 6 m from vehicle contour
    fun validateProximity(driverProximityM: Double): Boolean =
        driverProximityM in 0.0..6.0

    // Synthetic timing rule: session timeout
    fun isSessionValid(startMs: Long, nowMs: Long): Boolean =
        (nowMs - startMs) <= 300_000 // 5 minutes
}