package teste;

import model.Login;
import persistence.Dao;
import service.Criptografar;

public class teste {

	public static void main(String[] args) {

		Login login = new Login();
		login.setUsuario("usuario");
		login.setSenha("umasenha");
		login.setSenha(Criptografar.encriptografar(login.getSenha()));
		
		Dao dao = new Dao();
		
		dao.create(login);
		
		if(login.getSenha().equals(Criptografar.encriptografar("umasenha"))) {
			System.out.println("Funcionou!");
		}else {
			System.out.println("Noop");
		}
		
		
		

	}

}
