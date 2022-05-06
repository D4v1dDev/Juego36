package code.ventana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Consola;
import code.logica.Tablero;

public class Malla extends JPanel implements ActionListener {

    private final BotonA[] BOTONES = new BotonA[36];

    private final Tablero t;

    public Malla(Tablero t) {
        setLayout(new GridLayout(6, 6));
        this.t = t;
        for (int i = 0; i < BOTONES.length; i++) {
            BOTONES[i] = new BotonA("" + (i + 1));
            BOTONES[i].addActionListener(this);
            BOTONES[i].setVisible(true);
            BOTONES[i].setEnabled(t.casillas[i]);
            add(BOTONES[i]);
        }
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < BOTONES.length; i++) {
            if (e.getSource() == BOTONES[i]) {
                if (t.estaDisponible(i + 1) && t.turnoJugador) {
                    t.eliminarCasilla(i + 1);
                }
            }
        }
    }

    public void eliminar(int numero) {
        BOTONES[numero - 1].setEnabled(false);
        BOTONES[numero - 1].color = BotonA.COLOR_APAGADO;
    }

}
