package io.partydashboardservice.api.partyDashboard.controller;

import static io.partydashboardservice.api.partyDashboard.controller.dto.res.PartyDashboardResponse.*;

import io.partydashboardservice.api.partyDashboard.application.PartyDashboardSseFacade;
import io.partydashboardservice.api.partyDashboard.controller.dto.req.PartyDashboardRequest;
import io.partydashboardservice.api.partyDashboard.controller.dto.req.PartyGamePatchRequest;
import io.partydashboardservice.api.partyDashboard.controller.dto.req.PartyGameStagePatchRequest;
import io.partydashboardservice.api.partyDashboard.controller.dto.res.PartyDashboardResponse;
import io.partydashboardservice.api.partyDashboard.domain.PartyDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/parties")
public class PartyDashboardController {
  private final PartyDashboardSseFacade partyDashboardSseFacade;
  private final PartyDashboardService partyDashboardService;

  @GetMapping("/{partyId}/dashboard/game")
  public ResponseEntity<Long> retrieveCurrentGame(
      @PathVariable long partyId){
    return ResponseEntity.ok(from(partyDashboardService.findByPartyId(partyId)).currentGameId());
  }

  @GetMapping("/{partyId}/dashboard/stage")
  public ResponseEntity<Byte> retrieveCurrentStage(
      @PathVariable long partyId){
    return ResponseEntity.ok(from(partyDashboardService.findByPartyId(partyId)).currentGameStage());
  }

  @PatchMapping("/{partyId}/dashboard")
  public ResponseEntity<PartyDashboardResponse> changeDashboard(
      @PathVariable long partyId,
      @RequestBody PartyDashboardRequest request){
    return ResponseEntity.ok(from(partyDashboardService.changeDashboard(request.toCommand(partyId))));
  }

  @PatchMapping("/{partyId}/dashboard/game")
  public ResponseEntity<Long> changeCurrentGame(
          @PathVariable long partyId,
          @RequestBody PartyGamePatchRequest request) {
    return ResponseEntity.ok(from(partyDashboardService.changeDashboard(request.toCommand(partyId))).currentGameId());
  }

  @PatchMapping("/{partyId}/dashboard/gameStage")
  public ResponseEntity<Byte> changeCurrentGameStage(
          @PathVariable long partyId,
          @RequestBody PartyGameStagePatchRequest request) {
    return ResponseEntity.ok(from(partyDashboardSseFacade.changeGameStage(request.toCommand(partyId))).currentGameStage());
  }
}
