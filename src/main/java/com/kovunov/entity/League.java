package com.kovunov.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class League {
	@Id
	@GeneratedValue(generator = "League")
	private Long id;

	@OneToMany(mappedBy = "league", fetch = FetchType.EAGER)
	private List<Team> teamList;



}
