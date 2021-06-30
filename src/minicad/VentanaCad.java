package minicad;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JFrame;

/**
 * @author Ángel Manuel Sandria Pérez
 */
public class VentanaCad extends JFrame {

  public Pizarra pizarra;
  private Figuras figuras;

  public VentanaCad(Figuras figuras) {
    initComponents();
    addPizarra(figuras);
  }

  private void addPizarra(Figuras figuras) {
    pizarra = new Pizarra(figuras);
    add(pizarra, BorderLayout.CENTER);
    pizarra.setFocusable(true);
  }

  public void addFigura(String figuras){
    scrollDibujados.setText(figuras);
  }
  
  public void addEventos(ActionListener oyente) {
    botonLinea.addActionListener(oyente);
    botonLinea.setName("linea");
    botonTriangulo.addActionListener(oyente);
    botonTriangulo.setName("triangulo");
    botonCuadrado.addActionListener(oyente);
    botonCuadrado.setName("cuadrado");
    botonRectangulo.addActionListener(oyente);
    botonRectangulo.setName("rectangulo");
    botonPoligono.addActionListener(oyente);
    botonPoligono.setName("poligono");
    opcionAbrir.addActionListener(oyente);
    opcionAbrir.setName("abrir");
    opcionGuardar.addActionListener(oyente);
    opcionGuardar.setName("guardar");
    rotacion.addActionListener(oyente);
    rotacion.setName("rotar");
    traslacion.addActionListener(oyente);
    traslacion.setName("traslacion");
    opcionNuevo.addActionListener(oyente);
    opcionNuevo.setName("nuevo");
    opcionColor.addActionListener(oyente);
    pizarra.addEventos((MouseAdapter) oyente);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panelEste = new javax.swing.JPanel();
    panelDibujados = new javax.swing.JScrollPane();
    scrollDibujados = new javax.swing.JTextArea();
    panelOeste = new javax.swing.JPanel();
    panelBotones = new javax.swing.JPanel();
    botonLinea = new javax.swing.JButton();
    botonTriangulo = new javax.swing.JButton();
    botonCuadrado = new javax.swing.JButton();
    botonRectangulo = new javax.swing.JButton();
    botonPoligono = new javax.swing.JButton();
    barraMenu = new javax.swing.JMenuBar();
    menuArchivo = new javax.swing.JMenu();
    opcionNuevo = new javax.swing.JMenuItem();
    opcionAbrir = new javax.swing.JMenuItem();
    opcionGuardar = new javax.swing.JMenuItem();
    menuEdicion = new javax.swing.JMenu();
    menuTransformacion = new javax.swing.JMenu();
    rotacion = new javax.swing.JMenuItem();
    traslacion = new javax.swing.JMenuItem();
    opcionColor = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Proyecto MiniCad - Sandria Pérez Ángel Manuel");

    panelEste.setLayout(new java.awt.GridLayout(1, 0));

    scrollDibujados.setEditable(false);
    scrollDibujados.setColumns(20);
    scrollDibujados.setRows(5);
    panelDibujados.setViewportView(scrollDibujados);

    panelEste.add(panelDibujados);

    getContentPane().add(panelEste, java.awt.BorderLayout.LINE_END);

    panelOeste.setLayout(new java.awt.BorderLayout());

    panelBotones.setLayout(new java.awt.GridLayout(0, 1));

    botonLinea.setText("Línea");
    panelBotones.add(botonLinea);

    botonTriangulo.setText("Triangulo");
    panelBotones.add(botonTriangulo);

    botonCuadrado.setText("Cuadrado");
    panelBotones.add(botonCuadrado);

    botonRectangulo.setText("Rectangulo");
    panelBotones.add(botonRectangulo);

    botonPoligono.setText("Polígono");
    panelBotones.add(botonPoligono);

    panelOeste.add(panelBotones, java.awt.BorderLayout.NORTH);

    getContentPane().add(panelOeste, java.awt.BorderLayout.LINE_START);

    menuArchivo.setText("Archivo");

    opcionNuevo.setText("Nuevo");
    menuArchivo.add(opcionNuevo);

    opcionAbrir.setText("Abrir Archivo");
    menuArchivo.add(opcionAbrir);

    opcionGuardar.setText("Guardar Archivo");
    menuArchivo.add(opcionGuardar);

    barraMenu.add(menuArchivo);

    menuEdicion.setText("Editar");

    menuTransformacion.setText("Transformar");

    rotacion.setText("Rotar");
    menuTransformacion.add(rotacion);

    traslacion.setText("Traslación");
    traslacion.setName("traslacion"); // NOI18N
    menuTransformacion.add(traslacion);

    menuEdicion.add(menuTransformacion);

    opcionColor.setText("Color");
    opcionColor.setName("color"); // NOI18N
    menuEdicion.add(opcionColor);

    barraMenu.add(menuEdicion);

    setJMenuBar(barraMenu);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuBar barraMenu;
  private javax.swing.JButton botonCuadrado;
  private javax.swing.JButton botonLinea;
  private javax.swing.JButton botonPoligono;
  private javax.swing.JButton botonRectangulo;
  private javax.swing.JButton botonTriangulo;
  private javax.swing.JMenu menuArchivo;
  private javax.swing.JMenu menuEdicion;
  private javax.swing.JMenu menuTransformacion;
  private javax.swing.JMenuItem opcionAbrir;
  private javax.swing.JMenuItem opcionColor;
  private javax.swing.JMenuItem opcionGuardar;
  private javax.swing.JMenuItem opcionNuevo;
  private javax.swing.JPanel panelBotones;
  private javax.swing.JScrollPane panelDibujados;
  private javax.swing.JPanel panelEste;
  private javax.swing.JPanel panelOeste;
  private javax.swing.JMenuItem rotacion;
  private javax.swing.JTextArea scrollDibujados;
  private javax.swing.JMenuItem traslacion;
  // End of variables declaration//GEN-END:variables
}
