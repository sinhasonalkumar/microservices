# JIB LAYERED JAR Build Maven Plugin

```
<plugin>
	<groupId>com.google.cloud.tools</groupId>
	<artifactId>jib-maven-plugin</artifactId>
	<version>2.7.1</version>
</plugin>
```

# Build Application and Docker Image

```
./mvnw compile jib:build -Dimage=sinhasonalkumar/profile-service-jib:v1

```

