package minicad;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * @author Ángel Manuel Sandria Pérez
 */
public class OyenteFiguras extends MouseAdapter implements ActionListener {

  private final Figuras figuras;
  private final VentanaCad ventana;
  private final Pizarra pizarra;
  private int contador;
  private String figura;
  private int puntos;
  Color color;

  public OyenteFiguras(Figuras figuras, VentanaCad ventana) {
    this.figuras = figuras;
    this.ventana = ventana;
    this.pizarra = ventana.pizarra;
    figura = "linea";
    puntos = 2;
    contador = 0;
    color = Color.BLACK;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    dibujarFigura(e.getX(), e.getY());
    pizarra.repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JComponent source = (JComponent) e.getSource();
    if (contador > 0) {
      figuras.remove(figuras.get(figuras.size() - 1));
      contador = 0;
      mostrarFiguras();
    }
    switch (source.getName()) {
      case "linea" -> {
        puntos = 2;
        figura = "linea";
      }
      case "triangulo" -> {
        puntos = 3;
        figura = "triangulo";
      }
      case "cuadrado" -> {
        puntos = 4;
        figura = "cuadrado";
      }
      case "rectangulo" -> {
        puntos = 4;
        figura = "rectangulo";
      }
      case "poligono" -> {
        puntos = Math.max(3, Integer.parseInt(JOptionPane.showInputDialog("Introduzca número de vértices")));
        figura = "poligono";
      }
      case "abrir" -> {
        abrirFiguras();
        mostrarFiguras();
      }
      case "guardar" -> {
        guardarFiguras();
      }
      case "color" ->
        color = JColorChooser.showDialog(null, "Seleccione un color", color);
      case "nuevo" -> {
        figuras.clear();
        contador = 0;
        mostrarFiguras();
      }
      default -> {
        try {
          int transformada = Integer.parseInt(JOptionPane.showInputDialog("Transformar figura número:")) - 1;
          if (transformada != 0) {
            switch (source.getName()) {
              case "rotar" -> {
                int angulo = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el ángulo"));
                figuras.get(transformada).rotacion(angulo);
              }
              case "traslacion" -> {
                int desplazamientoX = Integer.parseInt(JOptionPane.showInputDialog("Introduzca desplazamiento horizontal"));
                int desplazamientoY = Integer.parseInt(JOptionPane.showInputDialog("Introduzca desplazamiento vertical"));
                figuras.get(transformada).traslacion(desplazamientoX, desplazamientoY);
              }
            }
          }
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(ventana, "Por favor introduzca otro valor");
        }
      }
    }
    pizarra.repaint();
  }

  private void dibujarFigura(int x, int y) {
    if (contador == 0) {
      figuras.add(new Figura(puntos, color, figura));
      figuras.get(figuras.size() - 1).añadirPunto(x, y, contador);
      System.out.println("Figura agregada");
    } else if (contador < puntos) {
      Figura actual = figuras.get(figuras.size() - 1);
      System.out.println(x + " " + y);
      actual.añadirPunto(x, y, contador);
      System.out.println("Punto agregado");
      if (figura.equals("cuadrado") && contador == 1) {
        figuras.get(figuras.size() - 1).cuadrado();
        contador = puntos - 1;
      }
      if (contador == puntos - 1) {
        contador = -1;
        System.out.println("Dibujado");
        mostrarFiguras();
      }
    }
    contador++;
  }

  public void mostrarFiguras() {
    ArrayList<String> info = figuras.infoFiguras();
    String cad = "";
    int i = 1;
    for (String linea : info) {
      cad += (i + ".- " + linea + "\n");
      i++;
    }
    ventana.addFigura(cad);
  }

  public void guardarFiguras() {
    JFileChooser seleccion = new JFileChooser();
    int opcion = seleccion.showSaveDialog(ventana);
    if (opcion == JFileChooser.APPROVE_OPTION) {
      grabarArchivo(seleccion.getSelectedFile(), figuras.infoFiguras());
    }
  }

  public static void grabarArchivo(File archivo, ArrayList<String> lineas) {
    try {
      FileWriter flujo = new FileWriter(archivo);
      BufferedWriter buffer = new BufferedWriter(flujo);
      for (String linea : lineas) {
        buffer.write(linea);
        buffer.newLine();
      }
      buffer.close();
      flujo.close();
    } catch (IOException ex) {
      System.out.println("¡Error de escritura! " + ex);
      System.exit(-1);
    }
  }

  public void abrirFiguras() {
    JFileChooser seleccion = new JFileChooser();
    int opcion = seleccion.showOpenDialog(ventana);
    if (opcion == JFileChooser.APPROVE_OPTION) {
      File archivo = seleccion.getSelectedFile();
      ArrayList<String> lineas = leerArchivo(archivo);
      figuras.clear();

      for (String linea : lineas) {
        String figura[] = linea.split(", ");
        this.figura = figura[0];
        puntos = Integer.parseInt(figura[1]);
        String color = figura[(puntos * 2) + 2];
        this.color = new Color(Integer.valueOf(color.substring(1, 3), 16), Integer.valueOf(color.substring(3, 5), 16), Integer.valueOf(color.substring(5, 7), 16));
        for (int j = 2; j <= puntos * 2; j += 2) {
          int x = Integer.parseInt(figura[j]);
          int y = Integer.parseInt(figura[j + 1]);
          dibujarFigura(x, y);
          if (this.figura.equals("cuadrado") && j == 4) {
            System.out.println(figura[7]+" "+figura[9]+"verga");
            figuras.get(figuras.size() - 1).añadirPunto(Integer.parseInt(figura[6]), Integer.parseInt(figura[7]), 2);
            figuras.get(figuras.size() - 1).añadirPunto(Integer.parseInt(figura[8]), Integer.parseInt(figura[9]), 3);
            j = puntos * 2;
          }
        }
      }

    }
  }

  public static ArrayList<String> leerArchivo(File archivo) {
    ArrayList<String> lineas = new ArrayList<>();
    try {
      FileReader flujo = new FileReader(archivo);
      BufferedReader buffer = new BufferedReader(flujo);
      String linea = buffer.readLine();
      while (linea != null) {
        lineas.add(linea);
        linea = buffer.readLine();
      }
      buffer.close();
      flujo.close();
    } catch (IOException ex) {
      System.out.println("Error de archivo" + ex);
      System.exit(-1);
    }
    return lineas;
  }

}
