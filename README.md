# top-k-words

Web app that takes a text file and finds the top K most frequent words.

## Requirements:

- Java 17
- Spring Boot 3

## How to build & run app:

- cd app
- ./mvnw clean install
- ./mvnw spring-boot:run

## How to test the app:

curl -X POST -F "file=@test.txt" -F "k=10" http://localhost:8080/api/topk/analyze