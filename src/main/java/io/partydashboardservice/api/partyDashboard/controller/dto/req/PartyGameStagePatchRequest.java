package io.partydashboardservice.api.partyDashboard.controller.dto.req;

import io.partydashboardservice.api.partyDashboard.domain.dto.in.PartyGameStagePatchCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 17.
 */
public record PartyGameStagePatchRequest (
        byte gameStage
){

    public PartyGameStagePatchCommand toCommand(long partyId) {
        return new PartyGameStagePatchCommand(partyId, gameStage);
    }
}
