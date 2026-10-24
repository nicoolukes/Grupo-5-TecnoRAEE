package com.aydsii.demo.interfaces;

import com.aydsii.demo.dto.DonacionRequest;
import com.aydsii.demo.dto.DonacionResponse;
 
public interface IDonacionService {
 
    DonacionResponse registrarDonacion(DonacionRequest donacion);
}