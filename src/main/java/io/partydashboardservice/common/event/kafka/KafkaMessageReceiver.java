package io.partydashboardservice.common.event.kafka;

import io.partydashboardservice.common.event.EventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
public interface KafkaMessageReceiver<T> extends EventListener<T> {

    void execute();
}
