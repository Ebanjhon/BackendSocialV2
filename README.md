docker build -t auth-service .
build image: docker build -t 'yourname' .
build container: docker-compose build
chay container moi: docker-compose up -d
check: docker ps
xoa toaà bô image : docker rmi $(docker images -q)
B1 clean and build packet tạo file jar: mvn clean package họac mvn clean install -DskipTests (bỏ qua téet)
B2 docker-compose build
B3 chay container moi hoăc sua file: docker-compose up -d

cach pỏot chạy
"HOST_PORT:CONTAINER_PORT"

chạy rabbitMQ: docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management
http://localhost:15672

mvn spring-boot:run

build va khoi dong :docker-compose up --build -d

sinh file proto: mvn clean compile

lenh check port co dang chay: netstat -ano | findstr :'poorrrt'
lenh tat port do ket thuc: taskkill /F /PID <PID>
