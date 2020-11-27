package com.example.entityapi;


import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.driver.exception.ResponseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EntityService
{

	List<Entity> getEntities() throws FileNotFoundException, URISyntaxException
	{
		Client client = getClient();

		var query = "g.V()"; // get all vertices

		runQuery(client, query);

		// Properly close all opened clients and the cluster
		client.getCluster().close();

		return null;
	}


	/**
	 * Use the Cluster instance to construct different Client instances (e.g. one for sessionless communication
	 * and one or more sessions). A sessionless Client should be thread-safe and typically no more than one is
	 * needed unless there is some need to divide connection pools across multiple Client instances. In this case
	 * there is just a single sessionless Client instance used for the entire App.
	 */
	private Client getClient() throws FileNotFoundException, URISyntaxException
	{
		try {
			// Attempt to create the connection objects
			var cosmosFilePath = getClass().getClassLoader().getResource("cosmosdb.yaml");
			if (cosmosFilePath == null) {
				throw new IllegalArgumentException("file not found!");
			}
			Cluster cluster = Cluster.build(new File(cosmosFilePath.toURI())).create();
			return cluster.connect();
		} catch (FileNotFoundException | URISyntaxException e) {
			// Handle file errors.
			System.out.println("Couldn't find the configuration file.");
			e.printStackTrace();
			throw e;
		}
	}

	private void runQuery(Client client, String query){
		System.out.println("\nSubmitting this Gremlin query: " + query);

		// Submitting remote query to the server.
		ResultSet results = client.submit(query);

		CompletableFuture<List<Result>> completableFutureResults;
		CompletableFuture<Map<String, Object>> completableFutureStatusAttributes;
		List<Result> resultList;
		Map<String, Object> statusAttributes;

		try{
			completableFutureResults = results.all();
			completableFutureStatusAttributes = results.statusAttributes();

			resultList = completableFutureResults.get();
			for (Result result : resultList) {
				System.out.println("\nQuery result:");
				System.out.println(result.toString());
			}

			statusAttributes = completableFutureStatusAttributes.get();
			// Status code for successful query. Usually HTTP 200.
			System.out.println("Status: " + statusAttributes.get("x-ms-status-code").toString());
			// Total Request Units (RUs) charged for the operation, after a successful run.
			System.out.println("Total charge: " + statusAttributes.get("x-ms-total-request-charge").toString());
		}
		catch(ExecutionException | InterruptedException e){
			e.printStackTrace();
		}
		catch(Exception e){
			ResponseException re = (ResponseException) e.getCause();

			// Response status codes. You can catch the 429 status code response and work on retry logic.
			System.out.println("Status code: " + re.getStatusAttributes().get().get("x-ms-status-code"));
			System.out.println("Substatus code: " + re.getStatusAttributes().get().get("x-ms-substatus-code"));

			// If error code is 429, this value will inform how many milliseconds you need to wait before retrying.
			System.out.println("Retry after (ms): " + re.getStatusAttributes().get().get("x-ms-retry-after"));

			// Total Request Units (RUs) charged for the operation, upon failure.
			System.out.println("Request charge: " + re.getStatusAttributes().get().get("x-ms-total-request-charge"));

			// ActivityId for server-side debugging
			System.out.println("ActivityId: " + re.getStatusAttributes().get().get("x-ms-activity-id"));
			throw(e);
		}
	}
}
