package io.partydashboardservice.api.partyDashboard.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 17.
 */
public record PartyGamePatchCommand (
        long partyId,
        long gameId
){

}
