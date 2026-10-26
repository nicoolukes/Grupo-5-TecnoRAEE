package com.aydsii.demo.service;

import com.aydsii.demo.entity.DetalleDonacion;
import com.aydsii.demo.entity.Donacion;
import com.aydsii.demo.entity.Raee;
import com.aydsii.demo.interfaces.ICalculadoraPuntos;
import org.springframework.stereotype.Component;
 
@Component
public class CalculadoraPuntos implements ICalculadoraPuntos {
 
    /**
     * Puntos totales = suma de cantidad × (puntos + puntosExtra).
     * Los puntos extra solo aplican a detalles "Funcionales" (RN-05).
     */
    @Override
    public Integer calcularTotal(Donacion donacion) {
        int total = 0;
        for (DetalleDonacion detalle : donacion.getDetalles()) {
            Raee raee = detalle.getRaee();
            int puntos = raee.getPuntos() != null ? raee.getPuntos() : 0;
            int extra = Boolean.TRUE.equals(detalle.getFunciona()) && raee.getPuntosExtra() != null
                    ? raee.getPuntosExtra() : 0;
            total += detalle.getCantidad() * (puntos + extra);
        }
        return total;
    }
}