package com.proyecto.documento.controller;

import com.github.pagehelper.PageInfo;
import com.proyecto.documento.dto.PaginationDTO;
import com.proyecto.documento.entity.RecuperarPesonaDTO;
import com.proyecto.documento.security.dto.NuevoUsuario;
import com.proyecto.documento.security.entity.Usuarios;
import com.proyecto.documento.services.UsuarioServices;
import com.proyecto.documento.util.Constantes;
import com.proyecto.documento.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioServices usuarioServices;

    @ResponseBody
    @GetMapping("/listaUsuario")
    public ResponseEntity<HashMap<String, Object>> listartodoslosusuario(){
            HashMap<String, Object> salida = new HashMap<>();
            List<Usuarios> respuesta = usuarioServices.listarUsuario();
            salida.put("usuario",respuesta);
            return new ResponseEntity<>(salida, HttpStatus.OK);
    }

    @PostMapping(value = "/nuevo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Mensaje nuevoUsuario(@RequestBody NuevoUsuario nuevoUsuario){
        try {
            /*contraseña a enviar al usuario registrado, por primera vez en la plataforma*/
            String password = Base64.getEncoder().encodeToString((nuevoUsuario.getDni()).getBytes());
            String encriptarcontrasenia = passwordEncoder.encode(password);
            return usuarioServices.save(nuevoUsuario,encriptarcontrasenia, password);
        }catch (Exception e){
            return new Mensaje(Constantes.ERROR, e.getMessage());
        }

    }

    @ResponseBody
    @PutMapping(value = "/actualizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mensaje actualizarUsuario(@RequestParam(name = "codigo") int codigo, @RequestBody Usuarios bean){
        try {
            String encriptarcontrasenia = passwordEncoder.encode(bean.getPassword());
            return usuarioServices.update(codigo,bean, encriptarcontrasenia);
        }catch (Exception e){
            return  new Mensaje(Constantes.ERROR, e.getMessage());
        }
    }

    @PostMapping(value = "/buscarusuario", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Mensaje> buscarUsuario(@RequestParam(name = "codigo") int codigo){
        try {
            return usuarioServices.buscarUsuario(codigo);
        }catch (Exception e){
            throw e;
        }
    }

    @ResponseBody
    @GetMapping(value = "/buscarDni")
    public RecuperarPesonaDTO buscarPersonaDni(@RequestParam(name = "dni") String dni){
        RecuperarPesonaDTO recuperarPersonaDTO = new RecuperarPesonaDTO();
        try {
            recuperarPersonaDTO =  usuarioServices.buscarnumero(dni);
        }catch (Exception e){
            e.getMessage();
        }

        return  recuperarPersonaDTO;
    }
}
