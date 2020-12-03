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

Ensure you have a Cosmos DB account with Gremlin enabled and a Gremlin graph database, and a storage account:

Next, create a file in `/resources/database.properties` with the following content:

```properties
gremlin.hosts=$name$.gremlin.cosmosdb.azure.com
gremlin.port=443
gremlin.username=/dbs/$database$/colls/$collection$
gremlin.password=$password
gremlin.sslEnabled=false
gremlin.telemetryAllowed=true 
gremlin.maxContentLength=1000
azure.storage.account.name=
azure.storage.account.key=
```

Alternatively, these settings can also be configured from environment variables and the command line:

```bash
azure_storage_account_name= azure_storage_account_key= gremlin_endpoint= gremlin_port= gremlin_username= gremlin_password= gremlin_sslEnabled= ./mvnw spring-boot:run
```

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


## Limitations

For Cosmos DB instances you will also need to set the following property in your `application.property`
so this service knows to delete and re-create entities rather than attempt to update them due to 
Cosmos DB throwing an error when you attempt to amend the databases partition key, even with the same value.
This is due to the fact that partition keys are not a concern Gremlin, but are for Cosmos DB, therefore the 
underlying library used here (as mentioned above) has some mild incompatibility with Cosmos DB.

Property:
```properties
database.isCosmosDb=true
```

[Issue raised on GitHub](https://github.com/microsoft/spring-data-gremlin/issues/232)


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

## Swagger Documentation

Swagger docs in JSON: http://localhost:8080/v3/api-docs
Swagger UI: http://localhost:8080/swagger-ui/index.html?url=/v3/api-docs


## Gremlin Debugging

Gremlin tools can be found on the [Apache Tinkerpop website](https://tinkerpop.apache.org/).

### Gremlin Console

The console can interface directly with our Gremlin Cosmos instance. Database credentials need to be saved in
a yaml file. Eg:

```yaml
hosts: [$gremlin-endpoint$.gremlin.cosmosdb.azure.com]
port: 443
username: /dbs/$db-name$/colls/$graph-name$
password: $password$
connectionPool: {
  enableSsl: true}
serializer: { className: org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerV1d0, config: { serializeResultToString: true }}
```

This file then needs to be referenced from a command line argument. The following example was made with Powershell but a gremlin.sh fil
also exists in the Gremlin console download.

```powershell
PS C:\gremlin> .\bin\gremlin.bat

         \,,,/
         (o o)
-----oOOo-(3)-oOOo-----
plugin activated: tinkerpop.server
plugin activated: tinkerpop.utilities
plugin activated: tinkerpop.tinkergraph

// double slash is a comment in gremlin
// connect to remote
gremlin> :remote connect tinkerpop.server conf/cassemanagement.yaml
==>Configured $gremlin-endpoint$.gremlin.cosmosdb.azure.com
gremlin>

// WITNESS :> this means run on remote
// g.V() displays the entire graph
gremlin> :> g.V()

// display vertex with ID Boba Fett101
gremlin> :> g.V('Boba Fett101')

// values can be filtered
gremlin> :> g.V('Boba Fett101').values('name', 'type')

// a property can be changed
 g.V('Boba Fett101').property('createdBy','debug-user')
```

### Gremlin Spring Logging

Gremlin logging data, such as the Gremlin query being sent, can be enabled by setting org.apache.tinkerpop.gremlin.driver to debug level. Logs like the following can be found:

```
2020-12-03 15:54:11,998 DEBUG org.apache.tinkerpop.gremlin.driver.Client [main] Submitted RequestMessage{, requestId=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx, op='eval', processor='', args={batchSize=64, gremlin=g.V('Boba Fett101').property('comments', '[{"id":"Boba Fett_comment_0","comment":"Ea velit pariatur aliquip do consequat id cupidatat magna eiusmod Lorem. Lorem minim duis eu veniam proident.","createdBy":"sunt velit","createdDate":"2020-06-27T02:38:01.289Z"}]').property('createdDate', 1592698247384).property('createdBy', 'test').property('name', 'Boba: 2020-12-03T15:54Z').property('description', 'Ad sunt deserunt magna minim et sit cupidatat culpa sint exercitation.').property('attributes', '[{"key":"nickname","value":"sit magna reprehenderit duis","type":"STRING","visibility":"MINIMISED","required":false}]').property('_classname', 'com.example.entityapi.model.Entity').property('type', 'electronic')}} to - Connection{host=Host{address=$gremlin-endpoint$.gremlin.cosmosdb.azure.com, hostUri=wss://$gremlin-endpoint$.gremlin.cosmosdb.azure.com:443/gremlin}}
```
