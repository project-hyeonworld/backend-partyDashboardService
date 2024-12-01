package io.partydashboardservice.api.partyDashboard.event.kafka;

import io.partydashboardservice.api.partyDashboard.event.PartyEvent;
import io.partydashboardservice.api.partyDashboard.event.PartyEventPublisher;
import io.partydashboardservice.common.event.kafka.KafkaMessageReceiver;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
@RequiredArgsConstructor
public class PartyKafkaMessageReceiver implements KafkaMessageReceiver<PartyEvent> {

    private final PartyKafkaMessageConsumer partyKafkaMessageConsumer;
    private final PartyEventPublisher partyDashboardEventPublisher;

    @Override
    public void execute() {
        KafkaConsumer consumer = null;
        try {
            while (true) {
                consumer = partyKafkaMessageConsumer.getConsumer();
                ConsumerRecords<String, Long> records = consumer.poll(Duration.ofMillis(4000));
                for (ConsumerRecord<String, Long> record : records) {
                    handleEvent(partyKafkaMessageConsumer.consume(record));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (consumer != null) {
                consumer.close();
            }
        }
    }

    @Override
    public void handleEvent(PartyEvent event) {
        partyDashboardEventPublisher.execute(event);
    }
}
