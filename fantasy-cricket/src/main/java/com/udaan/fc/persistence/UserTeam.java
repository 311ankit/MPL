package com.udaan.fc.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
public class UserTeam {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long teamId;
	
	@Column(unique = true)
	private String teamName;
	
	private Long matchId;
	
	private Long userId;
	
	protected UserTeam() {}
	
}
