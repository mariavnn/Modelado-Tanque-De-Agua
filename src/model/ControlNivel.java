/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maria
 */
public class ControlNivel {
     private Valvula valvula;

    public ControlNivel(Valvula valvula) {
        this.valvula = valvula;
    }

    public void verificarNivel(double nivelAgua) {
        if (nivelAgua <= 0.6) {
            valvula.abrir(100);
        } else if (nivelAgua < 1.0) {
            valvula.abrir(50);
        } else {
            valvula.abrir(0);
        }
    }
}
