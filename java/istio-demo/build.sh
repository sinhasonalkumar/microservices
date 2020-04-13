#!/bin/bash

docker rmi -f $(docker images | grep sonal | awk "{print \$3}")

cd /Users/sonal/sonal/research_workspace/repo/gitHub/sinhasonalkumar/java/istio-demo/

cd employee-details-service
bash ./mvnw package
cd ../

cd user-skills-ratings-service
bash ./mvnw package
cd ../

cd capability-service
bash ./mvnw package
cd ../

cd discover-skills-service
bash ./mvnw package
cd ../

cd user-profile-service
bash ./mvnw package
cd ../

cd user-skills-service
bash ./mvnw package
cd ../

docker images | grep sinhasonalkumar


