package com.sonal.istio.springboot.paymentservice.client.vo;

import java.io.Serializable;

import com.sonal.istio.springboot.paymentservice.vo.PaymentResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChainedResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2612780242742875927L;
	

	private UserProfileResponse userProfileResponse;
	
	private NotificationResponse notificationResponse;
	
	private PaymentResponse paymentResponse;


}
