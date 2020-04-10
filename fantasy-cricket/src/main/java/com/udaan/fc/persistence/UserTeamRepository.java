package com.udaan.fc.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {

	public List<UserTeam> findByUserId(Long userId);
}
