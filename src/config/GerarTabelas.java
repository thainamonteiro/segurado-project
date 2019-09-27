package config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GerarTabelas {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("segurado"); 
		//Cria um objeto do tipo EntityManager que permite que fa�a queries no banco de dados
		factory.close();
		
		System.out.println("Tables geradas!");
	}
}
