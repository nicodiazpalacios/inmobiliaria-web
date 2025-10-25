package com.inmobiliaria.inmobiliaria;

import com.inmobiliaria.inmobiliaria.model.LoginForm;
import com.inmobiliaria.inmobiliaria.model.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Muestra el formulario de inicio de sesión
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    // Procesa el inicio de sesión
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") LoginForm loginForm) {
        // Lógica de autenticación (ej: service.authenticate(loginForm))

        // Redirige al dashboard si es exitoso
        return "redirect:/dashboard";
    }

    // Muestra el formulario de registro
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    // Procesa el registro de un nuevo usuario
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("registrationForm") RegistrationForm registrationForm) {
        // Lógica para guardar el usuario (ej: userService.save(registrationForm))

        // Redirige al login después de un registro exitoso
        return "redirect:/login";
    }
}