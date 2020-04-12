package com.sonal.demo.istio.userprofileservice.rest.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5496618596695439776L;

	private String userId;
	
	private String firstName;
	
	private String lastName;
}
