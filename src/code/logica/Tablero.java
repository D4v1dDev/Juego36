package code.logica;

import code.Consola;
import code.ventana.Malla;

import java.util.ArrayList;

public class Tablero {

    public Malla malla;
    public Consola consola;
    public boolean[] casillas;
    public int ultimaTirada = 0;
    public boolean turnoJugador = true;

    public Tablero(boolean[] casillas) {
        this.casillas = casillas;
        malla = null;
    }

    public void establecerConsola(Consola c) {
        consola = c;
    }

    public void establecerMalla(Malla malla) {
        this.malla = malla;
    }

    //Obtener los numeros que aun no se han escogido.
    public int[] obtenerNumerosNoEscogidos() {
        //Se crea un ArrayList
        ArrayList<Integer> num = new ArrayList<>();

        //Se obtienen las casillas en el ArrayList que aun no se han escogido
        for (int i = 0; i < casillas.length; i++) {
            if (casillas[i]) {
                num.add(i + 1);
            }
        }

        //Se copia el ArrayList en una matriz normal
        int[] vuelta = new int[num.size()];
        for (int i = 0; i < num.size(); i++) {
            vuelta[i] = num.get(i);
        }
        return vuelta;
    }

    //Obtener los únicos números que se pueden elegir a partir de la tirada anterior
    public int[] obtenerEleccionesDisponiblesPara(int n) {
        boolean primerMovimiento = n == 0;
        //Se crea un array y una matriz, esta ultima contiene los numeros que no se han escogido
        ArrayList<Integer> tiradasPosibles = new ArrayList<>();
        int[] numerosDisponibles = obtenerNumerosNoEscogidos();
        if (primerMovimiento) n = 2;
        //Se comprueba los numeros que se pueden tirar a raiz de la jugada n
        for (int numerosDisponible : numerosDisponibles) {
            if (numerosDisponible % n == 0 || n % numerosDisponible == 0) {
                tiradasPosibles.add(numerosDisponible);
            }
        }
        n = 0;
        if (primerMovimiento) n = 1;
        //Se copia el ArrayList en una matriz
        numerosDisponibles = new int[tiradasPosibles.size()];
        for (int i = n; i < tiradasPosibles.size(); i++) {
            numerosDisponibles[i] = tiradasPosibles.get(i);
        }

        return numerosDisponibles;
    }

    public int[] obtenerEleccionesDisponibles() {
        return obtenerEleccionesDisponiblesPara(ultimaTirada);
    }

    //Nos dice si el número n está disponible
    public boolean estaDisponible(int n) {

        //Se crea la matriz nums con las elecciones posibles
        if (ultimaTirada == 0) ultimaTirada = 2;

        int[] nums = obtenerEleccionesDisponibles();

        //Dependiendo si es la primera u otra tirada escoge valores distintos
       /* if(ultimaTirada==0){
            //Si es la Primera Jugada

            nums = obtenerEleccionesDisponiblesPara(2);
            int[] temp = new int[nums.length-1];
            if (nums.length - 1 >= 0) System.arraycopy(nums, 1, temp, 0, nums.length - 1);
            nums=temp;
        }else {
            //Si no es la Primera Jugada

            nums = obtenerEleccionesDisponibles();
        }*/

        //Si n esta dentro de los numeros disponibles devuelve true, sino false
        for (int num : nums) {
            if (num == n) {
                return true;
            }
        }
        return false;
    }

    //Elimina la casilla indicada y ademas hace tirar a la consola
    public void eliminarCasilla(int casilla) {
        casillas[casilla - 1] = false;
        if (malla != null) {
            malla.eliminar(casilla);
        }
        ultimaTirada = casilla;
        turnoJugador = !turnoJugador;
        if (!turnoJugador && consola != null) {
            consola.tirar();
        }
    }

    //Se crea otro tablero donde se ha tirado 'eleccion' y se devuelve
    public Tablero obtenerTableroAPartirDeEleccion(int eleccion) {

        //Se crea otra instancia de tablero, esta será el futuro tablero.

        boolean[] a = new boolean[36];

        System.arraycopy(casillas, 0, a, 0, casillas.length);

        Tablero t = new Tablero(a);

        t.turnoJugador = turnoJugador;
        t.ultimaTirada = ultimaTirada;

        //En este tablero se elimina la casilla seleccionada, y el turno se cambia además de la ultimaTirada
        t.eliminarCasilla(eleccion);


        return t;
    }

    //Obtiene todos los tableros posibles a partir del actual
    public Tablero[] obtenerTablerosPosibles() {

        int[] posibilidades = obtenerEleccionesDisponibles();

        //Hacer una matriz con todos los tableros posibles tras elegir alguna casilla
        Tablero[] tableros = new Tablero[posibilidades.length];

        //Inicializar cada elemento con cada eleccion
        for (int i = 0; i < tableros.length; i++) {
            tableros[i] = obtenerTableroAPartirDeEleccion(posibilidades[i]);
        }

        //Devolver el Valor
        return tableros;
    }

    public double posibilidadDeVictoriaJugador() {

        int[] elecciones = obtenerEleccionesDisponiblesPara(ultimaTirada);

        //Si no hay posibilidades en el tablero
        if (elecciones.length == 0) {
            if (turnoJugador) {
                return 0;
            } else {
                return 1;
            }
        }
        if (ultimaTirada == 1) {
            if (turnoJugador) {
                return 1;
            } else {
                return 0;
            }
        }

        Tablero[] tableros = obtenerTablerosPosibles();

        double mejorPosibilidad;
        if (turnoJugador) {
            mejorPosibilidad = 0;
        } else {
            mejorPosibilidad = 1;
        }

        for (int i = 0; i < tableros.length; i++) {
            double p = tableros[i].posibilidadDeVictoriaJugador();
            if (turnoJugador) {
                if (p > mejorPosibilidad) {
                    mejorPosibilidad = p;
                }
            } else {
                if (mejorPosibilidad > p) {
                    mejorPosibilidad = p;
                }
            }
        }
        return mejorPosibilidad;

    }
}