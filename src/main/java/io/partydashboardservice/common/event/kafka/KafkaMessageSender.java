package io.partydashboardservice.common.event.kafka;

import io.partydashboardservice.common.event.EventPublisher;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaMessageSender<E> extends EventPublisher<E> {
}
