package com.advancia.stage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advancia.stage.dao.UtenteDAO;
import com.advancia.stage.model.Utente;

import org.apache.log4j.Logger;

@WebServlet("/login") // l'URL della servlet al quale la pagina di login fa rediredt nel form action
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	@SuppressWarnings("unused")
	private UtenteDAO loginDAO = new UtenteDAO();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Salvo parametri ricevuti da login.jsp
		String nomeUtente = request.getParameter("nomeUtente");
		String passwordUtente = request.getParameter("passwordUtente");
		// creo oggetto utente per settare i parametri
		Utente utente = new Utente();

		// setto i parametri che verranno utilizzati nella classe LoginDAO dove è
		// presente il metodo validate(...)
		utente.setNomeUtente(nomeUtente);
		utente.setPasswordUtente(passwordUtente);
		try {
			utente = UtenteDAO.validate(utente);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		// se validate restituisce null, l'utente non è presente nel DB
		if ((utente) == null) {
			String errorMessage = "Utente non trovato!";
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
		} else {
			Cookie loginCookie = new Cookie("nomeUtente", utente.getNomeUtente());
			Cookie idCookie = new Cookie("idUtente", String.valueOf(utente.getIdUtente()));
			loginCookie.setMaxAge(30 * 60);
			idCookie.setMaxAge(30 * 60);
			response.addCookie(loginCookie);
			response.addCookie(idCookie);
			String flag = null;
			request.setAttribute("mostraPizze", flag);
			response.sendRedirect(request.getContextPath() + "/pizzeria");
		}
	}
}
