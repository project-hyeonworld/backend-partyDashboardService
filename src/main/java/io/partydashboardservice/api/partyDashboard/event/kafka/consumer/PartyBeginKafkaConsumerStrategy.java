package io.partydashboardservice.api.partyDashboard.event.kafka.consumer;

import io.partydashboardservice.api.partyDashboard.event.kafka.PartyBeginKafkaEvent;
import io.partydashboardservice.common.event.kafka.consumer.KafkaConsumerStrategy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import io.partydashboardservice.common.annotation.Strategy;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 2.
 */
@Strategy(3)
public class PartyBeginKafkaConsumerStrategy implements
        KafkaConsumerStrategy<PartyBeginKafkaEvent, String, Long> {

    private Duration timeout;
    private KafkaConsumer<String, Long> kafkaConsumer;

    public PartyBeginKafkaConsumerStrategy(@Value("${spring.kafka.broker.url}")String brokerUrl, @Value("${spring.kafka.topic.party.begin}")String partyBeginTopic, @Value("${spring.application.name}") String groupId) {
        timeout = Duration.ofMillis(1000);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(Collections.singletonList(partyBeginTopic));
    }

    @Override
    public Class<PartyBeginKafkaEvent> getEventClass() {
        return PartyBeginKafkaEvent.class;
    }

    @Override
    public KafkaConsumer<String, Long> getConsumner() {
        return kafkaConsumer;
    }

    @Override
    public Duration getTimeout() {
        return timeout;
    }

    @Override
    public List<PartyBeginKafkaEvent> receive() {
        List<PartyBeginKafkaEvent> events = new ArrayList<>();
        ConsumerRecords<String, Long> records = consume();
        for (ConsumerRecord<String, Long> record : records) {
            events.add(PartyBeginKafkaEvent.from(record.value()));
        }
        return events;
    }

    @Override
    public void close() {

    }
}
