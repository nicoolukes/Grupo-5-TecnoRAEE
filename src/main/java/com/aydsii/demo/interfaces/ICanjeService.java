package com.aydsii.demo.interfaces;

import com.aydsii.demo.dto.CanjeRequest;
import com.aydsii.demo.dto.CanjeResponse;

public interface ICanjeService {

    CanjeResponse canjearPuntosPorCupones(CanjeRequest request);
}
