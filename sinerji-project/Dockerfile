# Fase de construção
FROM maven:3.8.6-openjdk-8 AS builder

WORKDIR /app
COPY pom.xml .
COPY src src/

# Build da aplicação (gerando o .war)
RUN mvn clean package -DskipTests

# Fase de execução
FROM tomee:8-jre-8.0.6-webprofile

# Remove aplicações padrão
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia o .war para o Tomcat
COPY --from=builder /app/target/*.war /usr/local/tomcat/webapps/ROOT.war


EXPOSE 8080

CMD ["catalina.sh", "run"]