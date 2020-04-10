package com.udaan.fc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udaan.fc.persistence.Match;
import com.udaan.fc.persistence.MatchRepository;
import com.udaan.fc.persistence.Player;
import com.udaan.fc.persistence.PlayerRepository;
import com.udaan.fc.pojo.TeamBuildingResponse;
import com.udaan.fc.pojo.UserTeamRequest;

@Service
public class FantasyCricketRuleEngine {

	private static final String BATSMAN = "BATSMAN";
	private static final String BOWLER = "BOWLER";
	private static final String WICKETKEEPER = "WC";
	
	@Autowired
	PlayerRepository playerRepository;
	
	@Autowired
	MatchRepository matchRepository;
	
	private boolean isMatchActive(Long matchId) {
		return matchRepository.findById(matchId).get().isActive();
	}
	
	public TeamBuildingResponse checkUserTeam(UserTeamRequest userTeamRequest) {
		
		TeamBuildingResponse response = new TeamBuildingResponse();
		Map<String, String> errorMap = new HashMap<String, String>();
		
		if(!isMatchActive(userTeamRequest.getMatchId())) {
			response.setSuccess(false);
			errorMap.put("MatchError", "This match is not active now.");
			response.setErrorMap(errorMap);
		}
		
		int playerCount = userTeamRequest.getPlayers().size();
		
		if(playerCount != 11)
		{
			response.setSuccess(false);
			errorMap.put("PlayerCountError", "Please select 11 players only.");
			response.setErrorMap(errorMap);
			return response;
		}
		
		int batsmanCount = 0;
		int bowlerCount = 0;
		boolean isWicketKeeperPreset = false;
		Double userCreditScore  = 0.0;
		
		for(Long playerId: userTeamRequest.getPlayers()) {
			
			Player player = playerRepository.findById(playerId).get();
			
			
			if(player.getRole().equalsIgnoreCase(BATSMAN))
				 batsmanCount++;
			else if(player.getRole().equalsIgnoreCase(BOWLER))
				 bowlerCount++;
			else if(player.getRole().equalsIgnoreCase(WICKETKEEPER))
				 isWicketKeeperPreset = true;
		
			userCreditScore = userCreditScore + player.getCreditScore();
		}
		
		boolean batsmanCheck = false;
		boolean bowlerCheck = false;
		boolean creditCheck = false;
		
		if(batsmanCount < 3) {
			batsmanCheck = true;
			errorMap.put("BatsmanCountError", "choose atleast 3 Batsman, you have choosen only :" + batsmanCount + " batsman");
		}
		if(bowlerCount < 3) {
			bowlerCheck = true;
			errorMap.put("BowlerCountError", "choose atleast 3 bowler, you have choosen only :" + bowlerCount + " bowler");
		}
		if(!isWicketKeeperPreset)
			errorMap.put("WCError", "choose atleast a one wicketKeeper");
		
		if(userCreditScore > 100){
			creditCheck = true;
			errorMap.put("CreditError","you have cross the 100 credit limit.");
		}
		
		if(batsmanCheck || bowlerCheck || !isWicketKeeperPreset || creditCheck) 
			response.setSuccess(false);
		else
			response.setSuccess(true);
		
		response.setErrorMap(errorMap);
		
		return response;
	}
}
