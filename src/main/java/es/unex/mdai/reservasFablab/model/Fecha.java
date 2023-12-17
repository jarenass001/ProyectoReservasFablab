package es.unex.mdai.reservasFablab.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Fecha {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Calendario cal;
	
	private Date dia;
	private String hora;

	public Fecha() {
		this.dia = new Date(0);
		this.hora = "";
		this.cal = new Calendario();
	}
	
	public Fecha(Date dia, String hora) {
		this.dia = dia;
		this.hora = hora;
		this.cal = new Calendario();
	}
	
	public Fecha(Date dia, String hora, Calendario cal) {
		this.dia = dia;
		this.hora = hora;
		this.cal = cal;
	}

	public Calendario getCal() {
		return cal;
	}

	public void setCal(Calendario cal) {
		this.cal = cal;
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
		return "" + dia + " " + hora + "";
	}
	
}
