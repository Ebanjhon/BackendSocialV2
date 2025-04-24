To run these services follow this coment:
run docker compose file: docker-compose up --build -d
the coment will build and run.

To end services: docker-compose down

how to delete docker images: docker rmi $(docker images -q)

To build once project: mvn clean package / mvn clean install -DskipTests (for skip test).
run project: mvn spring-boot:run

How to generate packet file proto: mvn clean compile

the way port run: "HOST_PORT:CONTAINER_PORT"

the cmt show ports running: netstat -ano | findstr :'poorrrt'

To end this port: taskkill /F /PID <PID>

build single service : docker-compose up --build -d auth-service
