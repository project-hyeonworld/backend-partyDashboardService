package io.partydashboardservice.api.partyDashboard.domain.dto.out;

import static io.partydashboardservice.api.partyDashboard.infrastructure.entity.PartyDashboard.defaultBuilder;

import io.partydashboardservice.api.partyDashboard.domain.dto.in.DashboardPatchCommand;
import io.partydashboardservice.api.partyDashboard.domain.dto.in.PartyGamePatchCommand;
import io.partydashboardservice.api.partyDashboard.infrastructure.entity.PartyDashboard;
import io.partydashboardservice.api.common.mapper.ObjectrMapper;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Getter
public class PartyDashboardInfo {
  private Long partyId;
  private Long currentGameId;
  private Byte currentGameStage;
  public static PartyDashboardInfo from (PartyDashboard partyDashboard) {
    return ObjectrMapper.convert(partyDashboard, PartyDashboardInfo.class);
  }

  public static PartyDashboard toEntity (long partyId){
    return defaultBuilder()
            .partyId(partyId)
            .currentGameId((long) -1)
            .currentGameStage((byte) -1)
            .build();
  }

  public PartyDashboard toEntity (){
    return defaultBuilder()
            .partyId(partyId)
            .currentGameId(currentGameId)
            .currentGameStage(currentGameStage)
            .build();
  }

  public static PartyDashboard toEntity (DashboardPatchCommand command){
    return defaultBuilder()
            .partyId(command.partyId())
            .currentGameId(command.gameId())
            .currentGameStage(command.gameStage())
            .build();
  }

  public  PartyDashboard toEntity(PartyGamePatchCommand command) {
    return defaultBuilder()
            .partyId(command.partyId())
            .currentGameId(command.gameId())
            .currentGameStage(this.currentGameStage)
            .build();
  }

  public void changeCurrentGame(long gameId) {
    currentGameId = gameId;
  }

  public void changeCurrentGameStage(byte gameStage) {
    currentGameStage = gameStage;
  }
}
