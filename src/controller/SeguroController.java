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

public class SeguroController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SeguroController() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			Seguro seguro = new Seguro();

			seguro.setIdentificacao(req.getParameter("identificacao"));
			seguro.setValor(req.getParameter("valor"));

			if (req.getParameter("id") == null) {

				Dao<Seguro> dao = new Dao<Seguro>();
				dao.create(seguro);
				req.setAttribute("mensagem", "Cadastrado com sucesso! :)");

			} else {
				Dao<Seguro> dao = new Dao<Seguro>();

				String id = req.getParameter("id");
				seguro.setId(Integer.parseInt(id));

				dao.alterar(seguro);

				List<Seguro> listaSeguro = new ArrayList<>();
				listaSeguro = dao.listaSeguro();
				
				req.setAttribute("seguros", listaSeguro);

			}

			getServletContext().getRequestDispatcher("/seguro/listarseguro.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem", "Não foi cadastrado! :" + e.getMessage());
			getServletContext().getRequestDispatcher("/seguro/cadastrarseguro.jsp").forward(req, resp);

		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");

		if (acao != null && "cadastrarseguro".equalsIgnoreCase(acao)) {

			Dao<Seguro> dao = new Dao<Seguro>();

			List<Seguro> listaSeguro = dao.listaSeguro();
			req.setAttribute("seguro", listaSeguro);

			getServletContext().getRequestDispatcher("/seguro/cadastrarseguro.jsp").forward(req, resp);

		} else if (acao != null && "listarseguro".equalsIgnoreCase(acao)) {

			Dao<Seguro> dao = new Dao<Seguro>();

			List<Seguro> listaSeguro = new ArrayList<>();
			listaSeguro = dao.listaSeguro();
			req.setAttribute("seguros", listaSeguro);

			getServletContext().getRequestDispatcher("/seguro/listarseguro.jsp").forward(req, resp);

		} else if (acao != null && "excluirseguro".equalsIgnoreCase(acao)) {

			Dao<Seguro> dao = new Dao<Seguro>();

			String id = req.getParameter("id");

			dao.remove(Seguro.class, Integer.parseInt(id));

			List<Seguro> listaSeguro = dao.listaSeguro();
			req.setAttribute("seguros", listaSeguro);

			getServletContext().getRequestDispatcher("/seguro/listarseguro.jsp").forward(req, resp);

		} else if (acao != null && "alterarseguro".equalsIgnoreCase(acao)) {
			Dao<Seguro> dao = new Dao<Seguro>();

			String id = req.getParameter("id");

			Seguro seguro = (Seguro) dao.findById(Seguro.class, Integer.parseInt(id));

			req.setAttribute("seguro", seguro);

			getServletContext().getRequestDispatcher("/seguro/updseguro.jsp").forward(req, resp);

		}
	}

}
