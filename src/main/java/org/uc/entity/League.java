package org.uc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "League.findAll", query = "SELECT l FROM League l")
public class League {
	@Id
	@GeneratedValue(generator = "LEAGUE_ID_GEN")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "league", fetch = FetchType.EAGER)
	private List<Team> teamList;

	public League(Long id){
		this.id = id;
	}

	@Override
	public String toString() {
		return "League{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
