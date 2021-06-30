package minicad;

/**
 * @author Ángel Manuel Sandria Pérez
 */
public class MiniCad {

  public static void main(String[] args) {
    Figuras figuras = new Figuras();
    VentanaCad ventana = new VentanaCad(figuras);
    OyenteFiguras oyente = new OyenteFiguras(figuras, ventana);
    ventana.addEventos(oyente);
    ventana.setSize(1000, 600);
    ventana.setLocation(100, 100);
    ventana.setVisible(true);
  }
  
}
