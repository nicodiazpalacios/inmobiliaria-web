package com.inmobiliaria.inmobiliaria;

import com.inmobiliaria.inmobiliaria.model.ContactForm; // <-- ¡NUEVA IMPORTACIÓN!
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

    // --- Métodos de LOGIN ---
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") LoginForm loginForm) {
        // Lógica de autenticación
        return "redirect:/dashboard";
    }

    // --- Métodos de REGISTRO ---
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("registrationForm") RegistrationForm registrationForm) {
        // Lógica para guardar el usuario
        return "redirect:/login";
    }

    // --- Métodos de CONTACTO (NUEVOS) ---

    // Muestra el formulario de contacto (th:action en el HTML apunta a
    // /contactar-agente)
    @GetMapping("/contactar-agente")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact"; // Retorna el nombre de la plantilla contact.html
    }

    // Procesa el envío del formulario de contacto
    @PostMapping("/contactar-agente")
    public String processContactForm(@ModelAttribute("contactForm") ContactForm contactForm) {

        // **Lógica del Contacto:** Aquí es donde enviarías el email o guardarías la
        // consulta.
        // Ejemplo de log para ver los datos:
        System.out.println("Consulta recibida de: " + contactForm.getName());
        System.out.println("Mensaje: " + contactForm.getMessage());

        // Redirige a la página principal o a una página de confirmación
        return "redirect:/";
    }
}