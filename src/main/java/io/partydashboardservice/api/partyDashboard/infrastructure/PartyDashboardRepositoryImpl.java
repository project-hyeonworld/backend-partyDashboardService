package io.partydashboardservice.api.partyDashboard.infrastructure;

import io.partydashboardservice.api.partyDashboard.infrastructure.entity.PartyDashboard;
import io.partydashboardservice.api.partyDashboard.infrastructure.jpa.PartyDashboardJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Repository
@RequiredArgsConstructor
public class PartyDashboardRepositoryImpl implements PartyDashboardRepository {
  private final PartyDashboardJpaRepository partyDashboardJpaRepository;

  @Override
  public PartyDashboard save(PartyDashboard partyDashboard){
    return partyDashboardJpaRepository.save(partyDashboard);
  }

  @Override
  public PartyDashboard saveAndFlush(PartyDashboard partyDashboard) {
    return partyDashboardJpaRepository.saveAndFlush(partyDashboard);
  }

  @Override
  public Optional<PartyDashboard> findById(long partyId) {
    return partyDashboardJpaRepository.findById(partyId);
  }
}
