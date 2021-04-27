package com.kovunov.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerUpdateDto {
    private Long id;
    private String userName;
    private String firstName;
}
