# demo-oracle
 The convenience way of connect oracle DB to do some DDL operation.

## Usage

- Start jar in folder target command:
```json
java -jar target\demo-oracle-0.0.1-SNAPSHOT.jar --USER=system --PWD=oracle --HOST=localhost --PORT=49161 --SID=xe
```
- Request sql execution:
```json
curl --location --request POST 'localhost:6666/sql' \
--header 'Content-Type: text/plain' \
--data-raw 'select * from a'
```

## Start Oracle via Docker
1. docker run -d -p 49160:22 -p 49161:1521 deepdiver/docker-oracle-xe-11g
2. login with ssh:
```json
ssh root@localhost -p 49160
password: admin
```
3. connection info:
```json
hostname: localhost
port: 49161
sid: xe
username: system
password: oracle
```