package com.sonal.microservices.profileservice.controller.vo;

import com.sonal.microservices.profileservice.services.vo.UserProfileVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseVO {

	private UserProfileVO userProfileVO;
}
