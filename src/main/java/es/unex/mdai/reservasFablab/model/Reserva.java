package es.unex.mdai.reservasFablab.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class Reserva {
	
	@OneToOne
	private Maquina maquina;
	private double precioTotal;
	@ManyToOne
	private Usuario usuario;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	public Reserva() {
		maquina=null;
		precioTotal=0;
		id=-1L;
		usuario=null;
	}

	public Reserva(Maquina maquina, double precioTotal, Usuario usuario) {
		this.maquina = maquina;
		this.precioTotal = precioTotal;
		this.usuario = usuario;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
