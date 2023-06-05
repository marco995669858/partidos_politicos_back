package com.proyecto.documento.dto;

import java.io.Serializable;

public class MensajeDTO implements Serializable {
    private String estado;
    private String mensaje;

    public MensajeDTO(){}

    public MensajeDTO(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public MensajeDTO(String success) {
        this.estado = success;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
