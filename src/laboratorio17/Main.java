/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio17;

/**
 *
 * @author Gustavo
 */
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        String[] tiposDeTerritorio = {"Bosque", "Campo Abierto", "Montana", "Desierto", "Playa"};
        Random random = new Random();
        String tipoSeleccionado = tiposDeTerritorio[random.nextInt(tiposDeTerritorio.length)];
        Mapa mapa = new Mapa(tipoSeleccionado);
    }
}
