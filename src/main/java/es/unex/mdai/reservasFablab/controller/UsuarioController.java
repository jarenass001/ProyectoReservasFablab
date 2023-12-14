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

	@GetMapping("/")
	public String login(Usuario usuario, Model model) {
		model.addAttribute("usuario", usuario);
		return "Login";
	}

	@PostMapping("/login")
	public String loginComprobar(Usuario usuario, Model model) {
		if (usuario.getUsername().equals("admin") && usuario.getPassword().equals("1234"))
			return "redirect:/admin/menuPrincipal";
		else if(usuario.getUsername().equals("user") && usuario.getPassword().equals("1234")){
			return "redirect:/user/menuPrincipal";
		}else {
			return "redirect:/login";
		}
	}

	@GetMapping("/admin/menuPrincipal")
	public String showAdminMenu(Model model, Usuario usuario, Boolean admin) {
		usuario = usuarioService.findUsuarioByUsername("admin").get();
		admin = true;
		model.addAttribute("usuario", usuario);
		model.addAttribute("admin", admin);
		return "menuAdmin"; // devuelve la vista a renderizar
	}
	@GetMapping("/user/menuPrincipal")
	public String showUserMenu(Model model, Usuario usuario, Boolean admin) {
		usuario = usuarioService.findUsuarioByUsername("user").get();
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
