package com.udaan.fc.pojo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTeamRequest {

	Long userId;
	
	Long matchId;
	
	String teamName;
	
	List<Long> players;

}
