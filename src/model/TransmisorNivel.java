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
    private double nivelAgua;

    public TransmisorNivel() {
        this.nivelAgua = 0.0;
    }

    public double medirNivel() {
        return this.nivelAgua;
    }

    public void setNivelAgua(double nivelAgua) {
        this.nivelAgua = nivelAgua;
    }
}
