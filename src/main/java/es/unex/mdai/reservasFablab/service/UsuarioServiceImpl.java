package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioR;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		System.out.println("\t Constructor UsuarioServiceImpl ");
		this.usuarioR = usuarioRepository;		
	}
	
	@Override
	public Iterable<Usuario> crearUsuario(Usuario usuario) {
		usuarioR.save(usuario);
		return usuarioR.findAll();
	}

	@Override
	public Optional<Usuario> findUsuarioById(Long usuarioId) {
		return usuarioR.findById(usuarioId);
	}

	@Override
	public Iterable<Usuario> deleteUsuarioById(Long id) {
		usuarioR.deleteById(id);
		return findAllUsers();
	}

	@Override
	public Iterable<Usuario> updateUsuario(Usuario usuario) {
		usuarioR.save(usuario);
		return findAllUsers();
	}

	@Override
	public Iterable<Usuario> findAllUsers() {
		return usuarioR.findAll();
	}

}
