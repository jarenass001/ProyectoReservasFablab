package es.unex.mdai.reservasFablab.CommandLineRunner;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import es.unex.mdai.reservasFablab.model.Calendario;
import es.unex.mdai.reservasFablab.model.Fecha;
import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.model.Usuario;
import es.unex.mdai.reservasFablab.repository.CalendarioRepository;
import es.unex.mdai.reservasFablab.repository.FechaRepository;
import es.unex.mdai.reservasFablab.repository.MaquinaRepository;
import es.unex.mdai.reservasFablab.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final MaquinaRepository maquinaRepository;
    private final CalendarioRepository calendarioRepository;
    private final FechaRepository fechaRepository;

    public DataInitializer(UsuarioRepository usuarioRepository, MaquinaRepository maquinaRepository, CalendarioRepository calendarioRepository, FechaRepository fechaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.maquinaRepository = maquinaRepository;
        this.calendarioRepository = calendarioRepository;
        this.fechaRepository = fechaRepository;
    }

    @Override
    public void run(String... args) {
    	
    	Calendario cal1 = new Calendario();
		Calendario cal2 = new Calendario();

		Fecha f1 = new Fecha(new Date(123, 11, 28), "17:00");
		Fecha f2 = new Fecha(new Date(123, 11, 28), "18:00");
		Fecha f3 = new Fecha(new Date(123, 11, 28), "19:00");
		Fecha f4 = new Fecha(new Date(123, 11, 29), "17:00");
		Fecha f5 = new Fecha(new Date(123, 11, 29), "18:00");
		Fecha f6 = new Fecha(new Date(123, 11, 29), "19:00");
		Fecha f7 = new Fecha(new Date(123, 11, 30), "17:00");
		Fecha f8 = new Fecha(new Date(123, 11, 30), "18:00");
		Fecha f9 = new Fecha(new Date(123, 11, 30), "19:00");

		Maquina maq1 = new Maquina();
		Maquina maq2 = new Maquina();

		maq1.setCalendario(cal1);
		maq1.setNombre("Prusa 1");

		maq2.setCalendario(cal2);
		maq2.setNombre("Prusa 2");

		Usuario user1 = new Usuario();
		Usuario user2 = new Usuario();

		user1.setPassword("1234");
		user1.setUsername("admin");
		user2.setPassword("1234");
		user2.setUsername("user");
    	
		f1.setCal(cal1);
		f2.setCal(cal1);
		f3.setCal(cal1);
		f4.setCal(cal1);
		f5.setCal(cal2);
		f6.setCal(cal2);
		f7.setCal(cal2);
		f8.setCal(cal2);
		f9.setCal(cal2);
		
		calendarioRepository.save(cal1);
		calendarioRepository.save(cal2);
		
		fechaRepository.save(f1);
		fechaRepository.save(f2);
		fechaRepository.save(f3);
		fechaRepository.save(f4);
		fechaRepository.save(f5);
		fechaRepository.save(f6);
		fechaRepository.save(f7);
		fechaRepository.save(f8);
		fechaRepository.save(f9);
		
		cal1.addFecha(f1);
		cal1.addFecha(f2);
		cal1.addFecha(f3);
		cal1.addFecha(f4);
		cal2.addFecha(f5);
		cal2.addFecha(f6);
		cal2.addFecha(f7);
		cal2.addFecha(f8);
		cal2.addFecha(f9);
		
		
		usuarioRepository.save(user1);
		usuarioRepository.save(user2);

		maquinaRepository.save(maq1);
		maquinaRepository.save(maq2);
    	
//        // Crear usuarios
//        Usuario usuario1 = new Usuario("usuario1", "password1");
//        Usuario usuario2 = new Usuario("usuario2", "password2");
//        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));
//
//        // Crear máquinas
//        Maquina maquina1 = new Maquina("Máquina 1", null);
//        Maquina maquina2 = new Maquina("Máquina 2", null);
//        Maquina maquina3 = new Maquina("Máquina 3", null);
//
//        // Fechas con horas disponibles
//        Date fecha1 = Date.valueOf((LocalDate.now().plusDays(1);
//        Date fecha2 = Date.valueOf(LocalDate.now().plusDays(2));
//        Date fecha3 = Date.valueOf(LocalDate.now().plusDays(3));
//
//        List<LocalTime> horasDisponibles = Arrays.asList(LocalTime.of(8, 0), LocalTime.of(10, 0), LocalTime.of(14, 0));
//        
//        // Crear calendarios
//        Calendario cal1 = new Calendario(null, null);
//        
//        maquinaRepository.saveAll(Arrays.asList(maquina1, maquina2, maquina3));
//        
//
//        // Crear reservas con fechas y horas disponibles
//        List<Reserva> reservas = Arrays.asList(
//                new Reserva(fecha1, horasDisponibles.stream().map(h -> h.toString()).collect(Collectors.toList()), maquina1, usuario1),
//                new Reserva(fecha2, horasDisponibles.stream().map(h -> h.toString()).collect(Collectors.toList()), maquina2, usuario2),
//                new Reserva(fecha3, horasDisponibles.stream().map(h -> h.toString()).collect(Collectors.toList()), maquina3, usuario1)
//        );
//        reservaRepository.saveAll(reservas);
    }
}
