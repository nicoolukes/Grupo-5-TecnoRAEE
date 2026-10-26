package com.aydsii.demo.controller;

import java.util.List;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.aydsii.demo.dto.ConvocatoriaDTO;
import com.aydsii.demo.dto.APIResponse;
import com.aydsii.demo.interfaces.IConvocatoriaService;
import com.aydsii.demo.config.SwaggerExamples;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/convocatorias")
@RequiredArgsConstructor
@Tag(name = "Convocatorias", description = "CU-02 Consultar convocatorias")
public class ConvocatoriaController {

    private final IConvocatoriaService convocatoriaService;
 
    @Operation(
        summary = "Consultar convocatorias",
        description = "Lista las convocatorias disponibles (estado Pendiente o En curso) con su nombre, "
                + "estado y fecha y hora de inicio. Las convocatorias finalizadas no se incluyen."
    )
    @ApiResponses({
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "Listado de convocatorias disponibles (vacío si no hay ninguna)",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = APIResponse.class),
                examples = {
                    @ExampleObject(name = "Con convocatorias", value = SwaggerExamples.CONVOCATORIAS_LISTA),
                    @ExampleObject(name = "Sin convocatorias", value = SwaggerExamples.CONVOCATORIAS_VACIA)
                })),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "500",
            description = "No es posible recuperar la información de las convocatorias",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = APIResponse.class),
                examples = @ExampleObject(value = SwaggerExamples.ERROR_CONVOCATORIAS)))
    })
    @GetMapping
    public ResponseEntity<APIResponse<List<ConvocatoriaDTO>>> consultarConvocatorias() {
 
        List<ConvocatoriaDTO> convocatorias = convocatoriaService.consultarConvocatorias();
 
        String mensaje = convocatorias.isEmpty()
                ? "No hay convocatorias disponibles"      // FA-01
                : "Listado de convocatorias";

        return ResponseEntity.ok(APIResponse.ok(mensaje, convocatorias));
    }
}
