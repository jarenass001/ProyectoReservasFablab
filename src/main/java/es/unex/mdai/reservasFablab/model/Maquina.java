package es.unex.mdai.reservasFablab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Maquina {
	
	private String nombre;
	@OneToOne
	private Calendario calendario;
	@OneToOne (mappedBy = "maquina")
	private Reserva reserva;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Maquina() {
		nombre="";
		calendario=new Calendario();
		reserva=null;
	}

	public Maquina(String nombre, Calendario calendario, Reserva reserva) {
		this.nombre = nombre;
		this.calendario = calendario;
		this.reserva = reserva;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
