package code;

import code.logica.Tablero;
import code.ventana.Malla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio implements ActionListener {

    public static JFrame ventana, ventana1;
    private static Tablero tablero;
    private static Consola consola;
    private static final JButton btnConsola = new JButton("Empieza Consola"),
            btnJugador = new JButton("Empieza Jugador");

    public static void main(String[] args) {
        new Inicio();
    }

    public Inicio() {
        inicializar();
    }

    public void inicializar() {

        inicializarVentana();

        btnConsola.setEnabled(false);
        btnConsola.setPreferredSize(new Dimension(180, 40));
        btnJugador.setPreferredSize(new Dimension(180, 40));
        btnConsola.addActionListener(this);
        btnJugador.addActionListener(this);

        ventana1 = new JFrame("Juego Perfecto 36");
        ventana1.setSize(320, 150);
        ventana1.setLayout(new FlowLayout());
        ventana1.setLocationRelativeTo(null);
        ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana1.setResizable(false);
        ventana1.setMaximizedBounds(ventana1.getBounds());

        ventana1.add(btnConsola);
        ventana1.add(btnJugador);
        ventana1.setVisible(true);
    }

    private static void inicializarVentana() {
        ventana = new JFrame("Juego Perfecto 36");
        ventana.setSize(720, 480);
        ventana.setLayout(null);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setMaximizedBounds(ventana.getBounds());

        boolean[] casillas = new boolean[36];

        for (int i = 0; i < casillas.length; i++) {
            casillas[i] = true;
        }

        tablero = new Tablero(casillas);
        Malla malla = new Malla(tablero);

        malla.setBounds(250, 10, 400, 400);
        ventana.add(malla);

        tablero.establecerMalla(malla);

        ventana.setVisible(false);

        consola = new Consola(tablero);
        tablero.establecerConsola(consola);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnJugador) {
            ventana1.dispose();
            ventana.setVisible(true);
            tablero.turnoJugador = true;
        } else {
            ventana1.dispose();
            ventana.setVisible(true);
            tablero.turnoJugador = false;
            consola.tirar();
        }
    }
}
