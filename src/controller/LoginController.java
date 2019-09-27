package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.Dao;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao dao = new Dao();

		try {

			String usuario = req.getParameter("usuario");
			String senha = req.getParameter("senha");

			boolean res = dao.buscarLogin(usuario, senha);

			if (res == true) {

				HttpSession session = req.getSession();
				session.setAttribute("usuario", usuario);
				req.setAttribute("msg", "Bem vindo, " + usuario + "<br />");
				req.getRequestDispatcher("seguro/menu.jsp").forward(req, resp);

			} else {
				req.setAttribute("mensagem", "Dados Incorretos!");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			req.setAttribute("mensagem", "N�o foi cadastrado! :" + e.getMessage());
			req.getRequestDispatcher("seguro/cadastrosegurado.jsp").forward(req, resp);

		}
	}



}

