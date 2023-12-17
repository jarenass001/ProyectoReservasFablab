package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unex.mdai.reservasFablab.model.Fecha;
import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.repository.FechaRepository;

@Service
public class FechaServiceImpl implements FechaService {

private final FechaRepository fechaR;
	
	@Autowired
	public FechaServiceImpl(FechaRepository fechaRepository) {
		System.out.println("\t Constructor fechaServiceImpl ");
		this.fechaR = fechaRepository;		
	}
	
//	@Override
//	public Optional<Fecha> findFechaByStringMaquina(String fechaHora, Maquina m) {
//		fechaHora.split(" ");
//		Iterable<Fecha> iterable = fechaR.findAll();
//		iterable.forEach(fecha -> System.out.println(usuario.getUsername()));
//		return Optional.empty();
//	}

}
