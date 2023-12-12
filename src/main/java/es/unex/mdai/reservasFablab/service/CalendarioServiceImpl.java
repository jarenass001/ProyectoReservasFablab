package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unex.mdai.reservasFablab.model.Calendario;
import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.repository.CalendarioRepository;

@Service
public class CalendarioServiceImpl implements CalendarioService {

	private final CalendarioRepository calendarioR;
	
	@Autowired
	public CalendarioServiceImpl(CalendarioRepository calendarioRepository) {
		System.out.println("\t Constructor calendarioServiceImpl ");
		this.calendarioR = calendarioRepository;		
	}
	
	@Override
	public Iterable<Calendario> crearCalendario(Calendario calendario) {
		calendarioR.save(calendario);
		return calendarioR.findAll();
	}

	@Override
	public Optional<Calendario> findCalendarioById(Long calendarioId) {
		return calendarioR.findById(calendarioId);
	}

	@Override
	public Iterable<Calendario> deleteCalendarioById(Long id) {
		calendarioR.deleteById(id);
		return findAllCalendarios();
	}

	@Override
	public Iterable<Calendario> updateCalendario(Calendario calendario) {
		calendarioR.save(calendario);
		return findAllCalendarios();
	}

	@Override
	public Iterable<Calendario> findAllCalendarios() {
		return findAllCalendarios();
	}

	@Override
	public Optional<Calendario> findCalendarioByMaquina(Maquina maquina) {
		return calendarioR.findByMaquina(maquina);
	}

}
