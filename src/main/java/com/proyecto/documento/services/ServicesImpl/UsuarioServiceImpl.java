package com.proyecto.documento.services.ServicesImpl;

import com.proyecto.documento.dto.PaginationDTO;
import com.proyecto.documento.entity.RecuperarPesonaDTO;
import com.proyecto.documento.repository.UsuarioRepository;
import com.proyecto.documento.security.dto.NuevoUsuario;
import com.proyecto.documento.security.entity.Rol;
import com.proyecto.documento.security.entity.Usuarios;
import com.proyecto.documento.security.enums.RolNombre;
import com.proyecto.documento.security.service.RolService;
import com.proyecto.documento.services.UsuarioServices;
import com.proyecto.documento.util.Constantes;
import com.proyecto.documento.util.Mensaje;
import com.telesign.MessagingClient;
import com.telesign.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UsuarioServiceImpl implements UsuarioServices {
    private final static Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Autowired
    private UsuarioRepository repositoryUsuario;

    @Autowired
    RolService rolService;

    @Value("${url.dni}")
    String urldni;

    @Value("${token.url}")
    String token;

    @Override
    public List<Usuarios> listarUsuario() {
        return repositoryUsuario.findAll();
    }



    @Override
    public Mensaje update(int codigo,Usuarios bean, String encriptarcontrasenia) {
        Usuarios buscarUsuario = repositoryUsuario.findById(codigo).orElse(null);
        try {
            String correoExistente = repositoryUsuario.buscarCorreoExisteDistintoalRegistrado(bean.getCorreo(), codigo);
           /* boolean nombreUsuario = repositoryUsuario.existsByNombreusuario(bean.getNombreusuario());
            List<Usuarios> dniExistente = repositoryUsuario.findByDni(bean.getDni());*/

            if(correoExistente == null){

                Usuarios agregarUsuario = new Usuarios();
                agregarUsuario.setNombrecompleto(buscarUsuario.getNombrecompleto());
                agregarUsuario.setNombreusuario(buscarUsuario.getNombreusuario());
                agregarUsuario.setApellidosusuario(buscarUsuario.getApellidosusuario());

                agregarUsuario.setCorreo(buscarUsuario.getCorreo());
                agregarUsuario.setCorreoalternativo(buscarUsuario.getCorreoalternativo());
                agregarUsuario.setFechanacimiento(buscarUsuario.getFechanacimiento());
                agregarUsuario.setDni(buscarUsuario.getDni());
                agregarUsuario.setCelular(buscarUsuario.getCelular());
                agregarUsuario.setSexo(buscarUsuario.getSexo());
                agregarUsuario.setPassword(encriptarcontrasenia);
                /*Set<Rol> roles = new HashSet<>();
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
                if(bean.getRoles().contains("admin")){
                    roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
                }*/
                System.out.println(buscarUsuario.getRoles());
                agregarUsuario.setRoles(buscarUsuario.getRoles());
                agregarUsuario.setFecharegistro(buscarUsuario.getFecharegistro());
                agregarUsuario.setFechaactualizacion(new Date());
                agregarUsuario.setId(codigo);
                repositoryUsuario.save(agregarUsuario);

                //para agregar archivo si esta vacio
                return  new Mensaje(Constantes.SUCCESS, "Se actualizo la contraseña");
           }else{
                /*if(!dniExistente.isEmpty()){
                    return  new Mensaje(Constantes.SUCCESS, "El dni ya existe.");

                }else */if(!correoExistente.isEmpty() || !correoExistente.isBlank()){
                    return  new Mensaje(Constantes.SUCCESS, "El correo ya existe.");
                }/*else if (nombreUsuario == true) {
                    return  new Mensaje(Constantes.SUCCESS, "El nombre de usuario ya existe.");
                }*/
                return null;
            }

        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public ResponseEntity<Mensaje> buscarUsuario(int codigo) {
        HashMap<String, Object> salida = new HashMap<>();
        try {
            Usuarios buscarUsuario = repositoryUsuario.findById(codigo).orElse(null);
            if(buscarUsuario != null){
                salida.put("usuario", buscarUsuario);
            }else{
                Mensaje mensaje = new Mensaje(Constantes.ERROR, Constantes.MENSAJE_BUSQUEDA_NO_ENCONTRADO);
                return new ResponseEntity(mensaje,HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            throw e;
        }

        return new ResponseEntity(salida, HttpStatus.OK);
    }

    @Override
    public RecuperarPesonaDTO buscarnumero(String numero) {
        String url = "";
        try {
            url += createUrl(urldni);
            url += "numero=" + numero;
            logger.debug("conectando servicio recuperar persona natural:" + url);
            return getRecuperarPersonaDTO(url);
        }catch (Exception e){
            logger.error("Error al recuperar persona:" + url);
            logger.error("PRINTSTACKTRACE", e);
            return new RecuperarPesonaDTO(Constantes.ERROR, Constantes.MENSAJE_DNI_FALLIDO);
        }
    }

    private String createUrl(String urldni) {
        String url = urldni;
        return url;
    }

    private RecuperarPesonaDTO getRecuperarPersonaDTO(String url) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.set("Accept", "application/json");
        HttpEntity<Object> entidadPedido = new HttpEntity<Object>(headers);
        ResponseEntity<RecuperarPesonaDTO> entidadRespuesta = rest.exchange(url, HttpMethod.GET, entidadPedido, RecuperarPesonaDTO.class);
        RecuperarPesonaDTO recuperarPersonaDTO = entidadRespuesta.getBody();
        recuperarPersonaDTO.setEstado(Constantes.SUCCESS);
        recuperarPersonaDTO.setMensaje(Constantes.MENSAJE_DNI_CORRECTO);
        return recuperarPersonaDTO;
    }

    @Override
    public Mensaje save(NuevoUsuario bean, String encriptarcontrasenia, String password) {
        try {
            boolean correoExistente = repositoryUsuario.existsByCorreo(bean.getCorreo());
            boolean nombreUsuario = repositoryUsuario.existsByNombreusuario(bean.getNombreusuario());
            List<Usuarios> dniExistente = repositoryUsuario.findByDni(bean.getDni());


            if(nombreUsuario != true
                    && dniExistente.isEmpty() && correoExistente !=true){

                /*para el usuario*/
                String nombrePerfil = obtenerUsuarioPerfil(bean.getNombrecompleto(), bean.getApellidosusuario());
                Usuarios agregarUsuario = new Usuarios();
                agregarUsuario.setNombrecompleto(bean.getNombrecompleto());
                agregarUsuario.setNombreusuario(nombrePerfil);
                agregarUsuario.setApellidosusuario(bean.getApellidosusuario());
                agregarUsuario.setCorreo(bean.getCorreo());
                agregarUsuario.setCorreoalternativo(bean.getCorreoalternativo());
                agregarUsuario.setFechanacimiento(bean.getFechanacimiento());
                agregarUsuario.setDni(bean.getDni());
                agregarUsuario.setCelular(bean.getCelular());
                agregarUsuario.setSexo(bean.getSexo());
                agregarUsuario.setPassword(encriptarcontrasenia);
                Set<Rol> roles = new HashSet<>();
                if(bean.getRoles().contains("user")){
                    roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
                }else if(bean.getRoles().contains("admin")){
                    roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
                }
                agregarUsuario.setRoles(roles);
                agregarUsuario.setFecharegistro(new Date());
                agregarUsuario.setFechaactualizacion(new Date());
                repositoryUsuario.save(agregarUsuario);
                /*para enviar el sms*/
                //String respuesta = this.enviarSms(bean.getCelular(), bean.getCorreo(),password);
                //para agregar archivo si esta vacio
                //return  new Mensaje(Constantes.SUCCESS + '\n' + respuesta, agregarUsuario.getId().toString());
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

    public String enviarSms(String celular, String usuario, String password){
        String numero = "51"+celular;
        String phoneNumber = System.getenv().getOrDefault("PHONE_NUMBER", numero);
        String message = "Su credenciales de la plataforma son: \n Usuario: " + usuario + "\n" + "Contraseña: " +password + "\n" + "Bienvenido a la plataforma.";
        String messageType = "ARN";
        try {
            MessagingClient messagingClient = new MessagingClient(Constantes.customerId, Constantes.apiKey);
            RestClient.TelesignResponse telesignResponse = messagingClient.message(phoneNumber, message, messageType, null);
            System.out.println(telesignResponse.statusCode);
            System.out.println(telesignResponse.body);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Se envío el sms.";
    }

    public String obtenerUsuarioPerfil(String nombre,String apellido) {
        String nombrePerfilObtenido = null;
        try {
            String obtenerNombre = nombre.substring(0, 3);
            String obtenerApellido = apellido.substring(0, 3);
            nombrePerfilObtenido = obtenerNombre + obtenerApellido;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nombrePerfilObtenido.toLowerCase();
    }


}