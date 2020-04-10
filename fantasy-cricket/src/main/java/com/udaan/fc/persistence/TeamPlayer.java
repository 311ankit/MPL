package com.udaan.fc.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class TeamPlayer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long teamId;
	
	private Long playerId;
	
	protected TeamPlayer() {}
	
	public TeamPlayer(Long teamId, Long playerId) {
		this.teamId = teamId;
		this.playerId = playerId;
	}
}
