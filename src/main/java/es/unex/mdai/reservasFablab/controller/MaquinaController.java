package es.unex.mdai.reservasFablab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.service.MaquinaService;

@Controller
public class MaquinaController {

	private MaquinaService maquinaService;
	
	@Autowired
	public MaquinaController(MaquinaService maquinaService) {
		this.maquinaService = maquinaService;
		System.out.println("\t Constructor MaquinaController ");
	}
	
	@GetMapping("/user/listarMaquinas")
	public String verListaMaquinas(Model model) {
		model.addAttribute("listaDeMaquinas", (List<Maquina>) maquinaService.findAllMaquinas());
		return "listarMaquinas";
	}
	
	@PostMapping("/admin/addMaquina")
	public String anadirMaquinas(Maquina maquina, Model model) {
		maquinaService.crearMaquina(maquina);
		return "redirect:/user/listarMaquinas";
	}
	
	@PostMapping("/admin/deleteMaquina")
	public String deleteUsuario(Long id, Model model) {
		maquinaService.deleteMaquinaById(id);
		return "redirect:/user/listarMaquinas";
	}
	
	@GetMapping("/user/verMaquina/{id}")
	public String verMaquina(@PathVariable("id") Long id, Model model) {
		model.addAttribute("maquina", (Maquina) maquinaService.findMaquinaById(id).orElseThrow());
		return "mostrarMaquina";
	}
	
}
