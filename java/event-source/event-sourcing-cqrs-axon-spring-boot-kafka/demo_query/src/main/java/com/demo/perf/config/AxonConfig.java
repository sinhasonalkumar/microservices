package com.demo.perf.config;

import org.axonframework.boot.autoconfig.AxonAutoConfiguration;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.kafka.eventhandling.DefaultKafkaMessageConverter;
import org.axonframework.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@AutoConfigureAfter(AxonAutoConfiguration.class)
public class AxonConfig {

	@ConditionalOnMissingBean
	@Bean
	public KafkaMessageConverter<String, byte[]> kafkaMessageConverter(@Qualifier("mapper") ObjectMapper objectMapper) {
		return new DefaultKafkaMessageConverter(new JacksonSerializer(objectMapper));
	}
	
	@Qualifier("mapper")
	@Bean
	public ObjectMapper objectMapper() {
	    return
	            new ObjectMapper()
	            		.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
	                    .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}
	
	@ConditionalOnMissingBean
	@Bean
	public TokenStore tokenStore(@Qualifier("mapper") ObjectMapper objectMapper, EntityManagerProvider entityManagerProvider) {
	  return new JpaTokenStore(entityManagerProvider, new JacksonSerializer(objectMapper));
	}
}
