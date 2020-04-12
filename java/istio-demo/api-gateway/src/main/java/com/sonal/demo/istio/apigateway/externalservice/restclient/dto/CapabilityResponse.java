package com.sonal.demo.istio.apigateway.externalservice.restclient.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CapabilityResponse {

	private List<Capability> capabilities;
}
