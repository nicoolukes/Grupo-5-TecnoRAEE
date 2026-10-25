package config;

public class SwaggerExamples {

    private SwaggerExamples() {}

    public static final String ERROR_CANTIDAD = """
            { "success": false, "message": "La cantidad debe ser mayor a cero", "data": null }
            """;

    public static final String ERROR_RAEE = """
            { "success": false, "message": "El id del RAEE no existe", "data": null }
            """;

    public static final String ERROR_PERSONA = """
            { "success": false, "message": "El id de la persona no existe", "data": null }
            """;

    public static final String ERROR_PERMISOS = """
            { "success": false, "message": "No tiene permisos para registrar donaciones", "data": null }
            """;
    
    public static final String CONVOCATORIAS_LISTA = """
        {
          "success": true,
          "message": "Listado de convocatorias",
          "data": [
            { "nombre": "Jornada de reciclaje tecnológico", "estado": "En curso", "fechaHoraInicio": "2026-10-30T09:00:00" }
          ]
        }
        """;

    public static final String CONVOCATORIAS_VACIA = """
            { "success": true, "message": "No hay convocatorias disponibles", "data": [] }
            """;

    public static final String ERROR_CONVOCATORIAS = """
            { "success": false, "message": "No es posible recuperar la información de las convocatorias", "data": null }
            """;
}
