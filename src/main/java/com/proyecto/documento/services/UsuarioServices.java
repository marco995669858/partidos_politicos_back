package com.proyecto.documento.services;

import com.github.pagehelper.PageInfo;
import com.proyecto.documento.dto.PaginationDTO;
import com.proyecto.documento.dto.UsuarioSearchFormDTO;
import com.proyecto.documento.entity.RecuperarPesonaDTO;
import com.proyecto.documento.security.dto.NuevoUsuario;
import com.proyecto.documento.security.entity.Usuarios;
import com.proyecto.documento.util.Mensaje;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioServices {

    List<Usuarios> listarUsuario();

    Mensaje update(int codigo,Usuarios bean, String encriptarcontrasenia);

    ResponseEntity<Mensaje> buscarUsuario(int codigo);

    RecuperarPesonaDTO buscarnumero(String numero);

    Mensaje save(NuevoUsuario bean,String encriptarcontrasenia, String password);
}
