package io.partydashboardservice.api.partyDashboard.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record DashboardPatchCommand(
    Long partyId,
    Long gameId,
    Byte gameStage
) {
}
