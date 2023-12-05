package es.unex.mdai.reservasFablab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {
	Optional<Reserva> findByUsuario(Usuario usuario);
	Optional<Reserva> findByMaquina(Maquina maquina);
}
