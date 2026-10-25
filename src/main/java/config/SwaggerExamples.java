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
}
