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
    private double nivelActual;

    public ControlNivel() {
        this.nivelActual = 0.0; // Nivel inicial
    }

    public void setNivelActual(double nivelNuevo) {
        this.nivelActual = nivelNuevo;
    }
    
    public double medirNivel() {
        return this.nivelActual;
    }

}
