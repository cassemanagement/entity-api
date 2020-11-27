# Entity API

Provides a REST API to interact with the Entities stored within a database.


## Goal

A REST API to provide CRUDL and query functionality on a graph of Entities using Gremlin API.

The API will be deployed as a serverless function on either AWS or Azure and use either a Neptune or Cosmos database, respectively.

## Technology

* Java 11
* Spring 2.3.6
* Project Lombok


## Setup (Azure)

Ensure you have a Cosmos DB account with Gremlin enabled, and a Gremlin graph database 
setup.

Next, create a file in `/resources/cosmosdb.yaml` with the following content:

```yaml
hosts: [$name$.gremlin.cosmosdb.azure.com]
port: 443
username: /dbs/$database$/colls/$collection$
password: $masterKey$
connectionPool: {
  enableSsl: true}
serializer: { className: org.apache.tinkerpop.gremlin.driver.ser.GraphSONMessageSerializerV2d0, config: { serializeResultToString: true }}
```

Replacing the details with the following:

 Setting | Suggested Value | Description |
| ------- | --------------- | ----------- |
| hosts   | [***.gremlin.cosmosdb.azure.com] | This is the Gremlin URI value on the Overview page of the Azure portal, in square brackets, with the trailing :443/ removed.  This value can also be retrieved from the Keys tab, using the URI value by removing https://, changing documents to graphs, and removing the trailing :443/. |
| port | 443 | Set the port to 443 |
| username | `/dbs/<db>/colls/<coll>` | The resource of the form `/dbs/<db>/colls/<coll>` where `<db>` is your database name and `<coll>` is your collection name. |
| password | Your primary key | This is your primary key, which you can retrieve from the Keys page of the Azure portal, in the Primary Key box. Use the copy button on the left side of the box to copy the value. |
| connectionPool | `{enableSsl: true}` | Your connection pool setting for SSL. |
| serializer | { className: org.apache.tinkerpop.gremlin. driver.ser.GraphSONMessageSerializerV1d0, config: { serializeResultToString: true }} | Set to this value and delete any \n line breaks and spaces when pasting in the value. |

Built from [Azure's example](https://docs.microsoft.com/en-us/azure/cosmos-db/create-graph-java).


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