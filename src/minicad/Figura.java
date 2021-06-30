package minicad;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Ángel Manuel Sandria Pérez
 */
public class Figura implements Dibujable {

  private final int puntos;
  private final Color color;
  private final int[] puntosX;
  private final int[] puntosY;
  private final String nombre;

  public Figura(int puntos, Color color, String nombre) {
    this.puntos = puntos;
    this.color = color;
    this.puntosX = new int[puntos];
    this.puntosY = new int[puntos];
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public int getPuntos() {
    return puntos;
  }

  public String getColor() {
    int r = color.getRed();
    int g = color.getGreen();
    int b = color.getBlue();
    String hex = String.format("#%02X%02X%02X", r, g, b);
    return hex;
  }

  public boolean dibujado() {
    return !(puntosX[puntos - 1] == 0 && puntosY[puntos - 1] == 0);
  }

  public void añadirPunto(int x, int y, int contador) {
    puntosX[contador] = x;
    puntosY[contador] = y;
  }

  @Override
  public void dibujar(Graphics g) {
    g.setColor(color);
    if (dibujado()) {
      g.drawPolygon(puntosX, puntosY, puntos);
    }
  }

  public int getX(int i) {
    return puntosX[i];
  }

  public int getY(int i) {
    return puntosY[i];
  }

  public void cuadrado() {
    int x1 = puntosX[0], x2 = puntosX[1], y1 = puntosY[0], y2 = puntosY[1];
    int distX = x2 - x1;
    int distY = y2 - y1;
    if (distX < 0) {
      distX *= -1;
    }
    if (distY < 0) {
      distY *= -1;
    }
    if (distX > distY) {
      if (y2 - y1 > 0) {
        puntosY[1] = y1;
        puntosX[2] = x2;
        puntosX[3] = x1;
        puntosY[2] = y1 + distX;
        puntosY[3] = y1 + distX;
      } else {
        puntosY[1] = y1;
        puntosX[2] = x2;
        puntosX[3] = x1;
        puntosY[2] = y1 - distX;
        puntosY[3] = y1 - distX;
      }
    } else if (x2 - x1 < 0) {
      puntosX[1] = x1;
      puntosX[2] = x1 - distY;
      puntosX[3] = x1 - distY;
      puntosY[2] = y2;
      puntosY[3] = y1;
    } else {
      puntosX[1] = x1;
      puntosX[2] = x1 + distY;
      puntosX[3] = x1 + distY;
      puntosY[2] = y2;
      puntosY[3] = y1;
    }
  }

  public void rotacion(int angulo) {
    float rad = (float) Math.toRadians(angulo);
    for (int i = 0; i < puntos; i++) {
//      double radian = puntosX[i] * Math.sin(Math.toRadians(angulo));
//      radian += puntosY[i] * Math.cos(Math.toRadians(angulo));
//      puntosY[i] = (int) radian;
      puntosX[i] = (int) (puntosX[i] * Math.cos(rad) - puntosY[i] * Math.sin(rad));
      puntosY[i] = (int) (puntosX[i] * Math.sin(rad) + puntosY[i] * Math.cos(rad));
    }
  }

  public void traslacion(int desplazamientoX, int desplazamientoY) {
    for (int i = 0; i < puntos; i++) {
      puntosX[i] += desplazamientoX;
      puntosY[i] += desplazamientoY;
    }
  }

}
