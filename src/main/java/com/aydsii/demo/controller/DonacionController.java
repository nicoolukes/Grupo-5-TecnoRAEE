package com.aydsii.demo.controller;

import com.aydsii.demo.dto.APIResponse;
import com.aydsii.demo.dto.DonacionRequest;
import com.aydsii.demo.dto.DonacionResponse;
import com.aydsii.demo.interfaces.IDonacionService;
import com.aydsii.demo.config.SwaggerExamples;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
                    responseCode = "201",
                    description = "Donación registrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos (cantidad, campos obligatorios, sin detalles)",
                    content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = APIResponse.class),
                        examples = @ExampleObject(value = SwaggerExamples.ERROR_CANTIDAD))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "La persona no tiene rol Personal Convocatoria",
                    content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = APIResponse.class),
                        examples = @ExampleObject(value = SwaggerExamples.ERROR_PERMISOS))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "El id del RAEE o de la persona no existe",
                    content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = APIResponse.class),
                        examples = {
                                @ExampleObject(name = "RAEE inexistente", value = SwaggerExamples.ERROR_RAEE),
                                @ExampleObject(name = "Persona inexistente", value = SwaggerExamples.ERROR_PERSONA)
                        }))
    })
    @PostMapping
    public ResponseEntity<APIResponse<DonacionResponse>> registrarDonacion(
            @Valid @RequestBody DonacionRequest request) {
 
        DonacionResponse response = service.registrarDonacion(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.ok("Donación registrada correctamente", response));
    }
}