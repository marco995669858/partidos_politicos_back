package com.proyecto.documento.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "db_archivo")
@Data
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoarchivos;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Usuarios id;
    private String nombrearchivo;
    private String rutaarchivo;
    private Timestamp fecharegistro;
    private Timestamp fechaactualizacion;

    private String url;

    public Archivo(String nombreArchivo, String rutaArchivo){

        this.nombrearchivo = nombreArchivo;
        this.rutaarchivo = rutaArchivo;
    }

    public Archivo(Integer codigoarchivos, Usuarios id, String nombrearchivo, String rutaarchivo,
                   Timestamp fecharegistro, Timestamp fechaactualizacion) {
        this.codigoarchivos = codigoarchivos;
        this.id = id;
        this.nombrearchivo = nombrearchivo;
        this.rutaarchivo = rutaarchivo;
        this.fecharegistro = fecharegistro;
        this.fechaactualizacion = fechaactualizacion;
    }

    public Archivo(Integer codigoarchivos){
        this.codigoarchivos = codigoarchivos;
    }

    public Archivo(){}
}
