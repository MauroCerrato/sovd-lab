package org.sovdlab.haad

data class ShortTermReport(
    val reportId: String,
    val occurrenceType: String,
    val timestamp: String,
    val briefDescription: String,
    val dssadRef: String
)

class IsmrAssembler {
    fun buildShortTerm(tor: TorEvent): ShortTermReport =
        ShortTermReport(
            reportId = "STR-${tor.eventId.take(8)}",
            occurrenceType = if (tor.outcome == TorOutcome.ADSPerformedMRM) "CriticalOccurrence" else "TOR",
            timestamp = tor.timestamp.toString(),
            briefDescription = "TOR ${tor.torReason} → ${tor.outcome}",
            dssadRef = "DSSAD-${tor.eventId.takeLast(6)}"
        )
}