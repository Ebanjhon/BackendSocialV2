
FROM eclipse-temurin:21-jdk

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR vào container
COPY target/*.jar app.jar

EXPOSE 8086

# Chạy ứng dụng
CMD ["java", "-jar", "app.jar"]