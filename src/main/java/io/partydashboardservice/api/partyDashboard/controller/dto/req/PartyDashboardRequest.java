package io.partydashboardservice.api.partyDashboard.controller.dto.req;

import io.partydashboardservice.api.partyDashboard.domain.dto.in.DashboardPatchCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record PartyDashboardRequest (
    Long gameId,
    Byte gameStage
) {
  public DashboardPatchCommand toCommand(long partyId) {
    return new DashboardPatchCommand(partyId, gameId, gameStage);
  }
}