package algorithm;

import java.util.LinkedList;

/**
 * L'oggetto Node rappresenta  un nodo.  Sono memorizzati alcuni parametri: il suo nome, se il nodo è stato visitato,
 * se il nodo è connesso, la distanza con il nodo source, una lista di archi che partono dal nodo, il nodo precedente.
 */

public class Node implements Comparable<Node> {

	private boolean connected = false;
	private boolean visited = false;
	private int name;
	private LinkedList<Edge> edgeList = new LinkedList<Edge>();
	private double distance = Double.MAX_VALUE;
	private Node predecessorNode;

	//constructor
	public Node(int name) {
		this.name = name;
		this.edgeList = new LinkedList<>();
	}

	//It is used for add elements in the AdjacencyList
	public void addNeighbour(Edge edge) {
		this.edgeList.add(edge);
	}

	//getter and setter
	public boolean getVisited() {
		return visited;
	}

	public boolean getConnected() {
		return connected;
	}

	public int getName() {
		return this.name;
	}

	public double getDistance(){
		return this.distance;
	}
	
	public void setVisited(boolean newVisited){
		this.visited = newVisited;
	}

	public void setConnected(boolean newConnected){
		this.connected = newConnected;
	}

	public void setDistance(double newDistance){
		this.distance = newDistance;
	}

	public void setName(int newName){
		this.name = newName;
	}

	public Node getPredecessorNode() {
		return predecessorNode;
	}

	public void setPredecessorNode(Node predecessorNode) {
		this.predecessorNode = predecessorNode;
	}

	public LinkedList<Edge> getList() {
		return edgeList;
	}

	public void setList(LinkedList<Edge> newEdgeList) {
		this.edgeList = newEdgeList;
	}

	//implementation of Comparable
	@Override
	public int compareTo(Node nodeToCompare) {
		return Double.compare(this.distance, nodeToCompare.getDistance());
	}

}