/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maria
 */
public class Valvula {
    private int apertura;

    public Valvula() {
        this.apertura = 0;
    }

    public void abrir(int porcentaje) {
        this.apertura = porcentaje;
    }

    public int getApertura() {
        return this.apertura;
    }
}
