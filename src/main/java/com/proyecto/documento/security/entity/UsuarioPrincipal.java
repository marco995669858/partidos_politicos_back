package com.proyecto.documento.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioPrincipal implements UserDetails {


    private String nombrecompleto;
    private String nombreUsuario;
    private String correo;
    private String password;
    private Integer id;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String nombrecompleto, String nombreUsuario, String correo, String password,
                            Collection<? extends GrantedAuthority> authorities, Integer id) {
        this.nombrecompleto = nombrecompleto;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;
        this.authorities = authorities;
        this.id = id;
    }

    public static UsuarioPrincipal build(Usuarios usuario){
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol ->
                new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());

        return new UsuarioPrincipal(usuario.getNombrecompleto(), usuario.getNombreusuario(), usuario.getCorreo(),
                usuario.getPassword(), authorities, usuario.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
