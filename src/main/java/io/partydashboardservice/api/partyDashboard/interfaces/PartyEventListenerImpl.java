package io.partydashboardservice.api.partyDashboard.interfaces;

import io.partydashboardservice.api.partyDashboard.domain.PartyDashboardService;
import io.partydashboardservice.api.partyDashboard.event.PartyEvent;
import io.partydashboardservice.api.partyDashboard.event.kafka.PartyBeginKafkaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
@RequiredArgsConstructor
public class PartyEventListenerImpl implements PartyEventListener {

    private final PartyDashboardService partyDashboardService;

    @Override
    @EventListener
    public void handleEvent(PartyEvent event) {
        if (event instanceof PartyBeginKafkaEvent) {
            handleEventBy((PartyBeginKafkaEvent) event);
        }
    }

    private void handleEventBy(PartyBeginKafkaEvent event) {
        partyDashboardService.createPartyDashboard(event.partyId());
    }
}
