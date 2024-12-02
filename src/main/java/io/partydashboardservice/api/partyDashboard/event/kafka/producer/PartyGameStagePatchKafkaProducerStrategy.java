package io.partydashboardservice.api.partyDashboard.event.kafka.producer;

import io.partydashboardservice.api.partyDashboard.event.PartyGameStagePatchEvent;
import io.partydashboardservice.common.event.kafka.producer.KafkaProducerStrategy;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 3.
 */
@Component
public class PartyGameStagePatchKafkaProducerStrategy implements KafkaProducerStrategy<PartyGameStagePatchEvent, Long, Integer> {

    private final String PARTY_DASHBOARD_STAGE_PATCH_TOPIC;
    private KafkaProducer<Long, Integer> kafkaProducer;

    public PartyGameStagePatchKafkaProducerStrategy(@Value("${spring.kafka.broker.url}") String brokerUrl, @Value("${spring.kafka.topic.party-dashboard.game-stage.change}") String partyDashboardStagePatchTopic) {
        PARTY_DASHBOARD_STAGE_PATCH_TOPIC = partyDashboardStagePatchTopic;
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        kafkaProducer = new KafkaProducer<>(props);
    }


    @Override
    public String getTopic() {
        return PARTY_DASHBOARD_STAGE_PATCH_TOPIC;
    }

    @Override
    public KafkaProducer<Long, Integer> getProducer() {
        return kafkaProducer;
    }

    @Override
    public Class<PartyGameStagePatchEvent> getEventClass() {
        return PartyGameStagePatchEvent.class;
    }

    public ProducerRecord<Long, Integer> produce(PartyGameStagePatchEvent event) {
        return new ProducerRecord<>(getTopic(), event.partyId(), (int)event.gameStage());
    }

    @Override
    public void close() {

    }
}
