package es.unex.mdai.reservasFablab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.service.UsuarioService;

@Controller
public class UsuarioController {
	UsuarioService usuarioService;
	
	@GetMapping("/login")    
    public String login(Usuario usuario, Model model){  	
        return "Login"; 
    }
	@PostMapping("/login")
	public String loginComprobar(Usuario usuario, Model model) {
		if(usuario.getUsername().equals("Pepito") && usuario.getPassword().equals("1234"))
		return "index";
		else {
			return "Login";
		}
	}
}
