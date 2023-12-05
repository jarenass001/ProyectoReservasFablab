package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;

public interface UsuarioService {

	public Iterable <Usuario> crearUsuario(Usuario usuario);

	public Optional<Usuario> findUsuarioById (Long usuarioId);

	public Iterable<Usuario> deleteUsuarioById(Long id);
	
	public Iterable<Usuario> updateUsuario(Usuario usuario);
	
	public Iterable <Usuario> findAllUsers();
	
}
