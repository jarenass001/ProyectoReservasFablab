package es.unex.mdai.reservasFablab.model;

import java.sql.Date;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Calendario {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private ArrayList<Date> fechasLibres;
	@OneToOne (mappedBy = "calendario")
	private Maquina maquina;

	public Calendario() {
		fechasLibres = new ArrayList<Date>();
		id = -1L;
		maquina=null;
	}

	public Calendario(ArrayList<Date> fechasLibres, Maquina maquina) {
		this.fechasLibres = fechasLibres;
		this.maquina = maquina;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public ArrayList<Date> getFechasLibres() {
		return fechasLibres;
	}

	public void setFechasLibres(ArrayList<Date> fechasLibres) {
		this.fechasLibres = fechasLibres;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
