package es.unex.mdai.reservasFablab.model;

import java.sql.Date;

public class Fecha {
	
	Date dia;
	String hora;

	public Fecha() {
		dia = new Date(0);
		hora = "";
	}
	
	public Fecha(Date dia, String hora) {
		this.dia = dia;
		this.hora = hora;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Fecha [dia=" + dia + ", hora=" + hora + "]";
	}
	
}
