package escribirleerarchivo;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EscribirLeerArchivo extends JFrame {

    JTextField campoTexto;
    JTextArea areaTexto;
    JButton botonEscribir, botonLeer, botonBorrar;
    private JTable tabla;
    private JScrollPane scrollPaneTabla;

    public EscribirLeerArchivo() {
        setTitle("Escribir y Leer Archivo");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
    
        campoTexto = new JTextField(15);
        botonEscribir = new JButton("Agregar y Escribir");
        botonLeer = new JButton("Leer Archivo");
        botonBorrar = new JButton("Borrar Archivo");
    
        panelSuperior.add(new JLabel("Escribe algo: "));
        panelSuperior.add(campoTexto);
        panelSuperior.add(botonEscribir);
        panelSuperior.add(botonLeer);
        panelSuperior.add(botonBorrar);
    
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaTexto);
    
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    
        // Inicializar la tabla y el scrollPane
        tabla = new JTable();
        scrollPaneTabla = new JScrollPane(tabla);
    
        add(scrollPaneTabla, BorderLayout.SOUTH); // Añadir la tabla en la parte inferior
    
        botonEscribir.addActionListener(e -> agregarYEscribir());
        botonLeer.addActionListener(e -> leerArchivo());
        botonBorrar.addActionListener(e -> borrarArchivo());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EscribirLeerArchivo().setVisible(true));
    }

    private void borrarArchivo() {
        try (FileWriter escritor = new FileWriter("datoslista.txt")) {
            escritor.write("");
            areaTexto.setText("");
            JOptionPane.showMessageDialog(this, "Archivo borrado.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al borrar el archivo");
            e.printStackTrace();
        }
    }

    private void agregarYEscribir() {
        String texto = campoTexto.getText();
        if (!texto.isEmpty()) {
            try {
                // Convertir los valores ingresados a una lista de enteros
                String[] strValores = texto.split(",");
                int[] valores = new int[strValores.length];
                for (int i = 0; i < strValores.length; i++) {
                    valores[i] = Integer.parseInt(strValores[i]);
                }
    
                // Escribir en el archivo la lista de valores
                escribirEnArchivo("datoslista.txt", valores);
                campoTexto.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Ingrese solo valores numéricos separados por comas");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El campo de texto está vacío.");
        }
    }

    private void escribirEnArchivo(String nombreArchivo, int[] valores) {
        try (FileWriter escritor = new FileWriter(nombreArchivo, true)) { // `true` para agregar al final del archivo
            for (int valor : valores) {
                escritor.write(valor + ",");
            }
            escritor.write("\n"); // Nueva línea para cada fila de valores
            JOptionPane.showMessageDialog(this, "Datos escritos en el archivo.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al escribir en el archivo");
            e.printStackTrace();
        }
    }

    private void leerArchivo() {
        try (BufferedReader lector = new BufferedReader(new FileReader("datoslista.txt"))) {
            String linea;
            areaTexto.setText("");
            while ((linea = lector.readLine()) != null) {
                areaTexto.append(linea + "\n");
            }
            mostrarMatrizEnTabla();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo");
            e.printStackTrace();
        }
    }
    

    private void mostrarMatrizEnTabla() {
        try (BufferedReader lector = new BufferedReader(new FileReader("datoslista.txt"))) {
            List<String[]> datos = new ArrayList<>();
            String linea;
    
            while ((linea = lector.readLine()) != null) {
                datos.add(linea.split(","));
            }
    
            String[] columnas = {"A", "B", "C"};
            Object[][] data = new Object[datos.size()][columnas.length];
            
            for (int i = 0; i < datos.size(); i++) {
                String[] fila = datos.get(i);
                for (int j = 0; j < columnas.length; j++) {
                    data[i][j] = (j < fila.length) ? fila[j] : ""; // Añadir valores a las columnas y evitar index out of bounds
                }
            }
    
            DefaultTableModel modelo = new DefaultTableModel(data, columnas);
            tabla.setModel(modelo);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar la tabla");
            e.printStackTrace();
        }
    }
    
}