# my-secured-app

# To start docker mongo and load data
docker run -d --rm --name mongo-docker -p 27017:27017 mongo

# To test using Postman

1.
http://localhost:8080/api/auth/signup

Method: POST\
Content-Type: Application/json

Payload\
{
	"username": "subha81788",
	"email": "subha81788@gmail.com",
	"password": "topsecret12345",
	"roles": ["user","moderator","admin"] 
}

2.
http://localhost:8080/api/auth/signup

Method: POST\
Content-Type: Application/json

Payload\
{
	"username": "juhi92",
	"email": "sanjukta92nbdp@gmail.com",
	"password": "pa$$word@123",
	"roles": ["user","moderator"] 
}

3.
http://localhost:8080/api/auth/signup

Method: POST\
Content-Type: Application/json

Payload\
{
	"username": "sarmi83",
	"email": "sarmi83@gmail.com",
	"password": "saltlakeboater",
	"roles": ["user"] 
}

4.
http://localhost:8080/api/auth/signup

Method: POST\
Content-Type: Application/json

Payload\
{
	"username": "subhnath",
	"email": "subhashis.a.nath@capgemini.com",
	"password": "ilovecoding",
	"roles": ["user"] 
}
