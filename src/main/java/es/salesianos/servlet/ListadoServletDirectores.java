package es.salesianos.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.salesianos.model.Director;
import es.salesianos.repository.RepositoryActor;
import es.salesianos.service.DirectorService;

public class ListadoServletDirectores extends HttpServlet {
	
	private DirectorService servicio = new DirectorService();
	private RepositoryActor repositoryActor = new  RepositoryActor();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Director> listAllDirectors = servicio.listAllDirectors();
		req.setAttribute("listAllDirectors", listAllDirectors);
		redirect(req,resp);
	}
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addDirector.jsp");
		dispatcher.forward(req,resp);
	}
}
