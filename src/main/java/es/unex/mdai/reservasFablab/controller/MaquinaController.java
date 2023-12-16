package es.unex.mdai.reservasFablab.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.unex.mdai.reservasFablab.model.Calendario;
import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.service.CalendarioService;
import es.unex.mdai.reservasFablab.service.MaquinaService;
import es.unex.mdai.reservasFablab.service.ReservaService;
import es.unex.mdai.reservasFablab.service.UsuarioService;

@Controller
public class MaquinaController {

	private UsuarioService usuarioService;
	private MaquinaService maquinaService;
	private CalendarioService calendarioService;
	
	@Autowired
	public MaquinaController(UsuarioService usuarioService, MaquinaService maquinaService, CalendarioService calendarioService) {
		this.usuarioService = usuarioService;
		this.maquinaService = maquinaService;
		this.calendarioService = calendarioService;
		System.out.println("\t Constructor MaquinaController ");
	}
	
	@GetMapping("/user/listarMaquinas/{id}")
	public String verListaMaquinas(@PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		model.addAttribute("listaDeMaquinas", (List<Maquina>) maquinaService.findAllMaquinas());
		if(usuario.getUsername().equals("admin")) {
			model.addAttribute("admin", true);
		}else {
			model.addAttribute("admin", false);
		}
		return "listarMaquinas";
	}
	
	@GetMapping("/admin/addMaquina/{id}")
	public String getAnadirMaquinas(@PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		return "addMaquina";
	}
	
	@PostMapping("/admin/addMaquina/{id}")
	public String anadirMaquinas(@PathVariable("id") Long id, String nombre, Model model) {
		Calendario cal = new Calendario();
		calendarioService.crearCalendario(cal);
		model.addAttribute("listaDeMaquinas", maquinaService.crearMaquina(new Maquina(nombre, cal)));
		return "redirect:/user/listarMaquinas/"+id;
	}
	
	@PostMapping("/admin/deleteMaquina/{id}/{idMaquina}")
	public String deleteUsuario(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id, Model model) {
		maquinaService.deleteMaquinaById(idMaquina);
		return "redirect:/user/listarMaquinas/"+id;
	}
	
	@GetMapping("/user/verMaquina/{id}/{idMaquina}")
	public String verMaquina(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		model.addAttribute("maquina", (Maquina) maquinaService.findMaquinaById(idMaquina).orElseThrow());
		return "mostrarMaquina";
	}
	
	@GetMapping("/user/verMaquina/crearReserva/{id}/{idMaquina}")
	public String crearReserva(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		Maquina m = (Maquina) maquinaService.findMaquinaById(id).orElseThrow();
		model.addAttribute("maquina", m);
		model.addAttribute("fechasDisponibles", calendarioService.findCalendarioByMaquina(m).orElseThrow().getFechasLibres());
		return "crearReserva";
	}
	
	@PostMapping("/user/verMaquina/seleccionarHora/{id}/{idMaquina}")
	public String seleccionarHora(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id, Model model, String fechaReserva) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		ArrayList<String> horasDisp = new ArrayList<String>();	
		horasDisp.add("18:00");
		horasDisp.add("20:00");
		model.addAttribute("horasDisponiblesSB", horasDisp);
		model.addAttribute("maquina", (Maquina) maquinaService.findMaquinaById(idMaquina).orElseThrow());
		return "seleccionarHora";
	}
	
}
