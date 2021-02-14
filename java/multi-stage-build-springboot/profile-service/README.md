# LAYERED JAR Build Maven Plugin

```
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
	<configuration>
		<layers>
			<enabled>true</enabled>
		</layers>
	</configuration>
</plugin>
```

# Layer Tool To View Layers

```
java -Djarmode=layertools -jar target/profile-service.jar list

```

