package code.ventana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BotonA extends JButton implements MouseListener {

    public final static Color COLOR_ENCENDIDO = new Color(148, 243, 128), COLOR_APAGADO = new Color(232, 115, 115);
    public Color color = COLOR_ENCENDIDO;

    public BotonA(String a) {
        setText(a);
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(color);
        g.fillRect(2, 2, getWidth() - 4, getHeight() - 4);
        g.setFont(new Font("Calibri", Font.BOLD, 15));
        g.setColor(Color.darkGray);
        g.drawString(getText(), (getWidth() / 2) - 4, (getHeight() / 2) + 4);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        color = COLOR_APAGADO;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (isEnabled())
            color = new Color(217, 194, 95);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (isEnabled()) {
            color = COLOR_ENCENDIDO;
        }
    }
}
