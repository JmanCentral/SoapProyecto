package co.vinni.soapproyectobase.controladores;

import co.vinni.soapproyectobase.dto.PersonaDto;
import co.vinni.soapproyectobase.servicios.ServiciosPersona;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Log4j2
@Controller

public class ControladorPersona {

    private static final Logger logger = LogManager.getLogger(ControladorPersona.class);

    @Autowired
    ServiciosPersona servicioPersonas;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping({"/personas"})
    public String listarPersonas(Model model) {
        logger.info("Verificando ");
        model.addAttribute("personas", servicioPersonas.obtenerPersonas());
        return "personas";
    }

    @GetMapping("/personas/nuevo")
    public String mostrarFormularioNuevaPersona(Model model) {
        PersonaDto personaDto = new PersonaDto();
        model.addAttribute("persona", personaDto);
        return "crear_persona";
    }

    @PostMapping("/personas")
    public String registrarPersona(@ModelAttribute("persona") PersonaDto persona) {
        servicioPersonas.registrarPersona(persona);
        return "redirect:/personas";
    }

    @GetMapping("/personas/modificar/{numeroDoc}")
    public String mostrarFormularioEditarPersona(@PathVariable long numeroDoc, Model model) {
        PersonaDto personaDto = new PersonaDto();
        model.addAttribute("persona", servicioPersonas.obtenerPersonasPorNumeroDoc(numeroDoc));
        return "editar_persona";
    }

    @PostMapping("/personas/{numeroDoc}")
    public String modificarPersona(@PathVariable long numeroDoc, @ModelAttribute("persona") PersonaDto personaDto , Model model) {
        model.addAttribute("persona", servicioPersonas.modificarPersona(personaDto));
        return "redirect:/personas";
    }

    @GetMapping("/personas/{numeroDoc}")
    public String eliminarPersona(@PathVariable long numeroDoc) {
        servicioPersonas.eliminarPersona(numeroDoc);
        return "redirect:/personas";
    }
}
