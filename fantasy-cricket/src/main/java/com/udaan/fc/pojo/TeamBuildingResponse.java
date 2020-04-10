package com.udaan.fc.pojo;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class TeamBuildingResponse {

	 private boolean success;
	 
	 private Map<String, String> errorMap = new HashMap<String, String>();
}
