package algorithm;


public class Edge {

	private double weight;
	private Node startNode;
	private Node endNode;

	/**
	 * L'oggetto Edge rappresenta l'arco tra due nodi. Sono richiesti 3 parametri:
	 * @param weight costo dell'arco
	 * @param startNode nodo da cui parte l'arco
	 * @param endNode nodo su cui arriva l'arco
	 */

	public Edge(double weight, Node newStartNode, Node newEndNode) {
		this.weight = weight;
		this.startNode = newStartNode;
		this.endNode = newEndNode;
	}

	/**
	 * Metodo per stampare le informazioni dell'oggetto Edge
	 * @return Stringa contenente tutti i parametri definiti nell'oggetto Edge.
	 */
	@Override
	public String toString() {
		return "Edge{" +
				"weight=" + weight +
				", startNode=" + startNode +
				", endNode=" + endNode +
				'}';
	}

	//getter and setter
	public double getWeight() {
		return weight;
	}
 
	public void setWeight(double weight) {
		this.weight = weight;
	}
 
	public Node getStartNode() {
		return startNode;
	}
 
	public void setStartNode(Node newStartNode) {
		this.startNode = newStartNode;
	}
 
	public Node getEndNode() {
		return endNode;
	}
 
	public void setEndNode(Node newEndNode) {
		this.endNode = newEndNode;
	}
}
