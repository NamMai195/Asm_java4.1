package com.poly.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

	private Integer id;

	private String fullname;

	private String password;

	private String email;

	private boolean admin = false;

	private boolean active = true;
}
