format-java:
	mvn prettier:write

build:
	mvn clean package -DskipTests

run:
	mvn spring-boot:run
