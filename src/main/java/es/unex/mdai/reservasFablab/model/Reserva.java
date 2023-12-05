package es.unex.mdai.reservasFablab.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva {
	
	@ManyToOne
	private Maquina maquina;
	private double precioTotal;
	@ManyToOne
	private Usuario usuario;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private Date fecha;

	public Reserva() {
		maquina=null;
		precioTotal=0;
		usuario=null;
		fecha=null;
	}

	public Reserva(Maquina maquina, double precioTotal, Usuario usuario, Date fecha) {
		this.maquina = maquina;
		this.precioTotal = precioTotal;
		this.usuario = usuario;
		this.fecha = fecha;
		
		maquina.eliminarFecha(fecha);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
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

	@Override
	public String toString() {
		return "Reserva [maquina=" + maquina + ", precioTotal=" + precioTotal + ", usuario=" + usuario + ", id=" + id
				+ "]";
	}
	
}
