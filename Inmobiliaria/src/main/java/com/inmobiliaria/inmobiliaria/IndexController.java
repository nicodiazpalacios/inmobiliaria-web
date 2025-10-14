package com.inmobiliaria.inmobiliaria; // Asegúrate de que este paquete sea correcto

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/") // Mapea la URL raíz (http://localhost:8080/)
    public String index() {
        return "index"; // Retorna el nombre del archivo de la plantilla (index.html)
    }
}