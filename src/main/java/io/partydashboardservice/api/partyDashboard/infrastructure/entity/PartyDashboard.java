package io.partydashboardservice.api.partyDashboard.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "party_dashboard")
public class PartyDashboard {

  @Id
  @Column(name = "party_id")
  private Long partyId;

  @Column(name = "current_game_id")
  private Long currentGameId;

  @Column(name = "current_game_stage")
  private Byte currentGameStage;

  public static PartyDashboardBuilder defaultBuilder(){
    return PartyDashboard.builder();
  }
}
