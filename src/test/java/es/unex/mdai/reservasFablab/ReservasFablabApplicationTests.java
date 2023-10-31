package es.unex.mdai.reservasFablab;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.unex.mdai.reservasFablab.model.Calendario;
import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Reserva;
import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.repository.CalendarioRepository;
import es.unex.mdai.reservasFablab.repository.MaquinaRepository;
import es.unex.mdai.reservasFablab.repository.ReservaRepository;
import es.unex.mdai.reservasFablab.repository.UsuarioRepository;

@SpringBootTest
class ReservasFablabApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private MaquinaRepository maquinaRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private CalendarioRepository calendarioRepository;

	@Test
	void contextLoads() {

		Calendario cal1 = new Calendario();
		Calendario cal2 = new Calendario();

		cal1.addFecha(new Date(2023, 10, 28));
		cal1.addFecha(new Date(2023, 10, 29));
		cal1.addFecha(new Date(2023, 10, 30));

		cal2.addFecha(new Date(2023, 11, 28));
		cal2.addFecha(new Date(2023, 11, 29));
		cal2.addFecha(new Date(2023, 11, 30));

		Maquina maq1 = new Maquina();
		Maquina maq2 = new Maquina();

		maq1.setCalendario(cal1);
		maq1.setNombre("Prusa 1");

		maq2.setCalendario(cal2);
		maq2.setNombre("Prusa 2");

		Usuario user1 = new Usuario();
		Usuario user2 = new Usuario();

		user1.setPassword("1234");
		user1.setUsername("user1");
		user2.setPassword("qwerty");
		user2.setUsername("user2");

		Reserva res1 = new Reserva();
		Reserva res2 = new Reserva();
		Reserva res3 = new Reserva();

		res1.setMaquina(maq1);
		res1.setPrecioTotal(2.5);
		res1.setUsuario(user1);

		res2.setMaquina(maq1);
		res2.setPrecioTotal(1.2);
		res2.setUsuario(user2);

		res3.setMaquina(maq2);
		res3.setPrecioTotal(5.5);
		res3.setUsuario(user2);

//		ArrayList<Reserva> reservas = new ArrayList<Reserva>();
//		reservas.add(res1);
//		reservas.add(res2);
//		reservas.add(res3);

		// reservas = (ArrayList<Reserva>) reservaRepository.saveAll(reservas);

		calendarioRepository.save(cal1);
		calendarioRepository.save(cal2);

		usuarioRepository.save(user1);
		usuarioRepository.save(user2);

		maquinaRepository.save(maq1);
		maquinaRepository.save(maq2);

		reservaRepository.save(res1);
		reservaRepository.save(res2);
		reservaRepository.save(res3);

		System.out.println("Nombres de los usuarios:");
		Iterable<Usuario> iterable2 = usuarioRepository.findAll();
		iterable2.forEach(usuario -> System.out.println(usuario.getUsername()));

		System.out.println("Presio de las reservas:");
		Iterable<Reserva> iterable3 = reservaRepository.findAll();
		iterable3.forEach(reserva -> System.out.println(reserva.getPrecioTotal()));

		System.out.println("Nombre de las máquinas:");
		Iterable<Maquina> iterable4 = maquinaRepository.findAll();
		iterable4.forEach(maquina -> System.out.println(maquina.getNombre()));

		System.out.println("Fechas libres de las máquinas:");
		Iterable<Calendario> iterable5 = calendarioRepository.findAll();
		iterable5.forEach(calendario -> System.out.println(calendario.getFechasLibres().toString()));

		System.out.println("---------------------------BORRAMOS USUARIO2 Y SUS RESERVAS------------------------------");
		borrarUsuario(user2);

		System.out.println("Nombres de los usuarios:");
		iterable2 = usuarioRepository.findAll();
		iterable2.forEach(usuario -> System.out.println(usuario.getUsername()));

		System.out.println("Presio de las reservas:");
		iterable3 = reservaRepository.findAll();
		iterable3.forEach(reserva -> System.out.println(reserva.getPrecioTotal()));

		System.out.println("Nombre de las máquinas:");
		iterable4 = maquinaRepository.findAll();
		iterable4.forEach(maquina -> System.out.println(maquina.getNombre()));

		System.out.println("Fechas libres de las máquinas:");
		iterable5 = calendarioRepository.findAll();
		iterable5.forEach(calendario -> System.out.println(calendario.getFechasLibres().toString()));

		System.out.println("---------------------------ACTUALIZAMOS USUARIO1 A MI PANA MIGUEL------------------------------");
		user1.setUsername("Mi pana Miguel");
		actualizarUsuario(usuarioRepository.findById(user1.getId()).get(), user1);

		System.out.println("Nombres de los usuarios:");
		iterable2 = usuarioRepository.findAll();
		iterable2.forEach(usuario -> System.out.println(usuario.getUsername()));

		System.out.println("Presio de las reservas:");
		iterable3 = reservaRepository.findAll();
		iterable3.forEach(reserva -> System.out.println(reserva.getPrecioTotal()));

		System.out.println("Nombre de las máquinas:");
		iterable4 = maquinaRepository.findAll();
		iterable4.forEach(maquina -> System.out.println(maquina.getNombre()));

		System.out.println("Fechas libres de las máquinas:");
		iterable5 = calendarioRepository.findAll();
		iterable5.forEach(calendario -> System.out.println(calendario.getFechasLibres().toString()));
		
		System.out.println("Las reservas del Usuario1");
		iterable3 = reservaRepository.findByUsuario(user1);
		iterable3.forEach(reserva -> System.out.println(reserva.getPrecioTotal()));
	}

	public void borrarUsuario(Usuario u) {
		Iterable<Reserva> reservasBorrar = reservaRepository.findByUsuario(u);
		reservasBorrar.forEach(reserva -> reservaRepository.delete(reserva));
		usuarioRepository.delete(u);
	}

	public void actualizarUsuario(Usuario antiguo, Usuario u) {
		Usuario temp = usuarioRepository.findById(antiguo.getId()).get();
		Iterable<Reserva> reservasGuardar = reservaRepository.findByUsuario(antiguo);
		reservasGuardar.forEach(reserva -> reserva.setUsuario(u));
		borrarUsuario(temp);
		usuarioRepository.save(u);
		reservaRepository.saveAll(reservasGuardar);
	}

}
