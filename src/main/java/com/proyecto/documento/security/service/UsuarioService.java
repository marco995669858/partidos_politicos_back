package com.proyecto.documento.security.service;

import com.proyecto.documento.security.dto.NuevoUsuario;
import com.proyecto.documento.security.entity.Rol;
import com.proyecto.documento.security.entity.Usuarios;
import com.proyecto.documento.security.enums.RolNombre;
import com.proyecto.documento.security.repository.UsuarioRepositories;
import com.proyecto.documento.util.Constantes;
import com.proyecto.documento.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    RolService rolService;

    @Autowired
    UsuarioRepositories usuarioRepository;

    public Optional<Usuarios> getBynombreUsuario(String nombreUsuario, String correo){
        return  usuarioRepository.findByNombreusuarioOrCorreo(nombreUsuario, correo);
    }

    public Mensaje save(NuevoUsuario bean,String password){
        try {
            boolean correoExistente = usuarioRepository.existsByCorreo(bean.getCorreo());
            boolean nombreUsuario = usuarioRepository.existsByNombreusuario(bean.getNombreusuario());
            List<Usuarios> dniExistente = usuarioRepository.findByDni(bean.getDni());

            if(nombreUsuario != true
               && dniExistente.isEmpty() && correoExistente !=true){

                Usuarios agregarUsuario = new Usuarios();
                agregarUsuario.setNombrecompleto(bean.getNombrecompleto());
                agregarUsuario.setNombreusuario(bean.getNombreusuario());
                agregarUsuario.setApellidosusuario(bean.getApellidosusuario());

                agregarUsuario.setCorreo(bean.getCorreo());
                agregarUsuario.setCorreoalternativo(bean.getCorreoalternativo());
                agregarUsuario.setFechanacimiento(bean.getFechanacimiento());
                agregarUsuario.setDni(bean.getDni());
                agregarUsuario.setCelular(bean.getCelular());
                agregarUsuario.setSexo(bean.getSexo());
                agregarUsuario.setPassword(password);
                Set<Rol> roles = new HashSet<>();
                if(bean.getRoles().contains("user")){
                    roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
                }else if(bean.getRoles().contains("admin")){
                    roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
                }
                agregarUsuario.setRoles(roles);
                agregarUsuario.setFecharegistro(new Date());
                agregarUsuario.setFechaactualizacion(new Date());
                usuarioRepository.save(agregarUsuario);

                //para agregar archivo si esta vacio
                return  new Mensaje(Constantes.SUCCESS, agregarUsuario.getId().toString());
            }else{
                if(!dniExistente.isEmpty()){
                    return  new Mensaje(Constantes.SUCCESS, "El dni ya existe.");

                }else if(correoExistente == true){
                    return  new Mensaje(Constantes.SUCCESS, "El correo ya existe.");
                } else if (nombreUsuario == true) {
                    return  new Mensaje(Constantes.SUCCESS, "El nombre de usuario ya existe.");
                }
                return null;
            }

        }catch (Exception e){
            throw e;
        }
    }
}
