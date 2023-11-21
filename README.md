# top-k-words

Web app that takes a text file and finds the top K most frequent words.

## Requirements:

- Java 17
- Spring Boot 3

## How to build app & run unit tests:

```
cd app
./gradlew clean build
```

## How to run the app:

```
cd app
./gradlew run
```

## How to test the app locally:

Postman could be used for friendly UI, but the instuctions are provided for CURL just to make testing simple :upside_down_face:

### Try send GET reqiest to default "hello" endpoint `/` to verify that the app is running at `localhost:8080`:

```
curl -X GET http://localhost:8080/
```

### Then you need to authenticate using `/auth/login` endpoint:

The only credentials allowed are
- email: test@test.com
- password: test 

```
curl -X POST http://localhost:8080/auth/login \
    -H 'Content-Type: application/json' \
    -d '{"email":"test@test.com","password":"test"}'
```

The responce should be in the following format:

```
{"token":"JWT_TOKEN"}
```

### Using the JWT token, you can access `/api/topk/analyze` endpoint: 

```
curl -X POST http://localhost:8080/api/topk/analyze \
    -F "file=@test.txt" -F "k=10" \
    -H "Authorization: Bearer JWT_TOKEN"
```

Feel free to experiment with the file content and K parameter.