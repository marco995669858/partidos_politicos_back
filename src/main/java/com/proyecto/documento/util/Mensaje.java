package com.proyecto.documento.util;

import lombok.Data;

@Data
public class Mensaje {

    private String codigo;
    private String message;

    public Mensaje(String message){
        super();
        this.message = message;
    }
    public Mensaje(String codigo,String message){
        super();
        this.codigo = codigo;
        this.message = message;
    }

}
