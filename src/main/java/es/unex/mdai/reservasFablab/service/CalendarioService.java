package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import es.unex.mdai.reservasFablab.model.Calendario;
import es.unex.mdai.reservasFablab.model.Maquina;

public interface CalendarioService {

	public Iterable <Calendario> crearCalendario (Calendario calendario);
	
	public Optional <Calendario> findCalendarioById (Long calendarioId);
	
	public Iterable <Calendario> deleteCalendarioById (Long id);
	
	public Iterable <Calendario> updateCalendario (Calendario calendario);
	
	public Iterable <Calendario> findAllCalendarios();
	
	public Optional <Calendario> findCalendarioByMaquina(Maquina maquina);
	
}
