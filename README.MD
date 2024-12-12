Testing
=======

Setup
-----
fill database
```
org.example.CourseRetriever.main("sander-mak");
```
read data
```http request
###
GET http://localhost:8080/courses
```

update notes
```http request
###
POST http://localhost:8080/courses/04a053b6-3b4e-41c6-9b78-fba4522a0bee/notes
Content-Type: text/plain

note text
```


Unit tests
----------
```shell
mvn test 
```

Manual tests
------------
```http request
GET https://app.pluralsight.com/profile/data/author/sander-mak/all-content
Accept: application/json
```
