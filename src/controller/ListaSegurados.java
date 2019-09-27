package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Segurado;
import persistence.Dao;

public class ListaSegurados extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListaSegurados() {
		super();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Dao dao = new Dao();

		List<Segurado> listaSegurado = new ArrayList<>();
		listaSegurado = dao.listaSegurado();
		req.setAttribute("segurados", listaSegurado);

		getServletContext().getRequestDispatcher("/seguro/listarsegurado.jsp").forward(req,resp);
	}

}
