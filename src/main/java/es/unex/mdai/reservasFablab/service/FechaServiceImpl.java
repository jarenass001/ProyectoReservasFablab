package es.unex.mdai.reservasFablab.service;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unex.mdai.reservasFablab.model.Calendario;
import es.unex.mdai.reservasFablab.model.Fecha;
import es.unex.mdai.reservasFablab.repository.FechaRepository;

@Service
public class FechaServiceImpl implements FechaService {

private final FechaRepository fechaR;
	
	@Autowired
	public FechaServiceImpl(FechaRepository fechaRepository) {
		System.out.println("\t Constructor fechaServiceImpl ");
		this.fechaR = fechaRepository;		
	}

	@Override
	public void deleteFechaByDiaHoraCalendario(Date dia, String hora, Calendario calendario) {
		ArrayList<Fecha> borrar = new ArrayList<Fecha>();
		Iterable<Fecha> iterable = fechaR.findAll();
		iterable.forEach(fecha -> {if (fecha.getDia().equals(dia) && fecha.getHora().equals(hora) && fecha.getCal().equals(calendario)) borrar.add(fecha); });
		fechaR.delete(borrar.get(0));
	}

	@Override
	public void createFecha(Date dia, String hora, Calendario calendario) {
		Fecha fecha = new Fecha(dia, hora, calendario);
		fechaR.save(fecha);
	}

}
