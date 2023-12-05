package es.unex.mdai.reservasFablab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import es.unex.mdai.reservasFablab.model.Calendario;
import es.unex.mdai.reservasFablab.model.Maquina;

public interface CalendarioRepository extends CrudRepository<Calendario, Long> {

	public Optional<Calendario> findByMaquina(Maquina maquina);
	
}
