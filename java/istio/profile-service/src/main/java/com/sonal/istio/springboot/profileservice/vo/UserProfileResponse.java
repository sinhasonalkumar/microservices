package com.sonal.istio.springboot.profileservice.vo;

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
public class UserProfileResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -567146437065687874L;

	private String firstName;
	
	private String lastName;
	
	private String accountId;
	
	private String address;
	
	private String emailId;
}
