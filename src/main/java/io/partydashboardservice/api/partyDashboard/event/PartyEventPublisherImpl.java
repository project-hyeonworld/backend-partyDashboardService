package io.partydashboardservice.api.partyDashboard.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Component
@RequiredArgsConstructor
public class PartyEventPublisherImpl implements PartyEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void execute(PartyEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
