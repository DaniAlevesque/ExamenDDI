package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Film;
import es.salesianos.repository.Repository;

public class FilmService {
	
	
	private Repository repository = new Repository();

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public void addFilm(Film film) {
		repository.insertPelicula(film);
	}

	public List<Film> listAllFilms() {
		return repository.searchAllPeliculas();
	}

	public void searchAndDeleteFilm(Integer codPelicula) {
		repository.searchAndDeletePelicula(codPelicula);
	}

}
