package io.partydashboardservice.api.partyDashboard.application;

import io.partydashboardservice.api.partyDashboard.domain.PartyDashboardService;
import io.partydashboardservice.api.partyDashboard.domain.dto.in.PartyGameStagePatchCommand;
import io.partydashboardservice.api.partyDashboard.domain.dto.out.PartyDashboardInfo;
import io.partydashboardservice.api.partyDashboard.event.PartyDashboardEventPublisher;
import io.partydashboardservice.api.partyDashboard.event.PartyGameStagePatchEvent;
import io.partydashboardservice.api.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 18.
 */
@Facade
@RequiredArgsConstructor
public class PartyDashboardSseFacade {
    private final PartyDashboardService partyDashboardService;
    private final PartyDashboardEventPublisher eventPublisher;

    @Transactional
    public PartyDashboardInfo changeGameStage(PartyGameStagePatchCommand command) {
        PartyDashboardInfo partyDashboardInfo = partyDashboardService.changeDashboard(command);
        eventPublisher.execute(PartyGameStagePatchEvent.from(command));
        return partyDashboardInfo;
    }
}
