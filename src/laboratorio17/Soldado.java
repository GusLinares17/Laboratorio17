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
public class Soldado {
    int vida;
    public Soldado() {
        this.vida = new Random().nextInt(5) + 1;
    }
    public void incrementarVida() {
        this.vida += 1;
    }
}