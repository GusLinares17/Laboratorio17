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
public class Batalla {
    public static Ejercito resolver(Ejercito ejercito1, Ejercito ejercito2) {
        int vidaTotalE1 = ejercito1.getVidaTotal();
        int vidaTotalE2 = ejercito2.getVidaTotal();
        int vidaTotal = vidaTotalE1 + vidaTotalE2;

        double probabilidadE1 = (double) vidaTotalE1 / vidaTotal;
        double probabilidadE2 = (double) vidaTotalE2 / vidaTotal;

        System.out.printf("Probabilidades de ganar - Ejercito 1: %.2f%%, Ejercito 2: %.2f%%\n", probabilidadE1 * 100, probabilidadE2 * 100);

        Random random = new Random();
        if (random.nextDouble() < probabilidadE1) {
            incrementarVida(ejercito1);
            return ejercito1;
        } else {
            incrementarVida(ejercito2);
            return ejercito2;
        }
    }

    private static void incrementarVida(Ejercito ejercito) {
        for (Soldado soldado : ejercito.soldados) {
            soldado.incrementarVida();
        }
    }
}
