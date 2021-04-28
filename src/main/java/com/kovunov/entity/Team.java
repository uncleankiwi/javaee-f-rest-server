package com.kovunov.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
public class Team {
	@Id
	@GeneratedValue(generator = "TEAM_ID_GEN")
	private Long id;

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
	private List<Player> playerList;

	@ManyToOne
	@JoinColumn(name = "id_league")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //@JsonIgnore only for getLeague()
	private League league;

	private String name;

	private Date timeOfNextGame;

	public Team(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Team{" +
				"id=" + id +
				", league=" + league +
				", name='" + name + '\'' +
				'}';
	}
}