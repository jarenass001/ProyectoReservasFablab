package es.unex.mdai.reservasFablab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	
}
