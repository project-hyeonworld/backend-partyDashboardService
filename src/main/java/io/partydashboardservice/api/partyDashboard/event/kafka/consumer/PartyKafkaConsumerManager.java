package io.partydashboardservice.api.partyDashboard.event.kafka.consumer;

import io.partydashboardservice.api.partyDashboard.event.PartyEvent;
import io.partydashboardservice.common.event.kafka.consumer.CustomKafkaConsumerFactory;
import io.partydashboardservice.common.event.kafka.consumer.KafkaConsumerManager;
import io.partydashboardservice.common.event.kafka.consumer.KafkaConsumerStrategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
@RequiredArgsConstructor
public class PartyKafkaConsumerManager implements KafkaConsumerManager<PartyEvent> {

    private final CustomKafkaConsumerFactory customKafkaConsumerFactory;

    @Override
    public KafkaConsumerStrategy getConsumer(Class eventClass) {
        return customKafkaConsumerFactory.getConsumer(eventClass);
    }
}
