# Etapa de construção
FROM maven:3.8-openjdk-17 AS builder

WORKDIR /app

# Copia apenas o arquivo pom.xml para aproveitar o cache ao baixar dependências
COPY pom.xml .

# Baixa as dependências do Maven
RUN mvn dependency:go-offline

# Copia o restante dos arquivos do projeto
COPY . .

# Compila e empacota a aplicação Spring Boot em um JAR (ignorando testes)
RUN mvn clean install -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o JAR construído na etapa de construção
COPY --from=builder /app/target/aplication-0.0.1-SNAPSHOT.jar /app.jar

# Expor a porta usada pela aplicação Spring Boot (ajuste conforme necessário)
EXPOSE 8080

# Comando de execução
CMD ["java", "-jar", "/app.jar"]
