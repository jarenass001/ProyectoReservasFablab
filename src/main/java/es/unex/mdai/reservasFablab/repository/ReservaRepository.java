package es.unex.mdai.reservasFablab.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {
	Iterable<Reserva> findByUsuario(Usuario usuario);
}
