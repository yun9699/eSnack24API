# Ubuntu 베이스 이미지 사용
FROM ubuntu:latest

# 필수 패키지 업데이트 및 설치
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk && \
    rm -rf /var/lib/apt/lists/*

# 애플리케이션 JAR 파일 복사
COPY build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]