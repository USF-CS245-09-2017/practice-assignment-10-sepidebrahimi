//Sepid Ebrahimi ,CS 245, Section 1

import java.util.*;

public class GraphImplementation implements Graph {

    public int[][] adjMatrix;
    public int vertices;
    public int edges;

    public GraphImplementation(int vertices) {
        adjMatrix = new int[vertices][vertices]; //each cell set to zero
        this.vertices = vertices;
        this.edges = 0;
    }

    public void addEdge(int v1, int v2) {
        adjMatrix[v1][v2] = 1; //set the weight to 1
        edges++; // increment the number of edges
    }

    public List<Integer> topologicalSort() { //for directed graph
        int[][] incident = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                incident[i][j] = adjMatrix[i][j];
            }
        }
        //update indices array from adjMatrix
        //pick the vertex with incident = 0

        Set<Integer> visited = new HashSet<Integer>(); // all false at the start
        List<Integer> values = new ArrayList<>();

        boolean flag; //tell us if the vertex has been used

        for (int f = 0; f < vertices; f++) {
            for (int i = 0; i < vertices; i++) {
                flag = true;

                for (int j = 0; j < vertices; j++) {
                    if (incident[j][i] > 0) {
                        flag = false;
                        break;
                    }
                }

                if (flag && !visited.contains(i)) {
                    visited.add(i);
                    values.add(i);

                    for (int k = 0; k < vertices; k++) {
                        incident[i][k] = 0;
                    }

                    break;
                }
            }
        }

        return values;
    }

    public int[] neighbors(int vertex) {
        int count = 0;
        int neighborCount = 0;

        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[vertex][i] > 0) {
                count++;
            }
        }

        int[] neighbors = new int[count];

        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[vertex][i] > 0) {
                neighbors[neighborCount++] = i;
            }
        }

        return neighbors;
    }
}