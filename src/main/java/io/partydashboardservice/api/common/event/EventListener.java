package io.partydashboardservice.api.common.event;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public interface EventListener<T> {
    void handleEvent(T event);
}
