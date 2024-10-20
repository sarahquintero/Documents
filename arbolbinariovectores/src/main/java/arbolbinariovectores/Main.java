package arbolbinariovectores;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("ARBOL N-ARIO REPRESENTADO CON LISTAS");

        ArbolBinario arbol = new ArbolBinario();

        int opcion = 0;
        Scanner input = new Scanner(System.in);

        while (opcion != 5) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar nodo.");
            System.out.println("2. Recorrido Inorden.");
            System.out.println("3. Recorrido Preorden.");
            System.out.println("4. Buscar Nodo.");
            System.out.println("5. Salir.");
            System.out.print("Selecciona una opción: ");

            opcion = input.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Agregar Nodo.");
                    System.out.print("Introduce el valor del nuevo nodo: ");
                    int nodoNuevo = input.nextInt();
                    arbol.agregar(nodoNuevo);
                    break;
                case 2:
                    System.out.println("Recorrer Inorden: ");
                    arbol.recorridoInorden();
                    break;
                case 3:
                    System.out.println("Recorrer Preorden: ");
                    arbol.recorridoPreorden();
                    break;
                case 4:
                    System.out.println("Buscar Nodo: ");
                    System.out.print("Introduce el valor del nuevo nodo: ");
                    int nodoBuscar = input.nextInt();
                    arbol.buscar(nodoBuscar);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}