FROM openjdk:17

WORKDIR /usr/src/app
VOLUME /tmp
COPY . /usr/src/app

ADD /build/libs/intensive_2_4_1_springboot_security-0.0.1-SNAPSHOT.jar springboot_security.jar
ENTRYPOINT ["java", "-jar", "springboot_security.jar"]