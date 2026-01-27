package org.sovdlab.haad

import java.time.Instant

// Minimal data classes — synthetic and IP-safe.
data class TorEvent(
    val eventId: String,
    val adsFeature: String,
    val timestamp: Instant,
    val torReason: TorReason,
    val deadlineMs: Int,
    val outcome: TorOutcome
)
enum class TorReason { ODDExit, SystemDegradation, SensorObstruction, FallbackTesting }
enum class TorOutcome { HumanTookOver, ADSPerformedMRM, ADSContinued }

data class LaneChangeEvent(
    val eventId: String,
    val mode: LaneChangeMode,
    val intent: LaneChangeIntent,
    val startTs: Instant,
    val endTs: Instant,
    val fromLane: Int,
    val toLane: Int,
    val partialExcursion: Boolean = false
)
enum class LaneChangeMode { Regular, Emergency, MRM }
enum class LaneChangeIntent { Overtake, AvoidObstacle, AccessCorridor, LaneClosure }

data class MrmEvent(
    val eventId: String,
    val trigger: MrmTrigger,
    val startTs: Instant,
    val endTs: Instant,
    val targetState: MrmTargetState,
    val laneChangeDuringMRM: Boolean
)
enum class MrmTrigger { SevereFailure, NoTORResponse, NetworkLoss }
enum class MrmTargetState { StopInLane, StopOnShoulder }