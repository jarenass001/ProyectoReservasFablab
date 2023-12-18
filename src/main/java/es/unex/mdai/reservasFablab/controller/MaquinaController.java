package es.unex.mdai.reservasFablab.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.unex.mdai.reservasFablab.model.Calendario;
import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.service.CalendarioService;
import es.unex.mdai.reservasFablab.service.FechaService;
import es.unex.mdai.reservasFablab.service.MaquinaService;
import es.unex.mdai.reservasFablab.service.ReservaService;
import es.unex.mdai.reservasFablab.service.UsuarioService;

@Controller
public class MaquinaController {

	private UsuarioService usuarioService;
	private MaquinaService maquinaService;
	private CalendarioService calendarioService;
	private ReservaService reservaService;
	private FechaService fechaService;

	@Autowired
	public MaquinaController(UsuarioService usuarioService, MaquinaService maquinaService,
			CalendarioService calendarioService, ReservaService reservaService, FechaService fechaService) {
		this.usuarioService = usuarioService;
		this.maquinaService = maquinaService;
		this.calendarioService = calendarioService;
		this.reservaService = reservaService;
		this.fechaService = fechaService;
		System.out.println("\t Constructor MaquinaController ");
	}

	@GetMapping("/user/listarMaquinas/{id}")
	public String verListaMaquinas(@PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		model.addAttribute("listaDeMaquinas", (List<Maquina>) maquinaService.findAllMaquinas());
		if (usuario.getUsername().equals("admin")) {
			model.addAttribute("admin", true);
		} else {
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
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		Calendario cal = new Calendario();
		calendarioService.crearCalendario(cal);
		model.addAttribute("listaDeMaquinas", maquinaService.crearMaquina(new Maquina(nombre, cal)));
		return "redirect:/user/listarMaquinas/" + id;
	}

	@PostMapping("/admin/deleteMaquina/{id}/{idMaquina}")
	public String deleteMaquina(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		if(!reservaService.findReservasByMaquina(maquinaService.findMaquinaById(idMaquina).get()).equals(null)) {
			Iterable<Reserva> reservas = reservaService.findReservasByMaquina(maquinaService.findMaquinaById(idMaquina).get());
			reservas.forEach(r -> reservaService.deleteReservaById(r.getId()));
		}
		maquinaService.deleteMaquinaById(idMaquina);
		return "redirect:/user/listarMaquinas/" + id;
	}

	@GetMapping("/user/verMaquina/{id}/{idMaquina}")
	public String verMaquina(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		if (usuario.getUsername().equals("admin")) {
			model.addAttribute("admin", true);
		} else {
			model.addAttribute("admin", false);
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("maquina", (Maquina) maquinaService.findMaquinaById(idMaquina).orElseThrow());
		return "mostrarMaquina";
	}

	@GetMapping("/user/verMaquina/crearReserva/{id}/{idMaquina}")
	public String crearReserva(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		Maquina m = (Maquina) maquinaService.findMaquinaById(idMaquina).orElseThrow();
		model.addAttribute("maquina", m);
		if (calendarioService.findCalendarioByMaquina(m).orElseThrow().getFechasLibres().isEmpty()) {
			if (usuario.getUsername().equals("admin")) {
				model.addAttribute("admin", true);
			} else {
				model.addAttribute("admin", false);
			}
			model.addAttribute("alerta", "No hay horas disponibles");
			return "mostrarMaquina";
		}
		model.addAttribute("fechasDisponibles",
				calendarioService.findCalendarioByMaquina(m).orElseThrow().getFechasLibres());
		return "crearReserva";
	}

	@PostMapping("/user/verMaquina/generarReserva/{id}/{idMaquina}")
	public String generarReserva(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id, Model model,
			String fechaReserva) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		Maquina m = (Maquina) maquinaService.findMaquinaById(idMaquina).orElseThrow();
		model.addAttribute("maquina", m);
		Timestamp fecha = new Timestamp(Integer.parseInt(fechaReserva.split(" ")[0].split("-")[0]) - 1900,
				Integer.parseInt(fechaReserva.split(" ")[0].split("-")[1]) - 1,
				Integer.parseInt(fechaReserva.split(" ")[0].split("-")[2]),
				Integer.parseInt(fechaReserva.split(" ")[1].split(":")[0]),
				Integer.parseInt(fechaReserva.split(" ")[1].split(":")[1]), 0, 0);
		reservaService.crearReserva(new Reserva(m, 5, usuario, fecha));
		fechaService.deleteFechaByDiaHoraCalendario(new Date(fecha.getYear(), fecha.getMonth(), fecha.getDate()), String.valueOf(fecha.getHours()+":"+fecha.getMinutes()+"0"), m.getCalendario());
		return "redirect:/user/verMaquina/" + id + "/" + idMaquina;
	}

	@GetMapping("/admin/maquinas/reservas/{id}/{idMaquina}")
	public String verReservasMaquina(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id,
			Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		Maquina m = (Maquina) maquinaService.findMaquinaById(idMaquina).orElseThrow();
		model.addAttribute("maquina", m);
		model.addAttribute("listaDeReservas", reservaService.findReservasByMaquina(m));
		if (usuario.getUsername().equals("admin")) {
			model.addAttribute("admin", true);
		} else {
			model.addAttribute("admin", false);
		}
		return "listarReservas";
	}

	@PostMapping("/admin/deleteReserva/{id}/{idMaquina}/{reservaId}")
	public String eliminarReserva(@PathVariable("idMaquina") Long idMaquina, @PathVariable("id") Long id,
			@PathVariable("reservaId") Long reservaId, Model model) {
		Usuario usuario = usuarioService.findUsuarioById(id).get();
		model.addAttribute("usuario", usuario);
		Maquina m = (Maquina) maquinaService.findMaquinaById(idMaquina).orElseThrow();
		model.addAttribute("maquina", m);
		Reserva r = reservaService.findReservaById(reservaId).get();
		fechaService.createFecha(new Date(r.getFecha().getYear(), r.getFecha().getMonth(), r.getFecha().getDate()), String.valueOf(r.getFecha().getHours()+":"+r.getFecha().getMinutes()+"0"), m.getCalendario());
		reservaService.deleteReservaById(reservaId);
		return "redirect:/admin/maquinas/reservas/" + id + "/" + idMaquina;
	}

}
