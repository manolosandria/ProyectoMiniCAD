package minicad;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * @author Ángel Manuel Sandria Pérez
 */
public class Figuras extends ArrayList<Figura> implements Dibujable {

  @Override
  public void dibujar(Graphics g) {
    for (Figura figura : this) {
      figura.dibujar(g);
    }
  }

  public ArrayList<String> infoFiguras() {
    ArrayList cad = new ArrayList();
    for (Figura figura : this) {
      String linea = figura.getNombre() + ", " + figura.getPuntos();
      for (int i = 0; i < figura.getPuntos(); i++) {
        linea += (", " + figura.getX(i) + ", " + figura.getY(i));
      }
      linea += ", "+figura.getColor();
      cad.add(linea);
    }
    return cad;
  }

}
