package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;

public interface ReservaService {
	
	public Iterable <Reserva> crearReserva(Reserva reserva);
	
	public Optional <Reserva> findReservaById(Long reservaId);
	
	public Iterable <Reserva> deleteReservaById(Long id);
	
	public Iterable <Reserva> updateReserva(Reserva reserva);
	
	public Iterable <Reserva> findAllReservas();
	
	public Iterable <Reserva> findReservasByUsuario(Usuario usuario);
	
	public Iterable <Reserva> findReservasByMaquina(Maquina maquina);
	
}
