package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Segurado;
import model.Seguro;
import persistence.Dao;
import service.SeguradoService;
import service.SeguradoServiceImpl;

public class SeguradoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SeguradoController() {
		super();
	}

	SeguradoService seguradoService = new SeguradoServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao<Segurado> dao = new Dao<Segurado>();

		try {
			Segurado segurado = new Segurado();

			segurado.setNome(req.getParameter("nome"));
			segurado.setCpf(req.getParameter("cpf"));
			segurado.setRg(req.getParameter("rg"));
			segurado.setSexo(req.getParameter("sexo"));
			segurado.setCorrentista(req.getParameter("correntista"));
			segurado.setDiasVisita(req.getParameterValues("diasVisita"));
			segurado.setData_nas(req.getParameter("data_nas"));
			segurado.setData_alt(req.getParameter("data_alt"));
			segurado.setData_cad(new Date());

			List<Seguro> listaSeguro = new ArrayList<Seguro>();

			for (String id : req.getParameterValues("seguro")) {
				Seguro seguros = new Seguro();
				seguros.setId(Integer.valueOf(id));
				listaSeguro.add(seguros);
			}
			segurado.setSeguro(listaSeguro);

			if (req.getParameter("id") == null) {

				seguradoService.salvar(segurado);
				listarSegurados(req, dao);
				req.setAttribute("mensagem", "Cadastrado com sucesso! :)");

			} else {

				String id = req.getParameter("id");
				segurado.setId(Integer.parseInt(id));
				dao.alterar(segurado);
				listarSegurados(req, dao);
			}
			getServletContext().getRequestDispatcher("/seguro/listarsegurado.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem", "Não foi cadastrado! :" + e.getMessage());
			getServletContext().getRequestDispatcher("/seguro/cadastrarsegurado.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Dao<Segurado> dao = new Dao<Segurado>();
		String acao = req.getParameter("acao");

		if (acao != null && "listarsegurado".equalsIgnoreCase(acao)) {

			listarSegurados(req, dao);

			getServletContext().getRequestDispatcher("/seguro/listarsegurado.jsp").forward(req, resp);

		} else if (acao != null && "abrirseguro".equalsIgnoreCase(acao)) {

			List<Seguro> listaSeguro = new ArrayList<>();
			listaSeguro = dao.listaSeguro();
			req.setAttribute("seguros", listaSeguro);

			getServletContext().getRequestDispatcher("/seguro/cadastrarsegurado.jsp").forward(req, resp);

		} else if (acao != null && "excluirsegurado".equalsIgnoreCase(acao)) {

			String id = req.getParameter("id");
			dao.remove(Segurado.class, Integer.parseInt(id));
			listarSegurados(req, dao);

			getServletContext().getRequestDispatcher("/seguro/listarsegurado.jsp").forward(req, resp);

		} else if (acao != null && "alterarsegurado".equalsIgnoreCase(acao)) {

			String id = req.getParameter("id");
			Segurado segurado = (Segurado) dao.findById(Segurado.class, Integer.parseInt(id));
			req.setAttribute("segurado", segurado);

			List<Seguro> listaSeguro = new ArrayList<>();
			listaSeguro = dao.listaSeguro();
			req.setAttribute("seguros", listaSeguro);

			getServletContext().getRequestDispatcher("/seguro/upd.jsp").forward(req, resp);
		}
	}

	public void listarSegurados(HttpServletRequest req, Dao<Segurado> dao) {
		List<Segurado> listaSegurado = new ArrayList<>();
		listaSegurado = dao.listaSegurado();
		req.setAttribute("segurados", listaSegurado);
	}
}