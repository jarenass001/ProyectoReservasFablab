package es.unex.mdai.reservasFablab.service;

import java.util.Optional;

import es.unex.mdai.reservasFablab.model.Maquina;

public interface MaquinaService {

	public Iterable <Maquina> crearMaquina (Maquina maquina);
	
	public Optional <Maquina> findMaquinaById (Long maquinaId);
	
	public Iterable <Maquina> deleteMaquinaById (Long id);
	
	public Iterable <Maquina> updateMaquina (Maquina maquina);
	
	public Iterable <Maquina> findAllMaquinas();
	
}
