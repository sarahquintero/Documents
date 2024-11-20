package proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Grafo {
    private int[][] matrizAdyacencia;
    private int numNodos;
    private boolean dirigido;
    private int[] predecesores; // Arreglo de predecesores

    public Grafo(int tamano, boolean dirigido) {
        this.matrizAdyacencia = new int[tamano][tamano];
        this.numNodos = tamano;
        this.dirigido = dirigido;
    }

    public void agregarArista(int origen, int destino, int peso) {
        matrizAdyacencia[origen][destino] = peso;
        if (!dirigido) {
            matrizAdyacencia[destino][origen] = peso;
        }
    }

    public void eliminarArista(int origen, int destino) {
        matrizAdyacencia[origen][destino] = 0;
        if (!dirigido) {
            matrizAdyacencia[destino][origen] = 0;
        }
    }

    public void mostrarMatrizAdyacencia() {
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                System.out.print(matrizAdyacencia[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] dijkstra(int inicio) {
        int[] distancias = new int[numNodos];
        predecesores = new int[numNodos];
        boolean[] visitados = new boolean[numNodos];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        Arrays.fill(predecesores, -1);
        distancias[inicio] = 0;

        for (int i = 0; i < numNodos; i++) {
            int nodoMin = minDistancia(distancias, visitados);
            visitados[nodoMin] = true;

            for (int j = 0; j < numNodos; j++) {
                if (!visitados[j] && matrizAdyacencia[nodoMin][j] != 0 && distancias[nodoMin] != Integer.MAX_VALUE
                        && distancias[nodoMin] + matrizAdyacencia[nodoMin][j] < distancias[j]) {
                    distancias[j] = distancias[nodoMin] + matrizAdyacencia[nodoMin][j];
                    predecesores[j] = nodoMin; // Actualiza el predecesor
                }
            }
        }

        return distancias;
    }

    private int minDistancia(int[] distancias, boolean[] visitados) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < numNodos; i++) {
            if (!visitados[i] && distancias[i] <= min) {
                min = distancias[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public int[][] floydWarshall() {
        int[][] dist = new int[numNodos][numNodos];

        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else if (matrizAdyacencia[i][j] != 0) {
                    dist[i][j] = matrizAdyacencia[i][j];
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < numNodos; k++) {
            for (int i = 0; i < numNodos; i++) {
                for (int j = 0; j < numNodos; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    public void cargarDesdeArchivo(String nombreArchivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String tipoGrafo = lector.readLine().trim();
            dirigido = tipoGrafo.equalsIgnoreCase("dirigido");

            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(" ");
                int origen = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt(partes[1]);
                int peso = Integer.parseInt(partes[2]);

                agregarArista(origen, destino, peso);
            }
            JOptionPane.showMessageDialog(null, "Grafo cargado desde archivo.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el grafo desde el archivo.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato de archivo incorrecto.");
            e.printStackTrace();
        }
    }

    public void guardarResultadosEnArchivo(String nombreArchivo, int[] distancias, int origen) {
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            escritor.write("Distancias mínimas desde el nodo " + origen + ":\n");
            for (int i = 0; i < distancias.length; i++) {
                String linea = "Nodo " + origen + " a Nodo " + i + " : " + distancias[i];
                System.out.println(linea); // Línea de depuración
                escritor.write(linea + "\n");
            }
            JOptionPane.showMessageDialog(null, "Resultados guardados en el archivo.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los resultados en el archivo.");
            e.printStackTrace();
        }
    }

    public void guardarResultadosEnArchivo(String nombreArchivo, int[][] distancias) {
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            escritor.write("Matriz de distancias mínimas:\n");
            for (int i = 0; i < distancias.length; i++) {
                for (int j = 0; j < distancias[i].length; j++) {
                    String valor = (distancias[i][j] == Integer.MAX_VALUE) ? "INF " : distancias[i][j] + " ";
                    System.out.print(valor); // Línea de depuración
                    escritor.write(valor);
                }
                System.out.println(); // Línea de depuración
                escritor.write("\n");
            }
            JOptionPane.showMessageDialog(null, "Resultados guardados en el archivo.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los resultados en el archivo.");
            e.printStackTrace();
        }
    }

    public int[][] getMatrizAdyacencia() {
        return matrizAdyacencia;
    }

    public void setMatrizAdyacencia(int[][] matrizAdyacencia) {
        this.matrizAdyacencia = matrizAdyacencia;
    }

    public int getNumNodos() {
        return numNodos;
    }

    public void setNumNodos(int numNodos) {
        this.numNodos = numNodos;
    }

    public boolean isDirigido() {
        return dirigido;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }

    public int[] getPredecesores() {
        return predecesores;
    }
}
