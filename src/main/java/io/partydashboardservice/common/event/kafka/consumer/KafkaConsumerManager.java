package io.partydashboardservice.common.event.kafka.consumer;

import io.partydashboardservice.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaConsumerManager<E extends CustomEvent> {

    KafkaConsumerStrategy getConsumer(Class<E> eventClass);
}