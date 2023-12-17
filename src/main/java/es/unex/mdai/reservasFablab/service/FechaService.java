package es.unex.mdai.reservasFablab.service;

import java.sql.Date;

import es.unex.mdai.reservasFablab.model.Calendario;

public interface FechaService {
	
	public void deleteFechaByDiaHoraCalendario(Date dia, String hora, Calendario calendario);
	
	public void createFecha(Date dia, String hora, Calendario calendario);
	
}
