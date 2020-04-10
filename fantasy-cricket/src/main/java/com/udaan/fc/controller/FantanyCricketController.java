package com.udaan.fc.controller;

import org.springframework.web.bind.annotation.RestController;

import com.udaan.fc.persistence.Player;
import com.udaan.fc.persistence.PlayerPerformance;
import com.udaan.fc.pojo.TeamBuildingResponse;
import com.udaan.fc.pojo.TeamResponse;
import com.udaan.fc.pojo.UserTeamRequest;
import com.udaan.fc.service.FantasyCricketService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FantanyCricketController {

	@Autowired
	FantasyCricketService fantasyCricketService;
	
	@GetMapping("/getAllPlayers")
	public List<Player> getAllPlayers() {
		return fantasyCricketService.getAllPlayers();
	}
	
	@PostMapping("/addPlayer")
	public boolean addPlayer(@RequestBody Player player) {
		return fantasyCricketService.addPlayer(player);
	}
	
	@GetMapping("/getUserTeams")
	public List<TeamResponse> getMyTeam(@RequestParam Long userId){
		return fantasyCricketService.getUserTeam(userId);
	}
	
	@PostMapping("/buildUserTeam")
	public TeamBuildingResponse buildUserTeam(@RequestBody UserTeamRequest userTeamRequest) {
		 return fantasyCricketService.createUserTeam(userTeamRequest);
	}
	
	@PostMapping("/updatePlayerPerformance")
	public boolean updatePlayerPerformance(@RequestBody List<PlayerPerformance> PlayerPerformanceList) {
		return fantasyCricketService.updatePlayerPerformance(PlayerPerformanceList);
	}
	
	@GetMapping("/userTeamPerformance")
	public double userTeamPerformance(@RequestParam Long matchId, @RequestParam Long userId, @RequestParam Long teamId){
		return fantasyCricketService.getUserTeamPerformance(userId, matchId, teamId);
	}
}
