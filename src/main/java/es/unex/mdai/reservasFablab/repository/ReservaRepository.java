package es.unex.mdai.reservasFablab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {
	Iterable<Reserva> findByUsuario(Usuario usuario);
	Iterable<Reserva> findByMaquina(Maquina maquina);
}
