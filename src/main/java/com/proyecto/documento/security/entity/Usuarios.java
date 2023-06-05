package com.proyecto.documento.security.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "db_usuario")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String nombrecompleto;
    @NotNull
    private String nombreusuario;
    @NotNull
    private String apellidosusuario;
    @NotNull
    private String correo;

    private String correoalternativo;
    @NotNull
    private String password;

    @NotNull
    private Date fechanacimiento;

    @NotNull
    private String dni;

    @NotNull
    private String celular;

    @NotNull
    private String sexo;

    private Date fecharegistro;

    private Date fechaactualizacion;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuarios() {
    }

    public Usuarios(Integer id){
        this.id = id;
    }

    public Usuarios(String nombrecompleto, String nombreusuario, String apellidosusuario,
                    String correo, String correoalternativo, String password, Date fechanacimiento,
                    String dni, String celular, String sexo, Date fecharegistro, Date fechaactualizacion) {
        this.nombrecompleto = nombrecompleto;
        this.nombreusuario = nombreusuario;
        this.apellidosusuario = apellidosusuario;
        this.correo = correo;
        this.correoalternativo = correoalternativo;
        this.password = password;
        this.fechanacimiento = fechanacimiento;
        this.dni = dni;
        this.celular = celular;
        this.sexo = sexo;
        this.fecharegistro = fecharegistro;
        this.fechaactualizacion = fechaactualizacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getApellidosusuario() {
        return apellidosusuario;
    }

    public void setApellidosusuario(String apellidosusuario) {
        this.apellidosusuario = apellidosusuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreoalternativo() {
        return correoalternativo;
    }

    public void setCorreoalternativo(String correoalternativo) {
        this.correoalternativo = correoalternativo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Date getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Date fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
