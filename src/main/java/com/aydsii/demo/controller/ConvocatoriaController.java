package com.aydsii.demo.controller;

import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.aydsii.demo.dto.ConvocatoriaDTO;
import com.aydsii.demo.dto.APIResponse;
import com.aydsii.demo.interfaces.IConvocatoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/convocatorias")
public class ConvocatoriaController {

    private final IConvocatoriaService convocatoriaService;
 
    public ConvocatoriaController(IConvocatoriaService convocatoriaService) {
        this.convocatoriaService = convocatoriaService;
    }
 
    @Operation(
        summary = "Consultar convocatorias",
        description = "Lista todas las convocatorias con una descripción breve de cada una, incluyendo nombre, fecha y estado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listado de convocatorias"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<APIResponse<List<ConvocatoriaDTO>>> consultarConvocatorias() {
 
        List<ConvocatoriaDTO> convocatorias = convocatoriaService.consultarConvocatorias();
 
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok("Listado de convocatorias", convocatorias));
    }
}
