package com.sonal.istio.springboot.profileservice.persistence.bo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileBO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6494102582068439535L;

	private String userId; 
	
	private String accountId;
	
	private String firstName;
	
	private String lastName;
	
	private String address;
	
	private String emailId;
	
}
