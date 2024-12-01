package io.partydashboardservice.common.event.kafka;

import io.partydashboardservice.api.partyDashboard.event.kafka.PartyKafkaMessageReceiver;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 12. 1.
 */
@Component
@RequiredArgsConstructor
public class KafkaMessageRecieverRunner implements CommandLineRunner {

    private final PartyKafkaMessageReceiver partyKafkaMessageReceiver;

    @Override
    public void run(String... args) throws Exception {
        partyKafkaMessageReceiver.execute();
    }
}
