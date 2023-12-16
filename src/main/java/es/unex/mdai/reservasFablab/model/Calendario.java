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
	private ArrayList<Fecha> fechasLibres;
	@OneToOne (mappedBy = "calendario")
	private Maquina maquina;

	public Calendario() {
		fechasLibres = new ArrayList<Fecha>();
		maquina=null;
	}

	public Calendario(ArrayList<Fecha> fechasLibres, Maquina maquina) {
		this.fechasLibres = fechasLibres;
		this.maquina = maquina;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public ArrayList<Fecha> getFechasLibres() {
		return fechasLibres;
	}

	public void setFechasLibres(ArrayList<Fecha> fechasLibres) {
		this.fechasLibres = fechasLibres;
	}
	
	public boolean addFecha(Fecha fecha) {
		return fechasLibres.add(fecha);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Calendario [id=" + id + ", fechasLibres=" + fechasLibres + "]";
	}

}
