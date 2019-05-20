package gdb;

import java.util.HashSet;

public interface Graph {

	HashSet<Vertex> get(Vertex v);
	void addVertex(Vertex v);
	void removeVertex(Vertex v);
	boolean isVertexExists(Vertex v);
	void addEdge(Vertex v, Vertex e);
	void overrideEdge(Vertex v, HashSet<Vertex> e);
	Integer size();
	void removeEdge(Vertex v, Vertex e);
	void printGraph();
	void clear();
}
