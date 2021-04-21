package com.kovunov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Team {
	@Id
	@GeneratedValue(generator = "Team")
	private Long id;

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
	private List<Player> playerList;

	@ManyToOne
	@JoinColumn(name = "id_league")
	@JsonIgnore
	private League league;

	private String name;

	private LocalDateTime timeOfNextGame;

	public Team(String name) {
		this.name = name;
	}

}
