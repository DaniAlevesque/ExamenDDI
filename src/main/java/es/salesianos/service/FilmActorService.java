package es.salesianos.service;



import es.salesianos.model.FilmActors;
import es.salesianos.repository.RepositoryFilmActors;

public class FilmActorService {

	private RepositoryFilmActors repositoryFilmActors = new RepositoryFilmActors();

	public RepositoryFilmActors getRepository() {
		return repositoryFilmActors;
	}

	public void setRepository(RepositoryFilmActors repositoryFilmActors) {
		this.repositoryFilmActors = repositoryFilmActors;
	}
	
	public void insertFilmActor(FilmActors peliculaActor) {
		repositoryFilmActors.insertFilmActors(peliculaActor);
	}

	public FilmActors filterAllPeliculaActor(String role) {
		return repositoryFilmActors.filterAllPeliculaActor(role);
	}


}
