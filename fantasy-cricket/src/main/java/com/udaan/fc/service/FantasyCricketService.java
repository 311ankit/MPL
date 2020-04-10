package com.udaan.fc.service;

import java.util.List;

import com.udaan.fc.persistence.Player;
import com.udaan.fc.persistence.PlayerPerformance;
import com.udaan.fc.pojo.TeamBuildingResponse;
import com.udaan.fc.pojo.TeamResponse;
import com.udaan.fc.pojo.UserTeamRequest;

public interface FantasyCricketService {

		public boolean addPlayer(Player player);
		
		public List<Player> getAllPlayers();
		
		public List<TeamResponse> getUserTeam(Long userId);
		
		public TeamBuildingResponse createUserTeam(UserTeamRequest playerList);
		
		public boolean updatePlayerPerformance(List<PlayerPerformance> playerPerformanceList);
		
		public Double getUserTeamPerformance(Long userId, Long matchId, Long teamId);
		
}
