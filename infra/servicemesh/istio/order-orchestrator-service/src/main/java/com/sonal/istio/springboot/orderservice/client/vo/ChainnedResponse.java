package com.sonal.istio.springboot.orderservice.client.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChainnedResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2612780242742875927L;
	

	private UserProfileResponse userProfileResponse;
	
	private ProductResponse productResponse;
	
	private PaymentResponse paymentResponse;
	
	private ShippingResponse shippingResponse;
	
	private NotificationResponse notificationResponse;
	
}
