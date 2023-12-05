package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.repository.ReservaRepository;

public class ReservaServiceImpl implements ReservaService {

	private final ReservaRepository reservaR;
	
	@Autowired
	public ReservaServiceImpl(ReservaRepository reservaRepository) {
		System.out.println("\t Constructor ReservaServiceImpl ");
		this.reservaR = reservaRepository;		
	}
	
	@Override
	public Iterable<Reserva> crearReserva(Reserva reserva) {
		reservaR.save(reserva);
		return reservaR.findAll();
	}

	@Override
	public Optional<Reserva> findReservaById(Long reservaId) {
		return reservaR.findById(reservaId);
	}

	@Override
	public Iterable<Reserva> deleteReservaById(Long id) {
		reservaR.deleteById(id);
		return findAllReservas();
	}

	@Override
	public Iterable<Reserva> updateReserva(Reserva reserva) {
		reservaR.save(reserva);
		return findAllReservas();
	}

	@Override
	public Iterable<Reserva> findAllReservas() {
		return reservaR.findAll();
	}

	@Override
	public Optional<Reserva> findReservasByUsuario(Usuario usuario) {
		return reservaR.findByUsuario(usuario);
	}

	@Override
	public Optional<Reserva> findReservasByMaquina(Maquina maquina) {
		// TODO Auto-generated method stub
		return reservaR.findByMaquina(maquina);
	}

}
