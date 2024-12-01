package io.partydashboardservice.api.partyDashboard.infrastructure.jpa;

import io.partydashboardservice.api.partyDashboard.infrastructure.entity.PartyDashboard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyDashboardJpaRepository extends JpaRepository<PartyDashboard, Long> {
}
