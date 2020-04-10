package com.udaan.fc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.udaan.fc.persistence.Match;
import com.udaan.fc.persistence.MatchRepository;
import com.udaan.fc.persistence.Player;
import com.udaan.fc.persistence.PlayerRepository;



@SpringBootApplication
public class MyFantasyCricket {
	public static void main(String[] args) {
		
		SpringApplication.run(MyFantasyCricket.class, args);

	}
	
	
	@Bean
	public CommandLineRunner match(MatchRepository repository) {
	    return (args) -> {
	    				   
	    	repository.save(new Match(0l, "IND", "NZ", true));
	      
	    };
	  }
	
	@Bean
	public CommandLineRunner demo(PlayerRepository repository) {
	    return (args) -> {
	    		
	    	repository.save(new Player(0l, "L Rahul", "WC", 10.0, 0.0, "IND"));
	    	repository.save(new Player(0l, "T Seifert", "WC", 8.5, 0.0, "NZ"));
	    	repository.save(new Player(0l, "R Pant", "WC", 8.5, 0.0, "IND"));
	    	repository.save(new Player(0l, "S Samson", "WC", 8.0, 0.0, "IND"));
	    	repository.save(new Player(0l, "R Sharma", "Batsman", 10.5, 0.0, "IND"));
	    	repository.save(new Player(0l, "V Kohli", "Batsman", 10.5, 0.0, "IND"));
	    	repository.save(new Player(0l, "M Guptill", "Batsman", 10.5, 0.0, "IND"));
	    	repository.save(new Player(0l, "K Williamson", "Batsman", 9.5, 0.0, "NZ"));
	    	repository.save(new Player(0l, "C Munro", "Batsman", 9.0 , 0.0, "NZ"));
	    	repository.save(new Player(0l, "R Taylor", "Batsman", 9.5, 0.0, "NZ"));
	    	repository.save(new Player(0l, "S Iyer", "Batsman",  8.5,0.0, "IND"));
	    	repository.save(new Player(0l, "M Pandey", "Batsman", 8.0, 0.0, "IND"));
	    	repository.save(new Player(0l, "I Sodhi", "Bowler", 8.5,0.0, "IND"));
	    	repository.save(new Player(0l, "Y Chahal", "Bowler",8.5 , 0.0, "IND"));
	    	repository.save(new Player(0l, "J Bumrah", "Bowler", 9.0, 0.0, "IND"));
	    	repository.save(new Player(0l, "B Tickner", "Bowler", 8.0,0.0, "NZ"));
	    	repository.save(new Player(0l, "T Southee", "Bowler",9.0 , 0.0, "NZ"));
	    	repository.save(new Player(0l, "H Bennett", "Bowler", 8.0, 0.0, "NZ"));
	    };
	  }
}
