format-java:
	mvn prettier:write

compile:
	mvn clean package -DskipTests

run:
	mvn spring-boot:run
