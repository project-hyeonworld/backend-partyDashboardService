package io.partydashboardservice.api.partyDashboard.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 17.
 */
public record PartyGameStagePatchCommand (
    long partyId,
    byte gameStage
){

}
