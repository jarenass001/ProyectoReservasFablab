package es.unex.mdai.reservasFablab.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.service.FechaService;
import es.unex.mdai.reservasFablab.service.ReservaService;
import es.unex.mdai.reservasFablab.service.UsuarioService;

@Controller
public class UsuarioController {
	private final UsuarioService usuarioService;
	private final ReservaService reservaService;
	private final FechaService fechaService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService, ReservaService reservaService, FechaService fechaService) {
		super();
		this.usuarioService = usuarioService;
		this.reservaService = reservaService;
		this.fechaService = fechaService;
	}

	@GetMapping("/")
	public String login(Usuario usuario, Model model) {
		model.addAttribute("usuario", usuario);
		return "Login";
	}

	@PostMapping("/login")
	public String loginComprobar(Usuario usuario, Model model, boolean admin) {
		if (usuario.getUsername().equals("admin") && usuario.getPassword().equals("1234")) {
			return "redirect:/admin/menuPrincipal";
		}else if(usuario.getUsername().equals("user") && usuario.getPassword().equals("1234")){
			return "redirect:/user/menuPrincipal";
		}else {
			return "redirect:/";
		}
	}

	@GetMapping("/admin/menuPrincipal")
	public String showAdminMenu(Usuario usuario, Model model, boolean admin) {
		usuario = usuarioService.findUsuarioByUsername("admin").get();
		admin = true;
		model.addAttribute("usuario", usuario);
		if(usuario.getUsername().equals("admin")) {
			model.addAttribute("admin", true);
		}else {
			model.addAttribute("admin", false);
		}
		return "menuAdmin";
	}
	@GetMapping("/user/menuPrincipal")
	public String showUserMenu(Usuario usuario, Model model, boolean admin) {
		usuario = usuarioService.findUsuarioByUsername("user").get();
		admin = false;
		model.addAttribute("usuario", usuario);
		model.addAttribute("admin", admin);
		return "menuUser";
	}
	
	@GetMapping("/admin/menuPrincipal/{id}")
	public String showAdminMenu2(@PathVariable("id") Long id, Usuario usuario, Model model, boolean admin) {
		usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		model.addAttribute("admin", admin);
		return "menuAdmin";
	}
	@GetMapping("/user/menuPrincipal/{id}")
	public String showUserMenu2(@PathVariable("id") Long id, Usuario usuario, Model model, boolean admin) {
		usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		if(usuario.getUsername().equals("admin")) {
			model.addAttribute("admin", true);
		}else {
			model.addAttribute("admin", false);
		}
		return "menuUser";
	}
	@GetMapping("/admin/ListarUsuarios/{id}")
	public String showUsersTable(@PathVariable("id") Long id, Usuario usuario, Model model) {
		usuario = usuarioService.findUsuarioById(id).get();
		List<Usuario> usuarioList = (List<Usuario>) usuarioService.findAllUsers();
		model.addAttribute("usuarios", usuarioList);
		return "ListarUsuarios"; // devuelve la vista a renderizar
	}
	@GetMapping("/user/VerUsuario/{id}/{idConsulta}")
	// @PathVariable: El parámetro forma parte de la URL
	public String showUpdateUsuarioForm(@PathVariable("idConsulta") Long usuarioId, @PathVariable("id") Long id, Model model) {
		model.addAttribute("usuarioBuscar", usuarioService.findUsuarioById(usuarioId).get());
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		if(usuario.getUsername().equals("admin")) {
			model.addAttribute("admin", true);
		}else {
			model.addAttribute("admin", false);
		}
		return "VerUsuario";
	}
	@GetMapping("/admin/addUsuario/{id}")
    public String showAddUsuarioForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("usuario", usuarioService.findUsuarioById(id).get());
        return "addUsuario";
    }
	@PostMapping("/admin/addUsuario/{id}")
	public String addUsuario(@PathVariable("id") Long id, String username, String password, Model model) {
		usuarioService.crearUsuario(new Usuario(username, password));
		return "redirect:/admin/ListarUsuarios/"+id;
	}
	@PostMapping("/admin/deleteUsuario/{id}/{idBorrar}")
    public String deleteUsuario(@PathVariable("idBorrar") Long usuarioId,@PathVariable("id") Long id, Model model) {
        usuarioService.deleteUsuarioById(usuarioId);        
        return "redirect:/admin/ListarUsuarios/"+id;
    }
	
	@GetMapping("/user/verReservas/{id}")
	public String verReservas(@PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		model.addAttribute("listaDeReservas", reservaService.findReservasByUsuario(usuario));
		return "listarReservasUser";
	}
	
	@PostMapping("/user/deleteReserva/{id}/{reservaId}")
	public String eliminarReserva(@PathVariable("id") Long id, @PathVariable("reservaId") Long reservaId, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		Reserva r = reservaService.findReservaById(reservaId).get();
		fechaService.createFecha(new Date(r.getFecha().getYear(), r.getFecha().getMonth(), r.getFecha().getDate()), String.valueOf(r.getFecha().getHours()+":"+r.getFecha().getMinutes()+"0"), r.getMaquina().getCalendario());
		reservaService.deleteReservaById(reservaId);
		return "redirect:/user/verReservas/"+ id;
	}
}
