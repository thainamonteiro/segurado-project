package service;

import model.Segurado;

public interface SeguradoService {
	
	public void salvar(Segurado segurado) throws Exception;
	
	public Segurado buscarPorCpf(Segurado segurado);

}
