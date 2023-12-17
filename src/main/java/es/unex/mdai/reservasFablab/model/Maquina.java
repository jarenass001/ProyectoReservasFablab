package es.unex.mdai.reservasFablab.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Maquina {
	
	private String nombre;
	@OneToOne
	private Calendario calendario;
//	@OneToMany (mappedBy = "maquina")
//	private ArrayList<Reserva> reservas;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Maquina() {
		nombre="";
		calendario=null;
//		reservas=new ArrayList<Reserva>();
	}

	public Maquina(String nombre, Calendario calendario) {
		this.nombre = nombre;
		this.calendario = calendario;
//		this.reservas = new ArrayList<Reserva>();
	}

//	public ArrayList<Reserva> getReservas() {
//		return reservas;
//	}
//
//	public void setReservas(ArrayList<Reserva> reservas) {
//		this.reservas = reservas;
//	}
//	
//	public boolean addReserva(Reserva r) {
//		return reservas.add(r);
//	}

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

	@Override
	public String toString() {
		return "Maquina [nombre=" + nombre + ", calendario=" + calendario + ", id=" + id + "]";
	}

	public void eliminarFecha(Timestamp fecha) {
		Fecha fecha2 = new Fecha(new Date(fecha.getYear(), fecha.getMonth(), fecha.getDate()), String.valueOf(fecha.getHours()+":"+fecha.getMinutes()+"0"), getCalendario());
		System.out.println(calendario.getFechasLibres());
		System.out.println(fecha2);
		//calendario.getFechasLibres().remove(fecha2);
		calendario.eliminarFecha(fecha2);
	}
}
