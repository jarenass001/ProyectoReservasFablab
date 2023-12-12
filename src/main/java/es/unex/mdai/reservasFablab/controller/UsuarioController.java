package es.unex.mdai.reservasFablab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.service.UsuarioService;

@Controller
public class UsuarioController {
	private final UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@GetMapping("/login")
	public String login(Usuario usuario, Model model) {
		return "Login";
	}

	@PostMapping("/login")
	public String loginComprobar(Usuario usuario, Model model) {
		if (usuario.getUsername().equals("Pepito") && usuario.getPassword().equals("1234"))
			return "index";
		else {
			return "Login";
		}
	}

	@GetMapping("/admin/menuPrincipal")
	public String showAdminMenu(Model model, Usuario usuario, Boolean admin) {
		usuarioService.crearUsuario(new Usuario("A", "1234"));
		usuario = usuarioService.findAllUsers().iterator().next();
		admin = true;
		model.addAttribute("usuario", usuario);
		model.addAttribute("admin", admin);
		return "menuAdmin"; // devuelve la vista a renderizar
	}
	@GetMapping("/user/menuPrincipal")
	public String showUserMenu(Model model, Usuario usuario, Boolean admin) {
		usuarioService.crearUsuario(new Usuario("B", "1234"));
		usuario = usuarioService.findAllUsers().iterator().next();
		admin = false;
		model.addAttribute("usuario", usuario);
		model.addAttribute("admin", admin);
		return "menuUser"; // devuelve la vista a renderizar
	}
	@GetMapping("/admin/ListarUsuarios")
	public String showUsersTable(Model model) {
		List<Usuario> usuarioList = (List<Usuario>) usuarioService.findAllUsers();
		model.addAttribute("usuarios", usuarioList);
		return "ListarUsuarios"; // devuelve la vista a renderizar
	}
	@GetMapping("/user/VerUsuario/{id}/{admin}")
	// @PathVariable: El parámetro forma parte de la URL
	public String showMyUserData(@PathVariable("id") Long usuarioId, Model model,@PathVariable("admin") Boolean admin) {
		model.addAttribute("usuario", usuarioService.findUsuarioById(usuarioId).get());
		model.addAttribute("admin", admin);
		return "VerUsuario";
	}
	@GetMapping("/user/VerUsuario/{id}")
	// @PathVariable: El parámetro forma parte de la URL
	public String showUpdateUsuarioForm(@PathVariable("id") Long usuarioId, Model model, Boolean admin) {
		model.addAttribute("usuario", usuarioService.findUsuarioById(usuarioId).get());
		model.addAttribute("admin", admin);
		return "VerUsuario";
	}

	@GetMapping("/admin/addUsuario")
    public String showAddUsuarioForm(Usuario usuario) {    	 	
        return "addUsuario";
    }
	@PostMapping("/admin/addUsuario")
	public String addUsuario(Usuario usuario, Model model) {
		model.addAttribute("usuarios", usuarioService.crearUsuario(usuario));
		return "redirect:/admin/ListarUsuarios";
	}
	@PostMapping("/admin/deleteUsuario/{id}")
    public String deleteUsuario(@PathVariable("id") Long usuarioId, Model model) {
        model.addAttribute("usuarios", usuarioService.deleteUsuarioById(usuarioId));        
        return "redirect:/admin/ListarUsuarios";
    }
}
