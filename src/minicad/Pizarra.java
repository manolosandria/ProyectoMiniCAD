package minicad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

/**
 * @author Ángel Manuel Sandria Pérez
 */
public class Pizarra extends JPanel {

  private final Figuras figuras;

  public Pizarra(Figuras figuras) {
    setBackground(Color.WHITE);
    this.figuras = figuras;
    this.setFocusable(true);
  }

  public void addEventos(MouseAdapter oyente) {
    this.addMouseListener(oyente);
    this.addMouseMotionListener(oyente);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    figuras.dibujar(g);
  }
}
