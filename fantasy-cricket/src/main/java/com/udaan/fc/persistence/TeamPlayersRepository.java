package com.udaan.fc.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPlayersRepository extends JpaRepository<TeamPlayer, Long> {

	public List<TeamPlayer> findByTeamId(Long teamId);
}
