package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Seguro;
import persistence.Dao;

public class ListaSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListaSeguros() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Dao dao = new Dao();

		List<Seguro> listaSeguro = new ArrayList<>();
		listaSeguro = dao.listaSeguro();
		req.setAttribute("seguros", listaSeguro);
		
		getServletContext().getRequestDispatcher("/seguro/listarseguro.jsp").forward(req, resp);
	}

}


