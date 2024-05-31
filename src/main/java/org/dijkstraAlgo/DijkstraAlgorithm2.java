package org.dijkstraAlgo;


import java.util.Scanner;

public class DijkstraAlgorithm2 {

    private static void dijkstra(int[][] matriceAdj) {
        // S (le nombre de sommets dans le graphe)
        int S = matriceAdj.length;
        // utilisé pour suivre les sommets qui ont déjà été visités
        boolean[] visited = new boolean[S];
        // stocker les distances entre le sommet source et tous les autres sommets
        int[] distance = new int[S];
        int[] predecessor = new int[S];

        // absence de chemin connu entre le sommet source et les autres sommets
        // considérer toutes les distances comme inconnues
        for (int i = 0; i < S; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        // la distance entre le sommet source et lui-même est toujours nulle
        distance[0] = 0;
        predecessor[0] = 0;

        // Le tableau de sortie. dist[i] contiendra la plus courte distance de src à i.
        for (int i = 0; i < S; i++) {
            // trouver le sommet avec la plus petite distance
            int minSommet = findMinSommet(distance, visited);
            visited[minSommet] = true;
            // explorer les voisins
            for (int j = 0; j < S; j++) {
                if (matriceAdj[minSommet][j] != 0 && !visited[j] && distance[minSommet] != Integer.MAX_VALUE) {
                    int newDist = distance[minSommet] + matriceAdj[minSommet][j];
                    if (newDist < distance[j]) {
                        distance[j] = newDist;
                        predecessor[j] = minSommet;
                    }
                }
            }
        }

        /* Mettre à jour dist[j] uniquement s'il n'est pas dans visited, il y a une arête de u à v,
           et total le poids du chemin de src à v en passant par u est plus petit que la valeur actuelle de dist[v] */

        // Affichage des résultats
        printSolution(distance, predecessor);
    }

    private static void printSolution(int[] distance, int[] predecessor) {
        System.out.println("Sommet\tDistance\tChemin");

        for (int i = 1; i < distance.length; i++) {
            System.out.println(i + "\t" + distance[i] + "\t\t" + getPath(predecessor, i));
        }
    }

    private static String getPath(int[] predecessor, int current) {
        StringBuilder path = new StringBuilder();
        path.append(current);

        while (predecessor[current] != 0) {
            path.insert(0, predecessor[current] + " ");
            current = predecessor[current];
        }

        path.insert(0, "0 ");
        return path.toString();
    }

    // Une fonction utilitaire pour trouver le sommet avec le minimum
    // valeur de distance, à partir de l'ensemble des sommets pas encore
    // inclus dans l'arborescence du chemin le plus court
    private static int findMinSommet(int[] distance, boolean[] visited) {
        int minSommet = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && (minSommet == -1 || distance[i] < distance[minSommet])) {
                minSommet = i;
            }
        }
        return minSommet;
    }

    public static void main(String[] arg) {
        Scanner s = new Scanner(System.in);
        int S, e;

        do {
            System.out.println("Entrez le nombre de sommets : ");
            S = s.nextInt();
        } while (S < 0);

        do {
            System.out.println("Entrez le nombre des arêtes :");
            e = s.nextInt();
        } while (e < 0);

        int[][] matriceAdj = new int[S][S];

        // Boocle scanner MatriceAdj
        int weight, S1, S2;
        for (int i = 0; i < e; i++) {
            do {
                System.out.println("Entrez S1 :");
                S1 = s.nextInt();
            } while (S1 < 0);

            do {
                System.out.println("Entrez S2 :");
                S2 = s.nextInt();
            } while (S2 < 0);

            do {
                System.out.println("Entrez le poids des arêtes :");
                weight = s.nextInt();
            } while (weight < 0);

            matriceAdj[S1][S2] = weight;
            matriceAdj[S2][S1] = weight;
        }

        dijkstra(matriceAdj);

        s.close();
    }
}
