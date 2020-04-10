package com.udaan.fc.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udaan.fc.persistence.Player;
import com.udaan.fc.persistence.PlayerPerformance;
import com.udaan.fc.persistence.PlayerPerformanceRepository;
import com.udaan.fc.persistence.PlayerRepository;
import com.udaan.fc.persistence.TeamPlayer;
import com.udaan.fc.persistence.TeamPlayersRepository;
import com.udaan.fc.persistence.UserTeam;
import com.udaan.fc.persistence.UserTeamRepository;
import com.udaan.fc.pojo.TeamBuildingResponse;
import com.udaan.fc.pojo.TeamResponse;
import com.udaan.fc.pojo.UserTeamRequest;
import com.udaan.fc.service.FantasyCricketService;

@Service
public class FantasyCricketServiceImpl implements FantasyCricketService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	UserTeamRepository userTeamRepository;

	@Autowired
	TeamPlayersRepository teamPlayersRepository;

	@Autowired
	FantasyCricketRuleEngine ruleEngine;

	@Autowired
	PlayerPerformanceRepository playerPerformanceRepository;

	@Override
	public boolean addPlayer(Player player) {
		Player savedPlayer = playerRepository.save(player);
		return savedPlayer != null ? true : false;
	}

	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@Override
	public List<TeamResponse> getUserTeam(Long userId) {

		List<UserTeam> userTeamList = userTeamRepository.findByUserId(userId);

		List<TeamResponse> userTeamsResponse = new ArrayList<TeamResponse>();

		for (UserTeam userTeam : userTeamList) {

			TeamResponse teamResponse = new TeamResponse();
			teamResponse.setTeamId(userTeam.getTeamId());
			teamResponse.setTeamName(userTeam.getTeamName());
			teamResponse.setMatchId(userTeam.getTeamId());

			List<TeamPlayer> teamPlayers = teamPlayersRepository.findByTeamId(userTeam.getTeamId());

			List<Player> teamPlayersList = new ArrayList<Player>();

			for (TeamPlayer teamPlayer : teamPlayers)
				teamPlayersList.add(playerRepository.findById(teamPlayer.getPlayerId()).get());

			teamResponse.setPlayers(teamPlayersList);

			userTeamsResponse.add(teamResponse);
		}

		return userTeamsResponse;
	}
	

	public TeamBuildingResponse createUserTeam(UserTeamRequest userTeamReqeust) {

		Map<String, String> errorMap = new HashMap<String, String>();
		TeamBuildingResponse response = ruleEngine.checkUserTeam(userTeamReqeust);
		if (!response.isSuccess())
			return response;

		Long userId = userTeamReqeust.getUserId();
		Long matchId = userTeamReqeust.getMatchId();
		UserTeam userTeam = new UserTeam(0l, userTeamReqeust.getTeamName(), matchId, userId);

		try {
			if (!isTeamAlreadyPresent(userTeamReqeust.getPlayers(), userId, matchId)) {
				userTeam = userTeamRepository.save(userTeam);
				for (Long playerId : userTeamReqeust.getPlayers())
					teamPlayersRepository.save(new TeamPlayer(userTeam.getTeamId(), playerId));
			}
		} catch (Exception e) {
			response.setSuccess(false);
			errorMap.put("Exception", e.getMessage().toString());
			response.setErrorMap(errorMap);
			return response;
		}

		return response;
	}

	private boolean isTeamAlreadyPresent(List<Long> players, Long userId, Long matchId) throws Exception {
		List<TeamResponse> userTeamResponse = getUserTeam(userId);
		for (TeamResponse team : userTeamResponse) {

			List<Long> teamPlayers = team.getPlayers().stream().map(Player::getId).collect(Collectors.toList());
			Collections.sort(teamPlayers);
			Collections.sort(players);
			if (team.getMatchId() == matchId && teamPlayers.equals(players))
				throw new Exception("team already present.");
		}
		return false;
	}

	@Override
	public boolean updatePlayerPerformance(List<PlayerPerformance> playerPerformanceList) {
		for (PlayerPerformance pp : playerPerformanceList) {
			playerPerformanceRepository.save(pp);
		}
		return true;
	}

	@Override
	public Double getUserTeamPerformance(Long userId, Long matchId, Long teamId) {

		Double userTeamPerformance = 0.0;
		List<TeamPlayer> teamPlayerList = teamPlayersRepository.findByTeamId(teamId);

		for (TeamPlayer tp : teamPlayerList) {
			PlayerPerformance playerPerformance = playerPerformanceRepository.findByMatchIdAndPlayerId(matchId,
					tp.getPlayerId());
			if (playerPerformance != null)
				userTeamPerformance += playerPerformance.getPerformanceScore();
		}

		return userTeamPerformance;
	}

}
