package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import es.unex.mdai.reservasFablab.model.Reserva;

public interface ReservaService {
	
	public Iterable <Reserva> crearReserva(Reserva reserva);
	
	public Optional <Reserva> findReservaById(Long reservaId);
	
	public Iterable <Reserva> deleteReservaById(Long id);
	
	public Iterable <Reserva> updateReserva(Reserva reserva);
	
	public Iterable <Reserva> findAllReservas();
	
}
