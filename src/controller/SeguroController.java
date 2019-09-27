package controller;

import java.io.IOException;

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
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			Seguro seguro = new Seguro();

			seguro.setIdentificacao(req.getParameter("identificacao"));
			seguro.setValor(req.getParameter("Valor"));
			
			Dao dao = new Dao();
			
			dao.create(seguro);
		
		
			req.setAttribute("mensagem", "Cadastrado com sucesso! :)");
			getServletContext().getRequestDispatcher("/seguro/listaseguro.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("mensagem", "Não foi cadastrado! :" + e.getMessage());
			getServletContext().getRequestDispatcher("/seguro/cadastrarseguro.jsp").forward(req, resp);

		}

	}

}
