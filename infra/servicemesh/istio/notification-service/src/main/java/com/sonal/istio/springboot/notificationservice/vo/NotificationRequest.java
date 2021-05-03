package com.sonal.istio.springboot.notificationservice.vo;

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
public class NotificationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2487410903792303463L;

	private String email;
	
	private String message;
	
}
