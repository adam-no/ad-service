# Ad-Service

## Quickstart

### Prerequisites

   - at least Java 17 installed

### Build

      ./gradlew build

### Run

#### Running from command line

      ./gradlew bootRun

#### Running as a docker image

      ./gradlew jibDockerBuild
      docker run -p 127.0.0.1:8080:8080 ad-service

#### Running application via IntelliJ

Use `AdApplication` run-configuration stored in the project file

### REST API

The Rest API is accessible via Swagger:

      http://localhost:8080/swagger-ui/index.html

## Domain

### Definitions 
- Owner - A user who creates advertisements. Each owner has a name.
- Listing - An advertisement which contains a title, description, price, published state. Each listing is created by an owner

### Capabilities
- Create an owner
- Create a listing
- Update a listing
- Publish/Unpublish a listing
- Get all listings

### Notes
Each owner can have a maximum of 10 active listings at any one time. Any attempt to
exceed that limit should result in an error. While not necessary to implement as part of the
task an understanding of how you would restrict updates of listings to their owner is
expected.

## Architecture
This application is following the concept of Hexagonal Architecture (aka Ports & Adapters):
- https://alistair.cockburn.us/hexagonal-architecture/
- https://herbertograca.com/2017/11/16/explicit-architecture-01-ddd-hexagonal-onion-clean-cqrs-how-i-put-it-all-together/

The project structure and implementation are inspired by:
- https://jmgarridopaz.github.io/content/hexagonalarchitecture-ig/chapter2.html

### Module dependencies

![module-dependencies](./img/ad-module-dependencies.png)

## Sample use-case scenario

1. Create Owner

       POST /api/v1/owners
       
       {
         "name": "John"
       }
      
2. Create Listing
 
       POST /api/v1/listings

       {
         "title": "Some title",
         "description": "Some description",
         "price": 10.20,
         "ownerName": "John"
       }

3. Get Listings

       GET /api/v1/listings

4. Publish Listing

       POST /api/v1/listings/0bf6a9ac-e822-421a-8614-1f7a2ff16814/publish