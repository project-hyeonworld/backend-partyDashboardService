package io.partydashboardservice.api.partyDashboard.controller.dto.res;

import io.partydashboardservice.api.partyDashboard.domain.dto.out.PartyDashboardInfo;
import io.partydashboardservice.common.mapper.ObjectrMapper;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record PartyDashboardResponse (
      long currentGameId,
      byte currentGameStage
  ) {

  public static PartyDashboardResponse from(PartyDashboardInfo partyDashboardInfo) {
    return ObjectrMapper.convert(partyDashboardInfo, PartyDashboardResponse.class);
  }
}
