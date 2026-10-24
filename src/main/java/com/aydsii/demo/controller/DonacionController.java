package com.aydsii.demo.controller;

import com.aydsii.demo.dto.APIResponse;
import com.aydsii.demo.dto.DetalleDonacionRequest;
import com.aydsii.demo.dto.DonacionRequest;
import com.aydsii.demo.dto.DonacionResponse;
import com.aydsii.demo.exception.BusinessException;
import com.aydsii.demo.exception.RaeeNotFoundException;
import com.aydsii.demo.interfaces.IDonacionService;
import com.aydsii.demo.repository.IRaeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@RequestMapping("/api/donaciones")
@RequiredArgsConstructor
@Tag(name = "Donaciones", description = "CU-01 Cargar donacion")
public class DonacionController {
 
    private final IDonacionService service;
 
    @Operation(
            summary = "Cargar una donación de RAEEs",
            description = "Registra una donación con sus detalles (id de RAEE, cantidad, estado) "
                    + "y calcula los puntos totales: cantidad × (puntos + puntos extra si es funcional)."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201", description = "Donación registrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Datos inválidos"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404", description = "El id del RAEE no existe")
    })
    @PostMapping
    public ResponseEntity<APIResponse<DonacionResponse>> registrarDonacion(
            @Valid @RequestBody DonacionRequest request) {
 
        DonacionResponse response = service.registrarDonacion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.ok("Donación registrada correctamente", response));
    }
}