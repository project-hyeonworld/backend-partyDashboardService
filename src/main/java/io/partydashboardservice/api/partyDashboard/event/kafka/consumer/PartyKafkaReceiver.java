package io.partydashboardservice.api.partyDashboard.event.kafka.consumer;

import io.partydashboardservice.api.partyDashboard.event.PartyEvent;
import io.partydashboardservice.api.partyDashboard.event.PartyEventPublisher;
import io.partydashboardservice.api.partyDashboard.event.kafka.PartyBeginKafkaEvent;
import io.partydashboardservice.common.event.kafka.consumer.KafkaConsumerStrategy;
import io.partydashboardservice.common.event.kafka.consumer.KafkaReceiver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
@RequiredArgsConstructor
public class PartyKafkaReceiver implements KafkaReceiver<PartyEvent> {

    private final PartyKafkaConsumerManager partyKafkaConsumerManager;
    private final PartyEventPublisher eventPublisher;


    @Override
    public void execute() {
        KafkaConsumerStrategy consumer = partyKafkaConsumerManager.getConsumer(PartyBeginKafkaEvent.class);
        while (true) {
            handleEvents(consumer.receive());
        }
    }

    @Override
    public void handleEvent(PartyEvent event) {
        if (event instanceof PartyBeginKafkaEvent) {
            eventPublisher.execute(event);
            return;
        }
    }
}
