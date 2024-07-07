package algorithm;

import java.util.LinkedList;
import java.util.Random;

/**
 * L'oggetto Graph definisce il grafo, e lo costruisce in maniera randomica.
 */
public class Graph {
    private final boolean verbose;
    private int numberNodes;
    private double density;
    private boolean unitCost;
    private int maxCostForEdge;
    private boolean orientedGraph;
    private LinkedList<Integer> insertedNodes = new LinkedList<Integer>();
    public Node[] nodes;
    private boolean allNodesConnected = false;
    private int randomNodeStart;
    private int randomNodeDestination;

    public Graph(int newNumberNodes, double density, boolean unitCost, int maxCostForEdge, boolean orientedGraph, boolean verbose) {
        this.numberNodes = newNumberNodes;
        this.density = density;
        this.unitCost = unitCost;
        this.maxCostForEdge = maxCostForEdge;
        this.orientedGraph = orientedGraph;
        this.verbose = verbose;
        nodes = new Node[numberNodes];
    }

    /**
     * Il metodo makeGraph costruisce il grafo. Dato il numero di nodi, la densità degli archi, il peso degli archi
     * (unitario, o randomico fino ad un valore prestabilito) e la tipologia di arco (orientato oppure non orientato).
     * Vengono generati degli archi in modo randomico (da un nodo random, fino ad un'altro random,
     * e con un peso random diverso da 0, oppure unitario), finchè la densità prestabilita non viene raggiunta.
     * Questo metodo si preoccupa inoltre di non creare archi doppi, per questo prima di aggiungere un arco è necessario
     * verificare che questo non sia gia presente.
     * Se viene richiesta la creazione di un grafo con densità troppo elevata, ovvero che non ci sono abbastanza nodi
     * per poter creare il numero richiesto di archi, allora il metodo continuerà in loop. Quindi è stato inserito un
     * counter, che se rileva un numero di cicli, ben al di sopra di un valore ideale, blocca la creazione del grafo e
     * termina il programma.
     * È presente anche una variabile verbose che se settata a true ci permette di mostrare la creazione del grafico
     * nella console
     *
     */

    public void createGraph(){
        int cost = 1;
        int counterEdges = 0;

        //per creare ed inserire tutti i nodi dentro la linkedlist dei nodi ancora non usati
        for(int i=0; i< numberNodes; i++){
            nodes[i] = new Node(i);
        }
        double partialDensity=0;

        firstRun();
        counterEdges++;

        //tutte le iterazioni, tranne la prima
        while(!allNodesConnected){
            //nodo sicuramente già connesso
            randomNodeStart = new Random(System.currentTimeMillis()).nextInt(insertedNodes.size());
            //nodo da aggiungere
            randomNodeDestination = new Random(System.currentTimeMillis()).nextInt(nodes.length);
            while(nodes[randomNodeDestination].getConnected()){ //se il nodo è già stato connesso
                randomNodeDestination = (randomNodeDestination+1) % nodes.length; //aumento di 1 e faccio il modulo della somma
            }
            if (!unitCost) { //scelgo un costo randomico
                cost = new Random(System.currentTimeMillis()).nextInt(maxCostForEdge);
            }

            //creo l'arco dal nodo di partenza a quello di arrivo
            Edge edge = new Edge(cost, nodes[insertedNodes.get(randomNodeStart)], nodes[randomNodeDestination]);
            if(!orientedGraph) { //se il grafo è orientato
                nodes[insertedNodes.get(randomNodeStart)].addNeighbour(edge); //aggiungo il collegamento al nodo
                nodes[randomNodeDestination].setConnected(true); //setto il setConnected a true
                insertedNodes.add(randomNodeDestination); //inserisco il nodo di destinazione sulla lista dei nodi collegati
                if(verbose) { 
                    System.out.println(insertedNodes.get(randomNodeStart) + " connected to " + randomNodeDestination +
                        " (unidirectional) with cost " + cost);
                }
            }else{ //se il grafo non è orientato
                Edge reversedEdge = new Edge(cost, nodes[randomNodeDestination], nodes[insertedNodes.get(randomNodeStart)]);
                nodes[insertedNodes.get(randomNodeStart)].addNeighbour(edge); //aggiungo il collegamento al nodo
                nodes[randomNodeDestination].addNeighbour(reversedEdge); //aggiungo il collegamento inverso al nodo
                nodes[randomNodeDestination].setConnected(true); //setto il setConnected a true
                insertedNodes.add(randomNodeDestination); //inserisco il nodo di destinazione sulla lista dei nodi collegati
                if(verbose) {
                    System.out.println(insertedNodes.get(randomNodeStart) + " connected to " + randomNodeDestination +
                        " (bidirectional) with cost " + cost);
                }
            }
            counterEdges++;
            partialDensity = (double)counterEdges/numberNodes;

            //controllo se tutti i nodi sono collegati
            allNodesConnected=true;
            for(int i=0; i<nodes.length; i++){
                if(nodes[i].getConnected() == false){
                    allNodesConnected=false;
                }
            }
        }

        //aggiungo archi aggiuntivi che però rispettino il vincolo di densità
        while(partialDensity <= density){ //vincolo di densità
            boolean duplicate = false; //variabile che mi indica se esiste già un arco tra i due nodi
            int randomNodeStart = new Random(System.currentTimeMillis()).nextInt(nodes.length);
            int randomNodeDestination = new Random(System.currentTimeMillis()).nextInt(nodes.length);

            while(randomNodeStart == randomNodeDestination){ //se il nodo è lo stesso
                randomNodeDestination = new Random(System.currentTimeMillis()).nextInt(nodes.length);
            }

            if (!unitCost) { //scelgo un costo randomico
                cost = new Random(System.currentTimeMillis()).nextInt(maxCostForEdge);
            }

            if(!orientedGraph){ //se il grafo è orientato
                for(int i=0; i<nodes[randomNodeStart].getList().size(); i++){
                    if(nodes[randomNodeStart].getList().get(i).getEndNode() == nodes[randomNodeDestination]){
                        duplicate = true;
                    }
                }
                if(!duplicate){ //se l'arco non è doppio
                    Edge edge = new Edge(cost, nodes[randomNodeStart], nodes[randomNodeDestination]);
                    nodes[randomNodeStart].addNeighbour(edge);
                    counterEdges++;
                    if(verbose) { 
                        System.out.println(randomNodeStart + " connected to " + randomNodeDestination +
                            " (unidirectional) with cost " + cost);
                    }
                }
            } else{ //se il grafo non è orientato
                for(int i=0; i< nodes[randomNodeStart].getList().size(); i++){
                    if(nodes[randomNodeStart].getList().get(i).getEndNode() == nodes[randomNodeDestination]){
                        duplicate = true;
                    }
                }
                if(!duplicate){
                    Edge edge = new Edge(cost, nodes[randomNodeStart], nodes[randomNodeDestination]);
                    nodes[randomNodeStart].addNeighbour(edge);
                    nodes[randomNodeDestination].addNeighbour(edge);
                    counterEdges++;
                    if(verbose) { 
                        System.out.println(randomNodeStart + " connected to " + randomNodeDestination +
                            " (bidirectional) with cost " + cost);
                    }
                }
            }
            partialDensity = (double)counterEdges/numberNodes;
        }

        if(verbose) { //stampa delle imformazioni finali
            System.out.println("Density Ratio: " + (double) counterEdges / numberNodes);
            System.out.println("N. of Nodes: " + numberNodes);
            System.out.println("N. of Edges: " + counterEdges);
            System.out.println();
        }
    }

    private void firstRun(){ //solo prima iterazione
        int cost = 1;
        randomNodeStart = new Random(System.currentTimeMillis()).nextInt(nodes.length);
        nodes[randomNodeStart].setConnected(true);
        insertedNodes.add(randomNodeStart);
        do{
            randomNodeDestination = new Random(System.currentTimeMillis()).nextInt(nodes.length);
        }while(nodes[randomNodeDestination].getConnected());

        if (!unitCost) { //scelgo un costo randomico
            cost = new Random(System.currentTimeMillis()).nextInt(maxCostForEdge);
        }
        //costruisco l'arco dal nodo iniziale al nodo di destinazione
        Edge edge = new Edge(cost, nodes[randomNodeStart], nodes[randomNodeDestination]);
        if(orientedGraph) { //se il grafo è orientato
            nodes[randomNodeStart].addNeighbour(edge); //aggiungo il collegamento al nodo
            nodes[randomNodeDestination].setConnected(true); //setto il setConnected a true
            insertedNodes.add(randomNodeDestination); //inserisco il nodo di destinazione sulla lista dei nodi collegati
            if(verbose) {
                System.out.println(randomNodeStart + " connected to " + randomNodeDestination + " (unidirectional) with cost " + cost);
            }
        }else{
            //se il grafo non è orientato
            Edge reversedEdge = new Edge(cost, nodes[randomNodeDestination], nodes[randomNodeStart]);
            nodes[randomNodeStart].addNeighbour(edge); //aggiungo il collegamento al nodo
            nodes[randomNodeDestination].addNeighbour(reversedEdge); //aggiungo il collegamento inverso al nodo
            nodes[randomNodeDestination].setConnected(true);//setto il setConnected a true
            insertedNodes.add(randomNodeDestination); //inserisco il nodo di destinazione sulla lista dei nodi collegati
            if(verbose) {
                System.out.println(randomNodeStart + " connected to " + randomNodeDestination + " (bidirectional) with cost " + cost);
            }
        }
    }

    /**
     * getAdjNode è un metodo che restituisce il nodo nella posizione i nella lista di adiacenza.
     * @param i numero del nodo
     * @return oggetto nodo nella posizione iesima della lista.
     */
    public Node getAdjNode(int i){
        if(nodes.length==0){
            return null;
        }
        return nodes[i];
    }

    /**
     * Metodo per stampare le informazioni dell'oggetto Graph
     *
     * @return Stringa contenente tutti i parametri definiti nell'oggetto Graph.
     */
    @Override
    public String toString() {
        return "Graph{" +
                "verbose=" + verbose +
                ", numberNodes=" + numberNodes +
                ", density=" + density +
                ", unitCost=" + unitCost +
                ", maxCostForEdge=" + maxCostForEdge +
                ", orientedGraph=" + orientedGraph +
                ", nodes=" + nodes.toString() +
                '}';
    }
}
