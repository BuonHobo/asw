FROM eclipse-temurin
LABEL authors="buonhobo"

ARG JAR

COPY ./$JAR/build/libs/$JAR.jar /target.jar

ENTRYPOINT java -Xms64m -Xmx128m -jar /target.jar

HEALTHCHECK --start-period=30s --interval=10s CMD curl -f http://localhost:8080/actuator/health || exit 1