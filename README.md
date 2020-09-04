# demo-oracle
 The convenience way of connect oracle DB to do some DDL operation.

## Usage

1. start jar in folder target command:
	java -jar target\demo-oracle-0.0.1-SNAPSHOT.jar --USER=system --PWD=oracle --HOST=localhost --PORT=49161 --SID=xe
	

## Start Oracle via Docker
1. docker run -d -p 49160:22 -p 49161:1521 deepdiver/docker-oracle-xe-11g