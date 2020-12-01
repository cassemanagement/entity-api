# Entity API

Provides a REST API to interact with the Entities stored within a database.


## Goal

A REST API to provide CRUDL and query functionality on a graph of Entities using Gremlin API.

The API will be deployed as a serverless function on either AWS or Azure and use either a Neptune or Cosmos database, respectively.


## TODOs

* Return links with entities (graph?)
* Setup as modules
* Write tests
    * Controller - check endpoints call service & validation
    * Service - calls repository
    * Component test (e2e)
* Exception handling

* Azure Function setup
* AD Integration
* Create build script (CI/CD)

Stretch:
* AWS Lambda setup
* Docker deployable
* Versioning
* Raise events/audit trail

## Technology

* Java 11
* Spring 2.3.6
* Project Lombok


## Setup (Azure)

Ensure you have a Cosmos DB account with Gremlin enabled, and a Gremlin graph database 
setup.

Next, create a file in `/resources/database.properties` with the following content:

```properties
gremlin.hosts=$name$.gremlin.cosmosdb.azure.com
gremlin.port=443
gremlin.username=/dbs/$database$/colls/$collection$
gremlin.password=$password
gremlin.sslEnabled=false
gremlin.telemetryAllowed=true 
gremlin.maxContentLength=1000
```

Also, environment variables

gremlin_endpoint= gremlin_port= gremlin_username= gremlin_password= gremlin_sslEnabled=

Replacing the details with the following:

 Setting | Suggested Value | Description |
| ------- | --------------- | ----------- |
| hosts   | ***.gremlin.cosmosdb.azure.com | This is the Gremlin URI value on the Overview page of the Azure portal, in square brackets, with the trailing :443/ removed.  This value can also be retrieved from the Keys tab, using the URI value by removing https://, changing documents to graphs, and removing the trailing :443/. |
| port | 443 | Set the port to 443 |
| username | `/dbs/<db>/colls/<coll>` | The resource of the form `/dbs/<db>/colls/<coll>` where `<db>` is your database name and `<coll>` is your collection name. |
| password | Your primary key | This is your primary key, which you can retrieve from the Keys page of the Azure portal, in the Primary Key box. Use the copy button on the left side of the box to copy the value. |
| telemetryAllowed | true | Set false to disable telemetry |
| maxContentLength | 1000 | Max content length. |

Further information about the Gremlin API and this setup can be found on this 
[MS Azure example](https://github.com/microsoft/spring-data-gremlin).


## Build/Run

To build:
```bash
mvn clean install -Dmaven.test.skip=true
```

To test:
```bash
mvn test
```

To run:
```bash
mvn spring-boot:run
```