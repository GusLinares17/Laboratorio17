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
public class Ejercito {
    String reino;
    ArrayList<Soldado> soldados;
    public Ejercito(String reino) {
        this.reino = reino;
        int cantidadSoldados = new Random().nextInt(10) + 1;
        this.soldados = new ArrayList<>();
        for (int i = 0; i < cantidadSoldados; i++) {
            soldados.add(new Soldado());
        }
    }
    public int getNumeroSoldados() {
        return soldados.size();
    }
    public int getVidaTotal() {
        int vidaTotal = 0;
        for (Soldado s : soldados) {
            vidaTotal += s.vida;
        }
        return vidaTotal;
    }
    public void aplicarBonificacionTerritorio(String tipoTerritorio) {
        if ((reino.equals("Inglaterra") && tipoTerritorio.equals("Bosque")) ||
            (reino.equals("Francia") && tipoTerritorio.equals("Campo Abierto")) ||
            (reino.equals("Castilla-Aragon") && tipoTerritorio.equals("Montana")) ||
            (reino.equals("Moros") && tipoTerritorio.equals("Desierto")) ||
            (reino.equals("Sacro Imperio Romano-Germanico") && 
                (tipoTerritorio.equals("Bosque") || tipoTerritorio.equals("Playa") || tipoTerritorio.equals("Campo Abierto")))) {
            for (Soldado soldado : soldados) {
                soldado.incrementarVida();
            }
        }
    }
}