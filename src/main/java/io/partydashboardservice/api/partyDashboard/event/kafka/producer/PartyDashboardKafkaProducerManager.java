package io.partydashboardservice.api.partyDashboard.event.kafka.producer;

import io.partydashboardservice.api.partyDashboard.event.PartyDashboardEvent;
import io.partydashboardservice.common.event.kafka.producer.CustomKafkaProducerFactory;
import io.partydashboardservice.common.event.kafka.producer.KafkaProducerManager;
import io.partydashboardservice.common.event.kafka.producer.KafkaProducerStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 3.
 */
@Component
@RequiredArgsConstructor
public class PartyDashboardKafkaProducerManager implements KafkaProducerManager<PartyDashboardEvent, KafkaProducerStrategy> {

    private final CustomKafkaProducerFactory customKafkaProducerFactory;

    @Override
    public KafkaProducerStrategy getProducer(PartyDashboardEvent event) {
        return customKafkaProducerFactory.getStrategy(event);
    }
}
