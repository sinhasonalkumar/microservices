package com.sonal.spring.security.rs.oidc.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sonal.spring.security.rs.oidc.persistence.bo.ProductBO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(value = Include.NON_NULL)
@ToString(includeFieldNames = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3825426965718308471L;

	private List<ProductBO> products;
	
	private String message;
}
