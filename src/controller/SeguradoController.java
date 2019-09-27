package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Segurado;
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

		try {

			Segurado segurado = new Segurado();

			segurado.setNome(req.getParameter("nome"));
			segurado.setCpf(req.getParameter("cpf"));
			segurado.setRg(req.getParameter("rg"));
			segurado.setSexo(req.getParameter("sexo"));
			segurado.setCorrentista(req.getParameter("correntista"));
			segurado.setDiasVisita(req.getParameterValues("diasVisita"));
			segurado.setData_nas(req.getParameter("data_nas"));
			//segurado.setData_cad(req.getParameter("data_cad"));
			segurado.setData_alt(req.getParameter("data_alt"));
			
			
			seguradoService.salvar(segurado);
			
		
			req.setAttribute("mensagem", "Cadastrado com sucesso! :)");
			getServletContext().getRequestDispatcher("/listasegurado.jsp").forward(req, resp);


		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem", "Não foi cadastrado! :" + e.getMessage());
			getServletContext().getRequestDispatcher("/seguro/cadastrarsegurado.jsp").forward(req, resp);

		}

	}

		
}
