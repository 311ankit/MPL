package com.udaan.fc.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;




@Entity
@Data
@AllArgsConstructor
public class Player {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	 Long id;
	
	private String name;
	
	private String role;
	
	private double creditScore;
	
	private double overAllPerformanceScore;
	
	private String team;
	
	protected Player() {}

}