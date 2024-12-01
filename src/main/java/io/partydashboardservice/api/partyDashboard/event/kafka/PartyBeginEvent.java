package io.partydashboardservice.api.partyDashboard.event.kafka;

import io.partydashboardservice.api.partyDashboard.event.PartyEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public record PartyBeginEvent(long partyId) implements PartyEvent {

    public static PartyEvent from(long partyId) {
        return new PartyBeginEvent(partyId);
    }
}