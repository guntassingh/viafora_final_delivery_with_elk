# Url Shortener

Link shortening service that can be used to turn fully qualified URLs into short code to be usedin links.

## Installation

Package manager 
```bash
[Docker](https://www.docker.com/) to install Docker.


1-ConfigServerApplication-0.0.1-SNAPSHOT.jar
2-EurekaServer-0.0.1-SNAPSHOT.jar
3-ZuulGatewayService-0.0.1-SNAPSHOT.jar
4-URLShortener-0.0.1-SNAPSHOT.jar

## Step

1- Create docker network "SpringApp".
2- Run docker mysql image.
3- Machine IP instead of localhost in share and resource folder ConfigServerApplication application.
application.yml file
gateway-service.yml  file
4-Machine IP instead of localhost in  resource folder ConfigServerApplication application.
  application.properties
5- Machine IP instead of localhost in  resource folder ZuulGatewayService application.
  bootstrap.yml
6- Machine IP instead of localhost in  resource folder URLShortener application.
  application.properties

7- Go to ConfigServerApplication folder and Run command mvn clean install
8- Go to target folder run command javajava -jar ConfigServerApplication-0.0.1-SNAPSHOT.jar

9- Go to EurekaServer folder and Run command mvn clean install
10- Go to target folder run command javajava -jar EurekaServer-0.0.1-SNAPSHOT.jar

11- Go to ZuulGatewayService folder and Run command mvn clean install
12- Go to target folder run command javajava -jar ZuulGatewayService-0.0.1-SNAPSHOT.jar'

13- Go to URLShortener folder and Run command mvn clean install
14- Go to target folder run command java -jar URLShortener-0.0.1-SNAPSHOT.jar

## Usage

1- Spring boot 2.1.8.
2- Java 8
3- SSL Certificate

```Java
import service

UrlService.find('MmM3MT') # returns Url
UrlService.createShortURL('CreateLinkDTO') # returns LinkDTO
StatisticService.create('Statistic') # returns Statistic
StatisticService.getStatisticsSummary()  # returns StatisticsSummaryDTO
StatisticService.getStatisticsSummaryByCode() # returns StatisticsSummaryDTO
```

## Request 
[Redirect Url](https://localhost:8443/v1/MmM3MT/)   ->   Open new page     
[Create Url](https://localhost:8443/v1/url)  
Header -  token =1234       
body-
{
  "customerId": "1112",
  "url": "http://yahoo.com"
 }


## Contributing
Please make sure to update tests as appropriate.

## License
[Rsystem](https://www.rsystems.com/)