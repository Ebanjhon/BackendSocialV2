# Sử dụng JDK 21 làm môi trường chạy
FROM eclipse-temurin:21-jdk

# Cài đặt Redis
RUN apt-get update && apt-get install -y redis-server

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR vào container
COPY target/*.jar app.jar

# Mở cổng 8080
# Mở cổng 6379 cho Redis và 8080 cho ứng dụng Spring Boot
EXPOSE 6379
EXPOSE 8080

# Chạy ứng dụng
# CMD ["java", "-jar", "app.jar"]
CMD redis-server /etc/redis/redis.conf & java -jar app.jar
