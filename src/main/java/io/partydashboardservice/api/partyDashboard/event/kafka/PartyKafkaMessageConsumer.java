package io.partydashboardservice.api.partyDashboard.event.kafka;

import io.partydashboardservice.api.partyDashboard.event.PartyEvent;
import io.partydashboardservice.common.event.kafka.KafkaMessageConsumer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
public class PartyKafkaMessageConsumer implements KafkaMessageConsumer<PartyEvent, String, Long> {

    private final String PARTY_TOPIC_BEGIN;
    private KafkaConsumer<String, Long> kafkaConsumer;

    public PartyKafkaMessageConsumer(@Value("${spring.kafka.broker.url}") String brokerUrl,
            @Value("${spring.kafka.topic.party.begin}") String partyTopicBegin, @Value("${spring.application.name}") String groupId) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Collections.singletonList(partyTopicBegin));
        PARTY_TOPIC_BEGIN = partyTopicBegin;
    }

    @Override
    public KafkaConsumer<String, Long> getConsumer() {
        return kafkaConsumer;
    }

    @Override
    public PartyEvent consume(ConsumerRecord consumerRecord) {
        if (consumerRecord.topic().equals(PARTY_TOPIC_BEGIN)) {
            return PartyBeginEvent.from((Long)consumerRecord.value());
        }
        return null;
    }
}
