package io.partydashboardservice.api.partyDashboard.event;

import io.partydashboardservice.api.partyDashboard.domain.dto.in.PartyGameStagePatchCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 18.
 */
public record PartyGameStagePatchEvent (
        long partyId,
    byte gameStage
) implements PartyDashboardEvent {

    public static PartyGameStagePatchEvent from(PartyGameStagePatchCommand command) {
        return new PartyGameStagePatchEvent(command.partyId(), command.gameStage());
    }
}
