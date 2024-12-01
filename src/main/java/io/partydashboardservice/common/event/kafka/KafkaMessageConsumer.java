package io.partydashboardservice.common.event.kafka;

import io.partydashboardservice.common.event.CustomEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaMessageConsumer<E extends CustomEvent, K, V> {

    KafkaConsumer<K, V> getConsumer();
    E consume(ConsumerRecord consumerRecord);
}