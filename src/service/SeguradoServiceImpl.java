package service;

import model.Segurado;
import persistence.Dao;

public class SeguradoServiceImpl implements SeguradoService{

	Dao dao = new Dao();
	
	@Override
	public void salvar(Segurado segurado) throws Exception {
		
		Segurado seguradoObject = this.buscarPorCpf(segurado);
		
		if (seguradoObject == null) {
			dao.create(segurado);
		}else {
			throw new Exception("Usuario já existe");
		}

		
	}

	@Override
	public Segurado buscarPorCpf(Segurado segurado) {

		Segurado seguradoObject =	dao.buscarSeguradoPorCpf(segurado.getCpf());

		return seguradoObject;
	}

}
