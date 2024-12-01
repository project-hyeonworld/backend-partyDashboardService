package io.partydashboardservice.api.partyDashboard.domain;

import io.partydashboardservice.api.partyDashboard.domain.dto.in.DashboardPatchCommand;
import io.partydashboardservice.api.partyDashboard.domain.dto.in.PartyGamePatchCommand;
import io.partydashboardservice.api.partyDashboard.domain.dto.in.PartyGameStagePatchCommand;
import io.partydashboardservice.api.partyDashboard.domain.dto.out.PartyDashboardInfo;
import io.partydashboardservice.api.partyDashboard.infrastructure.PartyDashboardRepository;
import io.partydashboardservice.api.common.exception.ServerException;
import io.partydashboardservice.api.common.exception.dto.ServerResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PartyDashboardService {
  private final PartyDashboardRepository partyDashboardRepository;


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public PartyDashboardInfo createPartyDashboard(long partyId) {
    try {
      return PartyDashboardInfo.from(partyDashboardRepository.save(PartyDashboardInfo.toEntity(partyId)));
    } catch (Exception e) {
      log.error("Failed to save PartyDashboard for partyId: {}", partyId, e);
      throw e;
    }
  }

  @Caching(
      put = @CachePut(cacheNames = "partyDashboardInfo", key = "#command.partyId")
  )
  @Transactional
  public PartyDashboardInfo changeDashboard(DashboardPatchCommand command) {
    return PartyDashboardInfo.from(partyDashboardRepository.save(PartyDashboardInfo.toEntity(command)));
  }

  @Caching(
          put = @CachePut(cacheNames = "partyDashboardInfo", key = "#command.partyId")
  )
  @Transactional
  public PartyDashboardInfo changeDashboard(PartyGamePatchCommand command) {
    PartyDashboardInfo partyDashboardInfo = findByPartyId(command.partyId());
    partyDashboardInfo.changeCurrentGame(command.gameId());
    return PartyDashboardInfo.from(partyDashboardRepository.save(partyDashboardInfo.toEntity()));
  }

  @Caching(
          put = @CachePut(cacheNames = "partyDashboardInfo", key = "#command.partyId")
  )
  @Transactional
  public PartyDashboardInfo changeDashboard(PartyGameStagePatchCommand command) {
    PartyDashboardInfo partyDashboardInfo = findByPartyId(command.partyId());
    partyDashboardInfo.changeCurrentGameStage(command.gameStage());
    return PartyDashboardInfo.from(partyDashboardRepository.save(partyDashboardInfo.toEntity()));
  }

  @Cacheable(cacheNames = "partyDashboardInfo", key = "#partyId", cacheManager = "caffeineCacheManager")
  public PartyDashboardInfo findByPartyId(long partyId) {
    return PartyDashboardInfo.from(partyDashboardRepository.findById(partyId)
        .orElseThrow(()->new ServerException(ServerResponseCode.PARTY_DASHBOARD_NOT_FOUND)));
  }
}
