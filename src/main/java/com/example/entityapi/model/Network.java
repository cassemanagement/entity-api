package com.example.entityapi.model;

import com.microsoft.spring.data.gremlin.annotation.EdgeSet;
import com.microsoft.spring.data.gremlin.annotation.Graph;
import com.microsoft.spring.data.gremlin.annotation.VertexSet;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Graph public class Network
{
	@Id
	private String id;

	@EdgeSet
	private List<Object> edges;

	@VertexSet
	private List<Object> vertexes;

	public Network()
	{
		this.edges = new ArrayList<Object>();
		this.vertexes = new ArrayList<Object>();
	}

}
