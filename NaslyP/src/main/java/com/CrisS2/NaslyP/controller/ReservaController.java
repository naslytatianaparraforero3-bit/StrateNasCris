package com.CrisS2.NaslyP.controller;

import com.CrisS2.NaslyP.service.ReservaService;
import com.CrisS2.NaslyP.strategy.TemporadaAltaStrategy;
import com.CrisS2.NaslyP.strategy.TemporadaBajaStrategy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/calcular")
    public String calcularTarifa(
            @RequestParam double precio,
            @RequestParam int noches,
            @RequestParam String temporada) {
        
        if (temporada.equalsIgnoreCase("baja")) {
            reservaService.setStrategy(new TemporadaBajaStrategy());
        } else if (temporada.equalsIgnoreCase("alta")) {
            reservaService.setStrategy(new TemporadaAltaStrategy());
        } else {
            return "Error: La temporada '" + temporada + "' no es válida. Use 'baja' o 'alta'.";
        }
        
        double total = reservaService.executeStrategy(precio, noches);
        
        return "El costo total de su reserva en temporada " + temporada + " es de: $" + total;
    }
}