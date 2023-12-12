package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unex.mdai.reservasFablab.model.Maquina;
import es.unex.mdai.reservasFablab.repository.MaquinaRepository;

@Service
public class MaquinaServiceImpl implements MaquinaService {

	private final MaquinaRepository maquinaR;
	
	@Autowired
	public MaquinaServiceImpl(MaquinaRepository maquinaRepository) {
		System.out.println("\t Constructor maquinaServiceImpl ");
		this.maquinaR = maquinaRepository;		
	}

	@Override
	public Iterable<Maquina> crearMaquina(Maquina maquina) {
		maquinaR.save(maquina);
		return maquinaR.findAll();
	}

	@Override
	public Optional<Maquina> findMaquinaById(Long maquinaId) {
		return maquinaR.findById(maquinaId);
	}

	@Override
	public Iterable<Maquina> deleteMaquinaById(Long id) {
		maquinaR.deleteById(id);
		return findAllMaquinas();
	}

	@Override
	public Iterable<Maquina> updateMaquina(Maquina maquina) {
		maquinaR.save(maquina);
		return findAllMaquinas();
	}

	@Override
	public Iterable<Maquina> findAllMaquinas() {
		return maquinaR.findAll();
	}

}
