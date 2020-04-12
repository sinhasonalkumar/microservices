package com.sonal.demo.istio.userprofileservice.rest.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersProfilesResponse implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -3403603630216331025L;
	
	private List<UserProfileResponse> usersprofiles;

}
