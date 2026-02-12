package org.sovdlab.haad.avp

import java.time.Instant

data class ParkingSelectionEvent(
    val selectionId: String,
    val mode: String,
    val lengthM: Double,
    val widthM: Double,
    val angleDeg: Double?,
    val ts: Instant
)

data class ParkingAbortEvent(
    val abortId: String,
    val trigger: String,
    val ts: Instant
)

data class RemoteParkingSession(
    val sessionId: String,
    val driverProximityM: Double,
    val startTs: Instant,
    val endTs: Instant?,
    val completionState: String
)