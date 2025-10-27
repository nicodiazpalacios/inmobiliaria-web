package com.inmobiliaria.inmobiliaria;

import com.inmobiliaria.inmobiliaria.model.ContactForm;
import com.inmobiliaria.inmobiliaria.model.LoginForm;
import com.inmobiliaria.inmobiliaria.model.RegistrationForm;
import com.inmobiliaria.inmobiliaria.model.StaffLoginForm; // <-- ¡NUEVA IMPORTACIÓN para el login de Staff!
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

    // -------------------------------------------------------------------------
    // --- MÉTODOS DE LOGIN DE USUARIO ---
    // -------------------------------------------------------------------------

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

    // -------------------------------------------------------------------------
    // --- MÉTODOS DE REGISTRO ---
    // -------------------------------------------------------------------------

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

    // -------------------------------------------------------------------------
    // --- MÉTODOS DE CONTACTO ---
    // -------------------------------------------------------------------------

    @GetMapping("/contactar-agente")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact"; // Retorna el nombre de la plantilla contact.html
    }

    @PostMapping("/contactar-agente")
    public String processContactForm(@ModelAttribute("contactForm") ContactForm contactForm) {
        // Lógica para enviar el email o guardar la consulta
        System.out.println("Consulta recibida de: " + contactForm.getName());
        System.out.println("Mensaje: " + contactForm.getMessage());

        // Redirige a la página principal o a una página de confirmación
        return "redirect:/";
    }

    // -------------------------------------------------------------------------
    // --- MÉTODOS DE LOGIN DE PERSONAL (STAFF) ---
    // -------------------------------------------------------------------------

    /**
     * Muestra el formulario de inicio de sesión de personal.
     */
    @GetMapping("/staff/login")
    public String showStaffLoginForm(Model model) {
        model.addAttribute("staffLoginForm", new StaffLoginForm()); // Objeto para el formulario de staff
        return "staff_login"; // Retorna el archivo staff_login.html
    }

    /**
     * Procesa el formulario de inicio de sesión de personal.
     */
    @PostMapping("/staff/login")
    public String processStaffLogin(@ModelAttribute("staffLoginForm") StaffLoginForm staffLoginForm) {
        // Lógica de autenticación de personal

        // Redirige al dashboard de personal si la autenticación es exitosa
        return "redirect:/staff/dashboard";
    }
}