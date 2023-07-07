## Hotel Room Booking Application

## Members work this project

(Nguyen Trung Nhat Nam)[nguyentrungnhatnam@gmail.com]

(Vivek V Pai)[vishwasvkumar5@gmail.com]

We are breaking the Hotel room booking application into three different microservices, which are as follows:

* Eureka-Server - Eureka Server is an application that holds the information about all client-service applications.
* API-Gateway - This service is exposed to the outer world and is responsible for routing all requests to the microservices internally.
* Booking service - This service is responsible for collecting all information related to user booking and sending a confirmation message once the booking is confirmed.
* Payment service - This is a dummy payment service; this service is called by the booking service for initiating payment after confirming rooms.

# Getting Started with Ecommerce Upgrad Backend

Application and environment were related to this app includes:
- [Java](https://www.oracle.com/cis/java/technologies/downloads/#java20)
- [Docker](https://www.docker.com/)
- [Spring boot](https://spring.io/)

### The way is running this app

Running by your commandline:

Run local environment for integration
`docker-compose up -d`

Run this app:

`./gradlew bootRun`

### Postman for information api is used:

https://documenter.getpostman.com/view/15495446/TzY3Cw65

### About this structure project

project structure

``` bash
.
├── api-gateway             # Api-Gateway Project
├── bookingservice          # Booking service
├── eureka-server           # Eureka Server
├── paymentservice          # Payment service 
└── docker-compose.yml      # Run environment for testing
```
component structure of api gateway

``` bash
.
├── ...                  
├── src
│   └── Main                # Source contained the configuration of service gatewayapi
├── resources
│   └── application.yml     # Contain the way configuration of API-Gateway           
├── ...               
└── build.gradle            # Contain the implementation library which is used.

```

component structure of booking service

``` bash
.
├── ...                  
├── src
│   └── main               
│       └── java                            
│           └── com.ntnn.booking            
│                ├── common                 # Source contained the common utilies are using on this project
│                ├── config                 # Source contained the configuration of this project
│                ├── controller             # Source contained the rest api
│                ├── entity                 # Source contained entity which is DTO
│                ├── exception              # Source contained the exception of this project
│                ├── helper                 # Source contained the utilies are using on this project
│                ├── model                  # Source contained the api response to backend
│                ├── repository             # Source contained the repository which mapped with JPA
│                └── service                # Source contained the service booking
│   └── test                                # Source contained the configuration of service gatewayapi
├── resources
│   └── application.yml     # Contain the way configuration of booking service          
├── ...               
└── build.gradle            # Contain the implementation library which is used.

```

component structure of eureka

``` bash
.
├── ...                  
├── src
│   └── Main                # Source contained the configuration of service eureka
├── resources
│   └── application.yml     # Contain the way configuration of eureka    
├── ...               
└── build.gradle            # Contain the implementation library which is used.

```

component structure of payments service

``` bash
.
├── ...                  
├── src
│   └── main               
│       └── java                            
│           └── com.ntnn.booking        
│                ├── controller             # Source contained the rest api
│                ├── entity                 # Source contained entity which is DTO
│                ├── helper                 # Source contained the utilies are using on this project
│                ├── model                  # Source contained the api response to backend
│                ├── repository             # Source contained the repository which mapped with JPA
│                └── service                # Source contained the service booking
│   └── test                                # Source contained the configuration of service gatewayapi
├── resources
│   └── application.yml     # Contain the way configuration of payment service           
├── ...               
└── build.gradle            # Contain the implementation library which is used.

```

Schema:

``` bash
-- public.booking definition

-- Drop table

-- DROP TABLE public.booking;

CREATE TABLE public.booking (
	id int4 NOT NULL,
	aadhar_number varchar(255) NULL,
	booked_on timestamp(6) NULL,
	from_date timestamp(6) NULL,
	num_of_rooms int4 NULL,
	room_numbers varchar(255) NULL,
	room_price int4 NOT NULL,
	to_date timestamp(6) NULL,
	transaction_id int4 NULL,
	CONSTRAINT booking_pkey PRIMARY KEY (id)
);

-- public."transaction" definition

-- Drop table

-- DROP TABLE public."transaction";

CREATE TABLE public."transaction" (
	transaction_id int4 NOT NULL,
	booking_id int4 NULL DEFAULT 0,
	card_number varchar(255) NULL,
	payment_mode varchar(255) NULL,
	upi_id varchar(255) NULL,
	CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id)
);

```