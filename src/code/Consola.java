package code;

import code.logica.Tablero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Consola implements ActionListener {

    private final Tablero tablero;

    public Consola(Tablero tablero) {
        this.tablero = tablero;
    }

    public void tirar() {
        planificarJugada();
    }

    private void planificarJugada() {

        int tiempo = (int) (System.nanoTime() / 1000000000F);
        System.out.println("Se comienza a planificar la jugada");

        int[] posibilidades = tablero.obtenerEleccionesDisponibles();
        Tablero[] tableros = tablero.obtenerTablerosPosibles();
        double mejorPosibilidad = 1;
        int indexMejorJugada = -1;
        for (int i = 0; i < tableros.length; i++) {

            double p = tableros[i].posibilidadDeVictoriaJugador();

            if (mejorPosibilidad > p) {
                mejorPosibilidad = p;
                indexMejorJugada = i;
            }
            System.out.println("Posibilidad del " + posibilidades[i] + " : " + p + "\t(" + mejorPosibilidad + ")\t|" + (i * 100 / tableros.length) + "% \t" + obtenerTiempoDemorado(tiempo));
            if (mejorPosibilidad == 0) {
                break;
            }
        }
        System.out.println("Terminamos la jugada | " + obtenerTiempoDemorado(tiempo));
        //         JUGAR       (                                        MEJOR JUGADA POSIBLE                   )
        if (posibilidades.length == 0) {
            juegoTerminado(true);
            return;
        }

        if (indexMejorJugada == -1) {
            Random r = new Random();
            if (posibilidades.length > 1) {
                indexMejorJugada = r.nextInt(posibilidades.length - 1) + 1;
            } else {
                indexMejorJugada = r.nextInt(posibilidades.length);
            }
        }
        tablero.eliminarCasilla(posibilidades[indexMejorJugada]);

        if (tablero.obtenerEleccionesDisponibles().length == 0) {
            juegoTerminado(false);
        }

    }


    private JPanel v;
    private final JButton btnJugarDeNuevo = new JButton("Otra partida"), btnCerrar = new JButton("Salir");

    private void juegoTerminado(boolean jugadorGana) {

        JLabel l = new JLabel();
        l.setBounds(0, 0, 200, 80);
        if (jugadorGana) {
            l.setText("Jugador ha ganado");
        } else {
            l.setText("Consola ha ganado");
        }

        v = new JPanel();
        v.setBounds(20, 40, 200, 150);
        v.setLayout(null);
        v.add(l);


        btnJugarDeNuevo.setBounds(0, 80, 200, 20);
        btnCerrar.setBounds(0, 100, 200, 20);

        btnCerrar.addActionListener(this);
        btnJugarDeNuevo.addActionListener(this);

        v.add(btnCerrar);
        v.add(btnJugarDeNuevo);

        btnCerrar.setVisible(true);
        btnJugarDeNuevo.setEnabled(false);
        btnJugarDeNuevo.setVisible(true);
        l.setVisible(true);

        Inicio.ventana.add(v);
        v.setVisible(true);
        Inicio.ventana.repaint();
    }

    private String obtenerTiempoDemorado(int tiempo) {
        String vuelta = "";

        int t = (int) (System.nanoTime() / 1000000000F) - tiempo;

        vuelta += (int) (t / 60);
        vuelta += " min. ";
        vuelta += (int) (t % 60);
        vuelta += " seg.";

        return "t / " + vuelta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (btnCerrar == e.getSource()) {
            System.exit(0);
        } else {
            new Inicio();
        }
    }
}
