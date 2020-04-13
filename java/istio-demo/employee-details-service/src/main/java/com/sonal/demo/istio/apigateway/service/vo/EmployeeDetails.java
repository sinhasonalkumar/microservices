package com.sonal.demo.istio.apigateway.service.vo;

import java.util.List;

import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.Capability;
import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.UserSkillsDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDetails {

	private String userId;
	
	private String fName;
	
	private String lName;
	
	private List<UserSkillsDetails> userSkillsDetails;
	
	private List<Capability> capabilitiesBank;
}
