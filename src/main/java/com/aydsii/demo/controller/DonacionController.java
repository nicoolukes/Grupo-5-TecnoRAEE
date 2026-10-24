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
 
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
@RestController
@RequestMapping("/api/CargarRAEEDonado")
@RequiredArgsConstructor
@Tag(name = "Donaciones", description = "CU-01 Cargar RAEEs donados")
public class DonacionController {
 
    private final IDonacionService service;
    private final IRaeeRepository raeeRepository;
 
    @Operation(
            summary = "Cargar una donación de RAEEs",
            description = "Registra una donación con sus detalles (id de RAEE, cantidad, estado) "
                    + "y calcula los puntos totales: cantidad × (puntos + puntos extra si es funcional)."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201", description = "Donación registrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400", description = "Datos inválidos (cantidad, campos obligatorios, sin detalles)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404", description = "El id del RAEE no existe")
    })
    @PostMapping
    public ResponseEntity<APIResponse<DonacionResponse>> registrarDonacion(
            @Valid @RequestBody DonacionRequest request) {
 
        validarRaee(request);
 
        DonacionResponse response = service.registrarDonacion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.ok("Donación registrada correctamente", response));
    }
 
    /** FA-02: todos los ids de RAEE deben existir; además no se admiten RAEEs repetidos (PK compuesta). */
    private void validarRaee(DonacionRequest request) {
        List<Integer> ids = request.getDetalles().stream()
                .map(DetalleDonacionRequest::getId)
                .toList();
        Set<Integer> idsUnicos = new HashSet<>(ids);
 
        if (idsUnicos.size() != ids.size()) {
            throw new BusinessException("No se puede repetir un mismo RAEE en los detalles de la donación");
        }
        if (raeeRepository.findAllById(idsUnicos).size() != idsUnicos.size()) {
            throw new RaeeNotFoundException("El id del RAEE no existe");
        }
    }
}