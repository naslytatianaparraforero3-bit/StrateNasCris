package com.CrisS2.NaslyP.service;

import com.CrisS2.NaslyP.strategy.CalculoTarifaStrategy;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private CalculoTarifaStrategy strategy;

    public void setStrategy(CalculoTarifaStrategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(double precioBase, int noches) {
        if (this.strategy == null) {
            throw new IllegalStateException("No se ha seleccionado ninguna estrategia de tarifa.");
        }
        return this.strategy.calcularTotal(precioBase, noches);
    }
}