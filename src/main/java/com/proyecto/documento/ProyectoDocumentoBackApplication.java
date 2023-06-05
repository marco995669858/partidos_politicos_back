package com.proyecto.documento;

/*import com.proyecto.documento.services.ArchivosServices;*/
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@SpringBootApplication
public class ProyectoDocumentoBackApplication {
    //implements CommandLineRunner
    /*@Resource
    ArchivosServices archivosServices;*/

    public static void main(String[] args) {
        SpringApplication.run(ProyectoDocumentoBackApplication.class, args);
    }

    /*@Override
    public void run(String... arg) throws Exception{
        archivosServices.deleteAll();
        archivosServices.eliminardata();
        archivosServices.init();
    }*/

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
            }
        };
    }

}
