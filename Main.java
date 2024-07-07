import java.util.Scanner;

import view.Printer;

public class Main {

    public static int numberNodes = 20;
    public static boolean unitCost = false;
    public static boolean orientedGraph = true;
    public static int maxCostForEdge = 10;
    public static double density = 2; //The density represent the ratio m/n respectively the number of nodes and the number of edges.
    public static boolean verbose = true;

    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        // Starting the GUI
        getInput();
        new Printer(numberNodes, unitCost, orientedGraph, maxCostForEdge, density, verbose);
    }

    public static void getInput(){ //ricevo i parametri
        System.out.println("Vuoi usare i parametri di default?");
        Boolean defaultParameters = SCANNER.nextBoolean();
        if(!defaultParameters){
            System.out.println("Inserisci il numero di nodi: ");
            numberNodes = SCANNER.nextInt();
            System.out.println("Vuoi che tutti gli archi hanno costo unitario? (true o false) ");
            unitCost = SCANNER.nextBoolean();
            System.out.println("Vuoi un grafo non orientato? (false: archi unilaterali. true: archi bilaterali) ");
            orientedGraph = SCANNER.nextBoolean();
            if(!unitCost){
                System.out.println("Inserisci il massimo costo che può avere un arco: ");
                maxCostForEdge = SCANNER.nextInt();
            }
            System.out.println("Inserisci la densità: ");
            density = SCANNER.nextDouble();
            System.out.println("Vuoi vedere i collegamenti tra i nodi? (true o false) ");
            verbose = SCANNER.nextBoolean();
        }
    }
}