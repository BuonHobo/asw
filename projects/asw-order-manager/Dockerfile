FROM eclipse-temurin
LABEL authors="buonhobo"

ARG JAR

COPY ./$JAR/build/libs/$JAR.jar /target.jar

ENTRYPOINT java -Xms64m -Xmx128m -jar /target.jar