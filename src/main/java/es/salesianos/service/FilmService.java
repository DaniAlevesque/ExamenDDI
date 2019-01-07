package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Film;
import es.salesianos.repository.RepositoryFilm;

public class FilmService {
	
	
	private RepositoryFilm repositoryFilm = new RepositoryFilm();

	public RepositoryFilm getRepository() {
		return repositoryFilm;
	}

	public void setRepository(RepositoryFilm repositoryFilm) {
		this.repositoryFilm = repositoryFilm;
	}

	public void addFilm(Film film) {
		repositoryFilm.insertFilm(film);
	}

	public List<Film> listAllFilms() {
		return repositoryFilm.searchAllPeliculas();
	}

	public void searchAndDeleteFilm(Integer codPelicula) {
		repositoryFilm.searchAndDeletePelicula(codPelicula);
	}

}
