package io.partydashboardservice.api.partyDashboard.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import io.partydashboardservice.api.partyDashboard.event.PartyDashboardEvent;
import io.partydashboardservice.api.partyDashboard.event.PartyGameStagePatchEvent;
//import com.toyproject.hyeonworld.api.sse.domain.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 18.
 */
@Component
@RequiredArgsConstructor
public class PartyDashboardEventListenerImpl implements PartyDashboardEventListener {
    //private final SseService sseService;


    @Override
    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleEvent(PartyDashboardEvent event) {
        if (event instanceof PartyGameStagePatchEvent) {
            handleGameStagePatchEvent((PartyGameStagePatchEvent) event);
        }
    }

    //TODO: kafka consumer in partyDashboard & sseService
    private void handleGameStagePatchEvent(PartyGameStagePatchEvent event) {
        //sseService.sendCurrentGameStage(event.partyId(), event.gameStage());
    }
}
