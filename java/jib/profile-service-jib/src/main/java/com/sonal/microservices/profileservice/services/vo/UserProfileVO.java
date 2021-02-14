package com.sonal.microservices.profileservice.services.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileVO {

	private String userName;

	private String firstName;

	private String lastName;
}
