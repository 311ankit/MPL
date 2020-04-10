package com.udaan.fc.pojo;

import java.util.List;

import com.udaan.fc.persistence.Player;

import lombok.Data;

@Data
public class TeamResponse {
	
	private Long teamId;
	
	private String teamName;
	
	private Long matchId;
	
	private List<Player> players;
}
