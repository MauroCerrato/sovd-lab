package org.sovdlab.haad

class EvidenceValidator {
    fun checkTorDeadline(event: TorEvent): Boolean = event.deadlineMs in 1000..10000
    fun checkLaneChangeDuration(event: LaneChangeEvent): Boolean =
        event.endTs.isAfter(event.startTs) &&
        java.time.Duration.between(event.startTs, event.endTs).seconds in 1..10

    fun checkMrmCompleteness(event: MrmEvent): Boolean =
        event.endTs.isAfter(event.startTs) && event.targetState in MrmTargetState.values()
}