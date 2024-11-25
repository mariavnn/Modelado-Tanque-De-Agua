/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author maria
 */
public class TransmisorNivel {
    private double nivelActual;

    public TransmisorNivel() {
        this.nivelActual = 0.0;
    }

    public double obtenerNivelActual() {
        return nivelActual;
    }
    
    public void setNivelAgua(double nivelAgua) {
        this.nivelActual = nivelAgua;
    }
}
