package io.partydashboardservice.api.partyDashboard.controller.dto.req;

import io.partydashboardservice.api.partyDashboard.domain.dto.in.PartyGamePatchCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 17.
 */
public record PartyGamePatchRequest (
        long gameId
){

    public PartyGamePatchCommand toCommand(long partyId) {
        return new PartyGamePatchCommand(partyId, gameId);
    }
}
