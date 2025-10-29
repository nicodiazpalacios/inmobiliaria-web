package com.inmobiliaria.inmobiliaria;

import com.inmobiliaria.inmobiliaria.model.ContactForm;
import com.inmobiliaria.inmobiliaria.model.LoginForm;
import com.inmobiliaria.inmobiliaria.model.RegistrationForm;
import com.inmobiliaria.inmobiliaria.model.Property;
import com.inmobiliaria.inmobiliaria.model.StaffLoginForm;
import com.inmobiliaria.inmobiliaria.model.PropertyDetail;
import com.inmobiliaria.inmobiliaria.model.DashboardMetrics;
import com.inmobiliaria.inmobiliaria.model.UpcomingDue;
import com.inmobiliaria.inmobiliaria.model.ActiveListing;
import com.inmobiliaria.inmobiliaria.model.Employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    // -------------------------------------------------------------------------
    // --- RUTAS PÚBLICAS Y DE FORMULARIOS ---
    // -------------------------------------------------------------------------

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "redirect:/dashboard";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("registrationForm") RegistrationForm registrationForm) {
        return "redirect:/login";
    }

    @GetMapping("/contactar-agente")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    @PostMapping("/contactar-agente")
    public String processContactForm(@ModelAttribute("contactForm") ContactForm contactForm) {
        System.out.println("Consulta recibida de: " + contactForm.getName());
        return "redirect:/";
    }

    // -------------------------------------------------------------------------
    // --- STAFF LOGIN Y DASHBOARDS ---
    // -------------------------------------------------------------------------

    @GetMapping("/staff/login")
    public String showStaffLoginForm(Model model) {
        model.addAttribute("staffLoginForm", new StaffLoginForm());
        return "staff_login";
    }

    @PostMapping("/staff/login")
    public String processStaffLogin(@ModelAttribute("staffLoginForm") StaffLoginForm staffLoginForm) {
        // Lógica de autenticación de staff (debería redirigir según el rol)
        // Por defecto, redirigimos al dashboard estándar:
        return "redirect:/staff/dashboard";
    }

    // Panel de Control Estándar de Empleado (sin tabla de empleados)
    @GetMapping("/staff/dashboard")
    public String showStaffDashboard(Model model) {

        User currentUser = new User("Ethan Carter", "Staff");

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("metrics", getDashboardMetrics("Staff"));
        model.addAttribute("recentActivity", getRecentActivity("Staff"));
        model.addAttribute("upcomingDues", getUpcomingDues());
        model.addAttribute("activeListings", getActiveListings());

        return "staff_dashboard";
    }

    // Panel de Control Maestro de Administración (con tabla de empleados)
    @GetMapping("/staff/admin-dashboard")
    public String showAdminDashboard(Model model) {

        User currentUser = new User("Olivia Bennett", "Admin");

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("metrics", getDashboardMetrics("Admin"));
        model.addAttribute("recentActivity", getRecentActivity("Admin"));
        model.addAttribute("upcomingDues", getUpcomingDues());
        model.addAttribute("activeListings", getActiveListings());
        model.addAttribute("employees", getEmployees());

        return "admin_dashboard";
    }

    // -------------------------------------------------------------------------
    // --- BUSQUEDA Y DETALLES DE PROPIEDAD ---
    // -------------------------------------------------------------------------

    @GetMapping("/search")
    public String searchResults(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Property> results = getMockProperties(query);
        model.addAttribute("properties", results);
        model.addAttribute("searchQuery", query);
        return "search";
    }

    @GetMapping("/propiedad/{id}")
    public String showPropertyDetails(@PathVariable("id") Long id, Model model) {
        PropertyDetail detail = getMockPropertyDetail(id);
        if (detail == null) {
            return "redirect:/search";
        }
        model.addAttribute("propertyDetail", detail);
        return "property_detail";
    }

    // -------------------------------------------------------------------------
    // --- SIMULACION DE DATOS (MOCK LOGIC) ---
    // -------------------------------------------------------------------------

    // Simulación: Clase de usuario
    private static class User {
        private String name;
        private String role;

        public User(String name, String role) {
            this.name = name;
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }
    }

    // Simulación: Métricas por rol
    private DashboardMetrics getDashboardMetrics(String role) {
        DashboardMetrics metrics = new DashboardMetrics();
        metrics.setActiveListings(role.equals("Admin") ? 15 : 3);
        metrics.setActiveContracts(role.equals("Admin") ? 8 : 2);
        metrics.setPendingPayments(role.equals("Admin") ? 4 : 1);
        return metrics;
    }

    // Simulación: Actividad reciente
    private List<Map<String, String>> getRecentActivity(String role) {
        return Arrays.asList(
                Map.of("icon", "File", "description", "Nuevo contrato de alquiler creado para 123 Maple Street",
                        "timeAgo", "Hace 2 días"),
                Map.of("icon", "CurrencyDollar", "description", "Pago recibido para 456 Oak Avenue", "timeAgo",
                        "Hace 5 días"));
    }

    // Simulación: Vencimientos
    private List<UpcomingDue> getUpcomingDues() {
        UpcomingDue d1 = new UpcomingDue();
        d1.setAddress("123 Maple Street");
        d1.setType("Alquiler");
        d1.setDueDate(LocalDate.of(2025, 10, 15));
        d1.setAmount(new BigDecimal("1500.00"));
        UpcomingDue d2 = new UpcomingDue();
        d2.setAddress("456 Oak Avenue");
        d2.setType("Venta");
        d2.setDueDate(LocalDate.of(2025, 11, 30));
        d2.setAmount(new BigDecimal("5000.00"));
        return Arrays.asList(d1, d2);
    }

    // Simulación: Listados Activos
    private List<ActiveListing> getActiveListings() {
        ActiveListing l1 = new ActiveListing();
        l1.setId(1L);
        l1.setAddress("123 Maple Street");
        l1.setStatus("Activo");
        ActiveListing l2 = new ActiveListing();
        l2.setId(2L);
        l2.setAddress("456 Oak Avenue");
        l2.setStatus("Activo");
        ActiveListing l3 = new ActiveListing();
        l3.setId(3L);
        l3.setAddress("789 Pine Lane");
        l3.setStatus("Pausado");
        return Arrays.asList(l1, l2, l3);
    }

    // Simulación: Empleados
    private List<Employee> getEmployees() {
        Employee e1 = new Employee();
        e1.setId(10L);
        e1.setName("Ethan Carter");
        e1.setRole("Agente");
        e1.setStatus("Activo");
        Employee e2 = new Employee();
        e2.setId(20L);
        e2.setName("Sophia Clark");
        e2.setRole("Gerente");
        e2.setStatus("Activo");
        Employee e3 = new Employee();
        e3.setId(30L);
        e3.setName("Liam Walker");
        e3.setRole("Asistente");
        e3.setStatus("Inactivo");
        return Arrays.asList(e1, e2, e3);
    }

    // Simulación: Propiedades para /search
    private List<Property> getMockProperties(String query) {
        Property p1 = new Property(101L, "Calle Arce 123, Madrid", "Casa", 2005, 3, 2, 1500,
                new BigDecimal("1200000.00"),
                "https://lh3.googleusercontent.com/aida-public/AB6AXuCA2X1J9Uya4KLo8kVdwPUKTmW4uCqQK2eXtTTTAF6fGoWWEZwy5gBovzCQ_93KR5o5TRMDm7Bn7g4l3_EZLndcWAPIXaYvL9e-5ZwtcM1XIn0iDcSrIUD_oVko7-llZBdsbtL96K9UOEmSqnYhe4T8EDr2F8SrOBcFAx1e9reaMETbCaRiUdn5aFyUHR4mnz-k0fWdKUgm-PMSAtRGI35LrytFocgY7UxlRdX043-ALPXZHF3yI5N_3mW0MsQ3AXAj6Sa5dpdGX_U");
        Property p2 = new Property(102L, "Avenida Roble 456, Madrid", "Apartamento", 2010, 4, 3, 2000,
                new BigDecimal("1500000.00"),
                "https://lh3.googleusercontent.com/aida-public/AB6AXuAHI-BrbPJk9-Xcv6r6_D4aCmO1xzRmNOuR6pvvjAOK0jOOuLv5OT_DhZEPtr_dIBv3NAH7mBfxevA1aR_QMd_R2tFjAzCl1w3AipkePB3HHvJOoIemk-kiVOal66aSYpRu8ErMAwOXJ4klImV6nktxWlZ3prvfeAfXGCYW8IaFvkv8gzSl2pVrpGqqDz9E1o9mUxsUfw6OvzA0elOvMOZ0fi-6dypoBvrPwA1_92X8_3sYMxgdyW-5nBFMX9PTGedzv8oq-nxE4iE");
        Property p3 = new Property(103L, "Calle Pino 789, Madrid", "Apartamento", 1998, 2, 1, 1000,
                new BigDecimal("800000.00"),
                "https://lh3.googleusercontent.com/aida-public/AB6AXuCcrnn4m-M7c4lLiBBvJGSgg6uI7BGS7_88_RqACq-oLpsBsB9EiCXUthbU1OwaNzRmmPdk7pEsix14RpC2NePoRmkpoSqH6oNxaMTpV2AJmn1sNTo2WnHHiRHJXklIDw1ezOWLXMVbLFIsHKl5glZyiB-Yxkqu-bw8jbvJ0ZjZ5FTO9jiJVCZ7J97ENDaBGPVLyhWdinVZ-V_Rm7bWF5yyTsmRYg0Xwh2Cai4FOijkh-VFiQbkJwIQmEiNXI9w3Ajf-TdZZBtGSWU");

        if (query != null && !query.isEmpty()) {
            String lowerQuery = query.toLowerCase();
            if (lowerQuery.contains("apartamento")) {
                return Arrays.asList(p2, p3);
            } else if (lowerQuery.contains("casa")) {
                return Arrays.asList(p1);
            }
        }
        return Arrays.asList(p1, p2, p3);
    }

    // Simulación: Detalle de Propiedad
    private PropertyDetail getMockPropertyDetail(Long id) {
        if (id.equals(101L)) {
            return new PropertyDetail(101L, "Espectacular Casa en la zona norte", "Hermosa casa...", "Casas", 2500, 4,
                    3, 2, new BigDecimal("3500000.00"),
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuDHhgcHdlXiQq20FJTeD2DThEsYfz-dflfT7_wPsjCbNFpSEkrg3_NMhlUkD7EIvgDhkDFD1IiBTNPTQ8PiHesoziDeLeLwfAAxU4x5HVPO9Yy6mOKPkI0Pkle0noSwvKJpf8hAwjnrkFWT1kuTW-xGqDQIcEiKeMQXYYbmILlRMigaZ_efJWVrsH89lXQghc1XtynT0w4e3Iyk8SWYuKPJfm7RdyLTxeAbK0FYDkd9PNOTP0SBOqgp0CLDgry7yiCCzBkQlEpBjno",
                    "Isabel García", "Agente de bienes raíces senior",
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuB2b7TmL94EzYrQ55_IaGj5DPXkqoo9jKl3PUlrJzP6GKTii98sdXeA5Ik5Y9PyZS9MLLb_FIa9Ezgd0ycuCrhA3XBQZPaZZFAgmu7HPudypDZk6oCgH5_4eYyIrwy6Dcia7qfvijKedp1VUscCC_8Iv3SACHinKSdAQB6euSikdmRTm1pYirVVoWc3RlT86_eTgzdOF4JVK1tXCUBQZ1ZGDrNHxgD2mTAw91a3v3MvsTh-P1sKpFr7Se_SN8a7zX0fxdX9jRRdPFw");
        } else if (id.equals(102L)) {
            return new PropertyDetail(102L, "Apartamento con vistas al río", "Lujoso apartamento...", "Apartamentos",
                    1200, 2, 2, 1, new BigDecimal("2500.00"),
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuAMz5XlOTCBDaayKsiqg7kdMjGLiCDOk2LxLK0jN0Kf8t5mjVfeFVIfyeqJB5S6JvLy4UoKDhmyI8aMQfsvM6zThUAXFNaDCT6i6emsWT5DEHtoDk_AJN57UsAxEavHsqhze9QJujfd7LfBMlLfIHuAfvxeP2TgtmMfe1s9ISRN7r2tU48lBx2AiqALCfIDNG4U337kGogZAqAG9ztFjB-SzQUlASvCX80emmwu3FlSSiUJRdlzgSZAtUMrFPcpXTZN4Drf6sPbq5k6",
                    "Javier Ruiz", "Agente de alquileres",
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuAMz5XlOTCBDaayKsiqg7kdMjGLiCDOk2LxLK0jN0Kf8t5mjVfeFVIfyeqJB5S6JvLy4UoKDhmyI8aMQfsvM6zThUAXFNaDCT6i6emsWT5DEHtoDk_AJN57UsAxEavHsqhze9QJujfd7LfBMlLfIHuAfvxeP2TgtmMfe1s9ISRN7r2tU48lBx2AiqALCfIDNG4U337kGogZAqAG9ztFjB-SzQUlASvCX80emmwu3FlSSiUJRdlzgSZAtUMrFPcpXTZN4Drf6sPbq5k7");
        }
        return null;
    }
}