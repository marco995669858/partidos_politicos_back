package com.proyecto.documento.security.controler;

import com.proyecto.documento.security.dto.JwtDto;
import com.proyecto.documento.security.dto.LoginUsuario;
import com.proyecto.documento.security.dto.NuevoUsuario;
import com.proyecto.documento.security.entity.UsuarioPrincipal;
import com.proyecto.documento.security.jwt.JwtProvaider;
import com.proyecto.documento.security.service.UsuarioService;
import com.proyecto.documento.util.Constantes;
import com.proyecto.documento.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    JwtProvaider jwtProvaider;

    @PostMapping("/nuevo")
    public Mensaje nuevoUsuario(@RequestBody NuevoUsuario nuevoUsuario){
        try {
            String password = passwordEncoder.encode(nuevoUsuario.getPassword());
            return usuarioService.save(nuevoUsuario,password);
        }catch (Exception e){
           return new Mensaje(Constantes.ERROR, e.getMessage());
        }

    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtDto> login(@RequestBody LoginUsuario loginUsuario){
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvaider.generateToken(authentication);
            //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UsuarioPrincipal usuario = (UsuarioPrincipal) authentication.getPrincipal();
            JwtDto jwtDto = new JwtDto(jwt, usuario.getUsername(), usuario.getAuthorities(),usuario.getId());
            return new ResponseEntity(jwtDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new Mensaje(Constantes.ERROR, Constantes.MENSAJE_CREDENCIALES_ERRONEAS),HttpStatus.BAD_REQUEST);
        }
    }
}
