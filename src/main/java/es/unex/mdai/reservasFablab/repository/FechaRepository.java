package es.unex.mdai.reservasFablab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import es.unex.mdai.reservasFablab.model.Calendario;
import es.unex.mdai.reservasFablab.model.Fecha;
import es.unex.mdai.reservasFablab.model.Maquina;

public interface FechaRepository extends CrudRepository<Fecha, Long> {

	//public Optional<Fecha> findAllByCalendarioMaquina(Calendario cal, Maquina m);
	
}
