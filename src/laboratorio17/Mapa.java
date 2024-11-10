/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio17;

/**
 *
 * @author Gustavo
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Mapa {
   String tipoDeTerritorio;
    Ejercito[][] campoDeBatalla;
    String[] reinos = {"Francia", "Inglaterra", "Castilla-Aragon", "Moros", "Sacro Imperio Romano-Germanico"};
    String reinoJugadorA;
    String reinoJugadorB;
    int vidaTotalJugadorA = 0;
    int vidaTotalJugadorB = 0;
    int cantidadEjercitosJugadorA = 0;
    int cantidadEjercitosJugadorB = 0;
    int cantidadSoldadosJugadorA = 0;
    int cantidadSoldadosJugadorB = 0;
    public Mapa(String tipoDeTerritorio) {
        this.tipoDeTerritorio = tipoDeTerritorio;
        this.campoDeBatalla = new Ejercito[10][10];
        seleccionarReinos();
        generarEjercitos();
        mostrarInformacionInicial();
        mostrarTablero();
        aplicarBonificacionTerritorio();
        mostrarBonificacion();
        mostrarTablero();
        iniciarJuego();
    }
    private void seleccionarReinos() {
        Random random = new Random();
        reinoJugadorA = reinos[random.nextInt(reinos.length)];
        do {
            reinoJugadorB = reinos[random.nextInt(reinos.length)];
        } while (reinoJugadorA.equals(reinoJugadorB));
    }

    private void generarEjercitos() {
        Random random = new Random();
        colocarEjercitos(reinoJugadorA, random.nextInt(10) + 1);
        colocarEjercitos(reinoJugadorB, random.nextInt(10) + 1);
    }
    private void colocarEjercitos(String reino, int cantidadEjercitos) {
        Random random = new Random();
        for (int i = 0; i < cantidadEjercitos; i++) {
            int x, y;
            do {
                x = random.nextInt(10);
                y = random.nextInt(10);
            } while (campoDeBatalla[x][y] != null);
            campoDeBatalla[x][y] = new Ejercito(reino);
        }
    }
    private void mostrarInformacionInicial() {
        System.out.println("Batalla entre: " + reinoJugadorA + " vs " + reinoJugadorB);
        System.out.println("Tipo de Territorio: " + tipoDeTerritorio);
        System.out.println("\nCampo de Batalla Inicial:");
    }
    private void aplicarBonificacionTerritorio() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (campoDeBatalla[i][j] != null && 
                   (campoDeBatalla[i][j].reino.equals(reinoJugadorA) || campoDeBatalla[i][j].reino.equals(reinoJugadorB))) {
                    campoDeBatalla[i][j].aplicarBonificacionTerritorio(tipoDeTerritorio);
                }
            }
        }
    }
    private void mostrarBonificacion() {
        System.out.print("Bonificacion por tipo de territorio a: ");
        boolean bonificacionOtorgada = false;
        
        if (esBeneficiado(reinoJugadorA)) {
            System.out.print(reinoJugadorA + " ");
            bonificacionOtorgada = true;
        }
        
        if (esBeneficiado(reinoJugadorB)) {
            System.out.print(reinoJugadorB);
            bonificacionOtorgada = true;
        }

        if (!bonificacionOtorgada) {
            System.out.print("Ningun reino");
        }

        System.out.println("\n\nCampo de Batalla con Bonificacion:");
    }
    private boolean esBeneficiado(String reino) {
        return (reino.equals("Inglaterra") && tipoDeTerritorio.equals("Bosque")) ||
               (reino.equals("Francia") && tipoDeTerritorio.equals("Campo Abierto")) ||
               (reino.equals("Castilla-Aragon") && tipoDeTerritorio.equals("Montana")) ||
               (reino.equals("Moros") && tipoDeTerritorio.equals("Desierto")) ||
               (reino.equals("Sacro Imperio Romano-Germanico") && 
               (tipoDeTerritorio.equals("Bosque") || tipoDeTerritorio.equals("Playa") || tipoDeTerritorio.equals("Campo Abierto")));
    }
    private void mostrarTablero() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (campoDeBatalla[i][j] != null) {
                    String simbolo = campoDeBatalla[i][j].reino.equals(reinoJugadorA) ? "A" : "B";
                    System.out.printf("%s:%-8s", simbolo, campoDeBatalla[i][j].getNumeroSoldados() + ":" + campoDeBatalla[i][j].getVidaTotal());
                } else {
                    System.out.printf("%-10s", "______");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    private void iniciarJuego() {
        Scanner scanner = new Scanner(System.in);
        boolean juegoTerminado = false;
        String jugadorActual = "Jugador A";
        while (!juegoTerminado) {
            System.out.println(jugadorActual + " elige un ejercito para mover");
            int fila, columna;
            do {
                System.out.print("Fila: ");
                fila = scanner.nextInt() - 1;
                System.out.print("Columna: ");
                columna = scanner.nextInt() - 1;
            } while (!posicionValida(fila, columna, jugadorActual));

            System.out.print("Mover al (norte, sur, este, oeste): ");
            String direccion = scanner.next();

            moverEjercito(fila, columna, direccion, jugadorActual);
            mostrarTablero();

            juegoTerminado = verificarFinDelJuego();
            jugadorActual = jugadorActual.equals("Jugador A") ? "Jugador B" : "Jugador A";
        }
        scanner.close();
    }
    private boolean posicionValida(int fila, int columna, String jugadorActual) {
        if (fila < 0 || fila >= 10 || columna < 0 || columna >= 10) return false;
        Ejercito ejercito = campoDeBatalla[fila][columna];
        if (ejercito == null) return false;
        String reino = jugadorActual.equals("Jugador A") ? reinoJugadorA : reinoJugadorB;
        return ejercito.reino.equals(reino);
    }
    private void moverEjercito(int fila, int columna, String direccion, String jugadorActual) {
        Ejercito ejercito = campoDeBatalla[fila][columna];
        int nuevaFila = fila;
        int nuevaColumna = columna;
    switch (direccion.toLowerCase()) {
        case "norte":
            nuevaFila--;
            break;
        case "sur":
            nuevaFila++;
            break;
        case "este":
            nuevaColumna++;
            break;
        case "oeste":
            nuevaColumna--;
            break;
    }
    if (esMovimientoValido(nuevaFila, nuevaColumna, jugadorActual)) {
        if (campoDeBatalla[nuevaFila][nuevaColumna] == null) {
            campoDeBatalla[nuevaFila][nuevaColumna] = ejercito;
            campoDeBatalla[fila][columna] = null;
        } else {
            Ejercito ejercitoEnemigo = campoDeBatalla[nuevaFila][nuevaColumna];
            Ejercito ganador = Batalla.resolver(ejercito, ejercitoEnemigo);

            if (ganador == ejercito) {
                campoDeBatalla[nuevaFila][nuevaColumna] = ejercito;
                campoDeBatalla[fila][columna] = null;
            } else {
                campoDeBatalla[fila][columna] = null;
            }
            System.out.println("Ganador de la batalla: " + ganador.reino);
        }
    } else {
        System.out.println("Movimiento invalido. Intenta de nuevo.");
            }
    }
    private boolean esMovimientoValido(int fila, int columna, String jugadorActual) {
        if (fila < 0 || fila >= 10 || columna < 0 || columna >= 10) return false;
        Ejercito ejercitoDestino = campoDeBatalla[fila][columna];
        String reinoJugador = jugadorActual.equals("Jugador A") ? reinoJugadorA : reinoJugadorB;

        return ejercitoDestino == null || !ejercitoDestino.reino.equals(reinoJugador);
    }
    private boolean verificarFinDelJuego() {
       int ejercitosJugadorA = contarEjercitos(reinoJugadorA);
    int ejercitosJugadorB = contarEjercitos(reinoJugadorB);

    if (ejercitosJugadorA == 0) {
        System.out.println("Jugador B ha ganado el juego.");
        return true;
    } else if (ejercitosJugadorB == 0) {
        System.out.println("Jugador A ha ganado el juego.");
        return true;
    }
    return false;
    }
   private int contarEjercitos(String reino) {
    int contador = 0;
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            if (campoDeBatalla[i][j] != null && campoDeBatalla[i][j].reino.equals(reino)) {
                contador++;
            }
        }
    }
    return contador;
}
}