package com.udaan.fc.persistence;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerPerformanceRepository extends JpaRepository<PlayerPerformance, Long>{

	public PlayerPerformance findByMatchIdAndPlayerId(Long matchId, Long playerId);
}
