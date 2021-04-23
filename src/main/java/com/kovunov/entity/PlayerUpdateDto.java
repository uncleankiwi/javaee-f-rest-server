package com.kovunov.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerUpdateDto {
	public Long id;
	public String userName;
	private String firstName;
}
