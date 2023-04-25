package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;


@WebServlet(urlPatterns = {"/login","/welcome","/cadastrar"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static List<Usuario> listusers = new ArrayList<Usuario>();

	static {
		listusers.add(new Usuario("adm","123"));
		listusers.add(new Usuario("Adm","123"));

	}

	public Controller() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println("do post,servlet path ->" + action);

		if (action.equals("/login")) {
			login(request, response);
		}else if (action.equals("/cadastrar")) {
			cadastrar(request, response);
		}

	}

	protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String novoLogin = request.getParameter("loginCadastro");
		String novaSenha = request.getParameter("senhaCadastro");
		listusers.add(new Usuario (novoLogin,novaSenha));
		response.sendRedirect("login.jsp");

	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entrou no login");
		String login = request.getParameter("loginUsuario");
		String senha = request.getParameter("senhaUsuario");

		for (Usuario users : listusers) {
			if (users.getUsername().equals(login) && users.getPassword().equals(senha)) {
				response.sendRedirect("welcome.jsp");
			}
		}


	}

}
