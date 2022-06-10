#### Method: GET
> curl http://localhost:8080/api/v1/users

#### Method: POST

##### 1. urlencoded 전송 Content-Type: application/x-www-form-urlencoded
> curl -X POST http://localhost:8080/api/v1/users/form -H 'Content-Type: application/x-www-form-urlencoded' -d name=하잉 -d age=34

##### 2. 파일 전송 Content-Type: multipart/form-data
>  curl -X POST http://localhost:8080/api/v1/users/multipart -H 'Content-Type: multipart/form-data' -F files=@{filepath1} -F files=@{filepath2}

#### 3. 파일 + text 전송 Content-Type: multipart/form-data
>  curl -X POST http://localhost:8080/api/v1/users/multipart2 -H 'Content-Type: multipart/form-data' -F files=@{filepath1} -F name=헬로우 -F age=22

