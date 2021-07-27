package com.sonal.actuator.actuatordemo.health.indicator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterOptions;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;

@Component
public class KafkaHealthIndicator extends AbstractHealthIndicator {

	@Autowired
	private AdminClient kafkaAdminClient;
	
	private final DescribeClusterOptions options = new DescribeClusterOptions().timeoutMs(1000);

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		
		try {
			DescribeClusterResult describeCluster = kafkaAdminClient.describeCluster(options);
			final String clusterId = describeCluster.clusterId().get();
			final int brokerCount = describeCluster.nodes().get().size();
			final KafkaFuture<Collection<Node>> nodes = kafkaAdminClient.describeCluster().nodes();
			final List<Integer> brokers = nodes.get()
											   .stream()
											   .map(Node::id)
											   .collect(Collectors.toList());
			
			//Set<String> set = kafkaAdminClient.listTopics().names().get();
			//kafkaAdminClient.describeTopics(topicNames)
			//kafkaAdminClient.describeConsumerGroups(groupIds)
			
			builder.up()
				   .withDetail("clusterId", clusterId)
				   .withDetail("brokerCount", brokerCount)
				   .withDetail("brokers", brokers)
				   .build();
			
		} catch (Exception e) {
			builder.down()
				   .withException(e)
			       .build();
			e.printStackTrace();
		}

	}

}
