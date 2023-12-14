package es.unex.mdai.reservasFablab.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import es.unex.mdai.reservasFablab.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);
}
