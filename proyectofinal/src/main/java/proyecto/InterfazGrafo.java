package proyecto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class InterfazGrafo extends JFrame {
    private Grafo grafo;
    private JComboBox<String> tipoGrafoComboBox;
    private JTextField nodoOrigenTextField, nodoDestinoTextField, pesoTextField;
    private JTextArea resultadosTextArea;
    private JTable tablaGrafo;
    private JScrollPane scrollPaneTabla;

    public InterfazGrafo() {
        // Pedir al usuario el tamaño del grafo
        String tamanoStr = JOptionPane.showInputDialog(this, "Ingrese el tamaño del grafo (número de nodos):");
        int tamano = Integer.parseInt(tamanoStr);
    
        // Pedir al usuario si el grafo es dirigido o no
        int respuesta = JOptionPane.showConfirmDialog(this, "¿El grafo es dirigido?", "Tipo de Grafo", JOptionPane.YES_NO_OPTION);
        boolean dirigido = (respuesta == JOptionPane.YES_OPTION);
    
        // Inicializar el grafo con los valores dados por el usuario
        grafo = new Grafo(tamano, dirigido);
    
        setTitle("Administrador de Grafos");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
    
        //tipoGrafoComboBox = new JComboBox<>(new String[] { "Dirigido", "No Dirigido" });
        nodoOrigenTextField = new JTextField(5);
        nodoDestinoTextField = new JTextField(5);
        pesoTextField = new JTextField(5);
    
        JButton agregarAristaButton = new JButton("Agregar Arista");
        JButton cargarArchivoButton = new JButton("Cargar Archivo");
        JButton guardarResultadosButton = new JButton("Guardar Resultados");
        JButton ejecutarDijkstraButton = new JButton("Ejecutar Dijkstra");
        JButton ejecutarFloydWarshallButton = new JButton("Ejecutar Floyd-Warshall");
    
        resultadosTextArea = new JTextArea(15, 50);
        resultadosTextArea.setEditable(false);
        JScrollPane scrollPaneResultados = new JScrollPane(resultadosTextArea);
    
        //panelSuperior.add(new JLabel("Tipo de Grafo:"));
        //panelSuperior.add(tipoGrafoComboBox);
        panelSuperior.add(new JLabel("Nodo Origen:"));
        panelSuperior.add(nodoOrigenTextField);
        panelSuperior.add(new JLabel("Nodo Destino:"));
        panelSuperior.add(nodoDestinoTextField);
        panelSuperior.add(new JLabel("Peso:"));
        panelSuperior.add(pesoTextField);
        panelSuperior.add(agregarAristaButton);
        panelSuperior.add(cargarArchivoButton);
        panelSuperior.add(guardarResultadosButton);
        panelSuperior.add(ejecutarDijkstraButton);
        panelSuperior.add(ejecutarFloydWarshallButton);
    
        tablaGrafo = new JTable();
        scrollPaneTabla = new JScrollPane(tablaGrafo);
    
        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPaneResultados, BorderLayout.CENTER);
        add(scrollPaneTabla, BorderLayout.SOUTH);
    
        agregarAristaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarArista();
            }
        });
    
        cargarArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArchivo();
            }
        });
    
        guardarResultadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarResultados();
            }
        });
    
        ejecutarDijkstraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarDijkstra();
            }
        });
    
        ejecutarFloydWarshallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarFloydWarshall();
            }
        });
    }    

    private void agregarArista() {
        try {
            int origen = Integer.parseInt(nodoOrigenTextField.getText().trim());
            int destino = Integer.parseInt(nodoDestinoTextField.getText().trim());
            int peso = Integer.parseInt(pesoTextField.getText().trim());

            if (origen < 0 || destino < 0 || peso < 0) {
                throw new IllegalArgumentException("Los valores no pueden ser negativos");
            }

            grafo.agregarArista(origen, destino, peso);
            resultadosTextArea.append("Arista agregada: " + origen + " -> " + destino + " con peso " + peso + "\n");
            actualizarTablaGrafo();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa valores numéricos válidos.", "Error de Formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Valor", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarArchivo() {
        String nombreArchivo = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo a cargar:");
        grafo = new Grafo(10, tipoGrafoComboBox.getSelectedItem().equals("Dirigido"));
        grafo.cargarDesdeArchivo(nombreArchivo);
        resultadosTextArea.append("Grafo cargado desde archivo: " + nombreArchivo + "\n");
        actualizarTablaGrafo();
    }

    private void guardarResultados() {
        String nombreArchivo = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo para guardar:");
        int[][] resultados = grafo.floydWarshall(); // Ejemplo con Floyd-Warshall
        grafo.guardarResultadosEnArchivo(nombreArchivo, resultados);
        resultadosTextArea.append("Resultados guardados en el archivo: " + nombreArchivo + "\n");
    }

    private void ejecutarDijkstra() {
        int nodoOrigen = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el nodo origen para Dijkstra:"));
        int[] distancias = grafo.dijkstra(nodoOrigen);
        resultadosTextArea.append("Resultados de Dijkstra desde el nodo " + nodoOrigen + ":\n");
        for (int i = 0; i < distancias.length; i++) {
            resultadosTextArea.append("Nodo " + nodoOrigen + " a Nodo " + i + " : " + distancias[i] + "\n");
        }
    }

    private void ejecutarFloydWarshall() {
        int[][] distancias = grafo.floydWarshall();
        resultadosTextArea.append("Resultados de Floyd-Warshall:\n");
        for (int i = 0; i < distancias.length; i++) {
            for (int j = 0; j < distancias[i].length; j++) {
                if (distancias[i][j] == Integer.MAX_VALUE) {
                    resultadosTextArea.append("INF ");
                } else {
                    resultadosTextArea.append(distancias[i][j] + " ");
                }
            }
            resultadosTextArea.append("\n");
        }
    }

    private void actualizarTablaGrafo() {
        String[] columnas = new String[grafo.getNumNodos()];
        for (int i = 0; i < grafo.getNumNodos(); i++) {
            columnas[i] = String.valueOf(i);
        }

        Object[][] data = new Object[grafo.getNumNodos()][grafo.getNumNodos()];
        for (int i = 0; i < grafo.getNumNodos(); i++) {
            for (int j = 0; j < grafo.getNumNodos(); j++) {
                data[i][j] = grafo.getMatrizAdyacencia()[i][j];
            }
        }

        DefaultTableModel modelo = new DefaultTableModel(data, columnas);
        tablaGrafo.setModel(modelo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGrafo().setVisible(true);
            }
        });
    }
}
