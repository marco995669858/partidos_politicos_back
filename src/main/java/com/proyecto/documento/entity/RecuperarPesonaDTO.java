package com.proyecto.documento.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.proyecto.documento.dto.MensajeDTO;

public class RecuperarPesonaDTO extends MensajeDTO {

    /*@JsonProperty("nombre")
    private String nombre;*/
    /*@JsonProperty("tipoDocumento")
    private String tipoDocumento;*/
    @JsonProperty("nroDocumento")
    private String nroDocumento;
    /*@JsonProperty("estado")
    private String estado;*/
    /*@JsonProperty("condicion")
    private String condicion;*/
    /*@JsonProperty("direccion")
    private String direccion;*/
    /*@JsonProperty("ubigeo")
    private String ubigeo;*/
    /*@JsonProperty("viaTipo")
    private String viaTipo;*/
    /*@JsonProperty("viaNombre")
    private String viaNombre;*/
    /*@JsonProperty("zonaCodigo")
    private String zonaCodigo;*/
    /*@JsonProperty("zonaTipo")
    private String zonaTipo;*/
    /*@JsonProperty("numero")
    private String numero;*/
    /*@JsonProperty("interior")
    private String interior;*/
    /*@JsonProperty("lote")
    private String lote;*/
    /*@JsonProperty("dpto")
    private String dpto;*/
    /*@JsonProperty("manzana")
    private String manzana;*/
    /*@JsonProperty("kilometro")
    private String kilometro;*/
    /*@JsonProperty("distrito")
    private String distrito;*/
   /* @JsonProperty("provincia")
    private String provincia;*/
    /*@JsonProperty("departamento")
    private String departamento;*/
    @JsonProperty("apellidoPaterno")
    private String apellidoPaterno;
    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;
    @JsonProperty("nombres")
    private String nombres;

    private String apellidoCompletos;
    public RecuperarPesonaDTO(){

    }

    public RecuperarPesonaDTO(String estado, String mensaje) {
        super(estado, mensaje);
    }

    /*public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }*/

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    /*public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getViaTipo() {
        return viaTipo;
    }

    public void setViaTipo(String viaTipo) {
        this.viaTipo = viaTipo;
    }

    public String getViaNombre() {
        return viaNombre;
    }

    public void setViaNombre(String viaNombre) {
        this.viaNombre = viaNombre;
    }

    public String getZonaCodigo() {
        return zonaCodigo;
    }

    public void setZonaCodigo(String zonaCodigo) {
        this.zonaCodigo = zonaCodigo;
    }

    public String getZonaTipo() {
        return zonaTipo;
    }

    public void setZonaTipo(String zonaTipo) {
        this.zonaTipo = zonaTipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getKilometro() {
        return kilometro;
    }

    public void setKilometro(String kilometro) {
        this.kilometro = kilometro;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }*/

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoCompletos() {
        if(apellidoPaterno == null  && apellidoMaterno == null){
            return null;
        }

        return  apellidoPaterno + ' ' + apellidoMaterno;

    }

}
