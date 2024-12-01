package io.partydashboardservice.api.partyDashboard.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 18.
 */
@Component
@RequiredArgsConstructor
public class PartyDashboardEventPublisherImpl implements PartyDashboardEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void execute(PartyDashboardEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
