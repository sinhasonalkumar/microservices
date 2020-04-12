package com.sonal.demo.istio.capabilityservice.rest.dto;

import java.util.List;

import com.sonal.demo.istio.capabilityservice.service.vo.Capability;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CapabilityResponseDTO {

	private List<Capability> capabilities;
}
