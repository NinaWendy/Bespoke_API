#  Scope_News

### By Wendy Nina

This is a bare-bones example of a News application providing a REST
API to an organisational News Portal.

The entire application is contained within the `App.java` file.


## Install

    gradle install

## Run the app

    gradle run

## REST API 

The REST API to the News app is described below.

## Get list of Departments

### Request

`GET /departments`

    curl -i -H 'Accept: application/json' http://localhost:4567/departments

### Response

    HTTP/1.1 200 OK
    Date: Fri, 20 May 2022 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    []

## Create a new Department

### Request

`POST /departments/new`

    curl -i -H 'Accept: application/json' -d 'name=Finance&description=moneydetail&size=5' http://localhost:4567/departments/new

### Response

    HTTP/1.1 201 Created
    Date: Fri, 20 May 2022 12:36:30 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: /departments/1
    Content-Length: 36

    {"id":1,"name":"Finance","description":"deals with money","size":20}

## Get a specific Department

### Request

`GET /departments/id`

    curl -i -H 'Accept: application/json' http://localhost:4567/departments/1

### Response

    HTTP/1.1 200 OK
    Date: Fri, 20 May 2022 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 36

    {"id":1,"name":"Finance","description":"deals with money","size":20}


## Get a non-existent Department

### Request

`GET /departments/id`

    curl -i -H 'Accept: application/json' http://localhost:4567/departments/999

### Response

    HTTP/1.1 404 Not Found
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 404 Not Found
    Connection: close
    Content-Type: application/json
    Content-Length: 35

    {"status":404,"reason":"Not found"}

## Create a new User

### Request

`POST /users/new`

    curl -i -H 'Accept: application/json' -d 'name=Nina&role=Head&position=manager&departmnt_id=2' http://localhost:4567/users/new

### Response

    HTTP/1.1 201 Created
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: /users/1
    Content-Length: 35

    {"id":1,"name":"Nina","role":"Board Head", "position":"Manager", "department_id":1}

## Get list of Users

### Request

`GET /users`

    curl -i -H 'Accept: application/json' http://localhost:4567/users

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 74

    [{"id":1,"name":"Nina","role":"Board Head", "position":"Manager", "department_id":1}]

## Get list of News

### Request

`GET /news`

    curl -i -H 'Accept: application/json' http://localhost:4567/news

### Response

    HTTP/1.1 200 OK
    Date: Fri, 20 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 2

    []

## Create news post

### Request

`POST /news/new`

    curl -i -H 'Accept: application/json' -d 'post=Java&writtenby=Nina http://localhost:4567/news/new

### Response

    HTTP/1.1 201 Created
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: /department/1
    Content-Length: 36

    {"id":1,"post":Mocking Bird","writtenby":"Nina"}

## Get specific news post

### Request

`GET /news/id`

    curl -i -H 'Accept: application/json' http://localhost:4567/news/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:30 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 36

    {"id":1,"post":Mocking Bird","writtenby":"Nina"}


## Create a user in a specific department

### Request

`POST /departments/:department_id/users/new`

### Response

    HTTP/1.1 201 Created
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: /users/1
    Content-Length: 35

    {"id":1,"name":"Nina","role":"Board Head", "position":"Manager", "department_id":1}


## Get all users of a specific department

### Request

`GET /departments/:department_id/users`

    curl -i -H 'Accept: application/json' -X PUT http://localhost:4567/departments/department_id/users

### Response

    HTTP/1.1 201 Created
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: /users/1
    Content-Length: 35

    {"id":1,"name":"Nina","role":"Board Head", "position":"Manager", "department_id":1}

## Create a news post for a specific department

### Request

`POST /departments/:department_id/news/new`

### Response

    HTTP/1.1 201 Created
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: /departments/department_id/news/new
    Content-Length: 35

    {"id":1,"post":Mocking Bird","writtenby":"Nina"}

## Get all news post for a specific department

### Request

`GET /departments/:department_id/news`

    curl -i -H 'Accept: application/json' -X PUT http://localhost:4567/departments/department_id/news

### Response

    HTTP/1.1 201 Created
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 201 Created
    Connection: close
    Content-Type: application/json
    Location: /departments/department_id/news
    Content-Length: 35

    {"id":1,"name":"Nina","role":"Board Head", "position":"Manager", "department_id":1}

## Delete a User

### Request

`DELETE /users/id`

    curl -i -H 'Accept: application/json' -X DELETE http://localhost:4567/users/1

### Response

    HTTP/1.1 204 No Content
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 204 No Content
    Connection: close


## Try to delete same user again

### Request

`DELETE /user/id`

    curl -i -H 'Accept: application/json' -X DELETE http://localhost:4567/users/1

### Response

    HTTP/1.1 404 Not Found
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 404 Not Found
    Connection: close
    Content-Type: application/json
    Content-Length: 35

    {"status":404,"reason":"Not found"}

## Get deleted user

### Request

`GET /users/1`

    curl -i -H 'Accept: application/json' http://localhost:4567/users/1

### Response

    HTTP/1.1 404 Not Found
    Date: Thu, 24 Feb 2011 12:36:33 GMT
    Status: 404 Not Found
    Connection: close
    Content-Type: application/json
    Content-Length: 35

    {"status":404,"reason":"Not found"}

## Get changed Thing

### Request

`GET /thing/id`

    curl -i -H 'Accept: application/json' http://localhost:7000/thing/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 40

    {"id":1,"name":"Foo","status":"changed"}

## Change a Thing

### Request

`PUT /thing/:id`

    curl -i -H 'Accept: application/json' -X PUT -d 'name=Foo&status=changed2' http://localhost:7000/thing/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:31 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 41

    {"id":1,"name":"Foo","status":"changed2"}

## Attempt to change a Thing using partial params

### Request

`PUT /thing/:id`

    curl -i -H 'Accept: application/json' -X PUT -d 'status=changed3' http://localhost:7000/thing/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 41

    {"id":1,"name":"Foo","status":"changed3"}

## Attempt to change a Thing using invalid params

### Request

`PUT /thing/:id`

    curl -i -H 'Accept: application/json' -X PUT -d 'id=99&status=changed4' http://localhost:7000/thing/1

### Response

    HTTP/1.1 200 OK
    Date: Thu, 24 Feb 2011 12:36:32 GMT
    Status: 200 OK
    Connection: close
    Content-Type: application/json
    Content-Length: 41

    {"id":1,"name":"Foo","status":"changed4"}
