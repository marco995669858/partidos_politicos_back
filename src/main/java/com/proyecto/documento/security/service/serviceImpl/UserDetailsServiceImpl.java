package com.proyecto.documento.security.service.serviceImpl;

import com.proyecto.documento.security.entity.Usuarios;
import com.proyecto.documento.security.entity.UsuarioPrincipal;
import com.proyecto.documento.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;


    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuarios usuario = usuarioService.getBynombreUsuario(nombreUsuario,nombreUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }
}
