package com.aydsii.demo.controller;

import com.aydsii.demo.config.SwaggerExamples;
import com.aydsii.demo.dto.APIResponse;
import com.aydsii.demo.dto.CanjeRequest;
import com.aydsii.demo.dto.CanjeResponse;
import com.aydsii.demo.interfaces.ICanjeService;

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
@RequestMapping("/api/canjear")
@RequiredArgsConstructor
@Tag(name = "Canjes", description = "CU-04 Canjear Puntos por Cupones")
public class CanjeController {

    private final ICanjeService canjeService;

    @Operation(
            summary = "Canjear puntos por cupones",
            description = "Descuenta los puntos de la persona (costo del cupón por cantidad) y guarda los cupones "
                    + "en su billetera (inventario) como disponibles."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Canje realizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos, cupón inactivo o puntos insuficientes",
                    content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = APIResponse.class),
                        examples = @ExampleObject(value = SwaggerExamples.ERROR_CANTIDAD))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "El id de la persona o del cupón no existe",
                    content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = APIResponse.class),
                        examples = @ExampleObject(name = "Persona inexistente", value = SwaggerExamples.ERROR_PERSONA)))
            
    })
    @PostMapping
    public ResponseEntity<APIResponse<CanjeResponse>> canjearPuntosPorCupones(@Valid @RequestBody CanjeRequest request) {

        CanjeResponse response = canjeService.canjearPuntosPorCupones(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.ok("Canje realizado correctamente", response));
    }
}