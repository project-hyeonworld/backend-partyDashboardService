package io.partydashboardservice.api.partyDashboard.event.kafka.producer;

import io.partydashboardservice.api.partyDashboard.event.PartyGameStagePatchEvent;
import io.partydashboardservice.common.event.kafka.producer.KafkaProducerStrategy;
import io.partydashboardservice.common.event.kafka.producer.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 3.
 */
@Component
@RequiredArgsConstructor
public class PartyGameStagePatchKafkaSender implements KafkaSender<PartyGameStagePatchEvent> {

    private final PartyDashboardKafkaProducerManager partyDashboardKafkaProducerManager;

    @Override
    public void execute(PartyGameStagePatchEvent event) {
        KafkaProducerStrategy kafkaProducer = partyDashboardKafkaProducerManager.getProducer(event);
        kafkaProducer.send(event);
    }
}
