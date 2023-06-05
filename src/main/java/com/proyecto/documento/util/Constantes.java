package com.proyecto.documento.util;

import org.springframework.beans.factory.annotation.Value;

public abstract class Constantes {
    public static final String SUCCESS = "1";
    public static final String ERROR = "0";
    public static final String MENSAJE_INSERTAR = "Se registro exitosamente";
    public static final String MENSAJE_ACTUALIZAR = "Se actualizo exitosamente";
    public static final String MENSAJE_ELIMINACION = "Se eliminao el registro exitosamente.";
    public static final String MENSAJE_ELIMINACION_CODIGO_NO_ENCONTRADO = "El codigo ingresado no existe.";
    public static final String MENSAJE_BUSQUEDA_NO_ENCONTRADO = "El codigo a buscar no existe.";
    public static final String MENSAJE_CREDENCIALES_ERRONEAS = "Las credenciales son erroneas.";
    public static final String MENSAJE_DNI_FALLIDO = "No se encontro el numero del dni o el dni ingresado no existe.";
    public static final String MENSAJE_DNI_CORRECTO = "Datos encontrados.";




    /*Credenciales de los sms*/
    public static final String customerId = System.getenv().getOrDefault("CUSTOMER_ID", "AE324E5C-8E0B-4A20-825C-C234AC55DB98");
    public static final String apiKey = System.getenv().getOrDefault("API_KEY", "7Id+CX8g8fUS5TiOmS6s40+TQINjsPupRA6Hj4OA8Ov5+psKugoyrccb1sWN9TZofjGv8gXOcvdimNJmXGZZxQ==");

}
