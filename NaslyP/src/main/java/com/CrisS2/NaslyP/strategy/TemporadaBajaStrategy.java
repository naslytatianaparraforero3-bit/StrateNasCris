package com.CrisS2.NaslyP.strategy;

public class TemporadaBajaStrategy implements CalculoTarifaStrategy {
    @Override
    public double calcularTotal(double precioBase, int noches) {
        return (precioBase * noches) * 0.90;
    }
}