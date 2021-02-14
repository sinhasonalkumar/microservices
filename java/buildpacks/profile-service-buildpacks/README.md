# BuildPacks LAYERED JAR Build Maven Plugin

```
<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
	<configuration>
		<image>
			<name>sinhasonalkumar/${project.artifactId}:v2</name>
		</image>
	</configuration>
</plugin>
```

# Build Application and Docker Image

```
./mvnw spring-boot:build-image

```

