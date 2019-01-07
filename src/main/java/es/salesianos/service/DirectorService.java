package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Director;
import es.salesianos.repository.RepositoryDirector;


public class DirectorService {
	private RepositoryDirector repositoryDirector = new RepositoryDirector();

	public RepositoryDirector getRepository() {
		return repositoryDirector;
	}

	public void setRepository(RepositoryDirector repositoryDirector) {
		this.repositoryDirector = repositoryDirector;
	}
	
	public List<Director> listAllDirectors() {
		return repositoryDirector.searchAllDirectors();
	}

	
	public void searchAndDeleteDirector(Integer codDirector) {
		repositoryDirector.searchAndDeleteDirector(codDirector);
	}


	public void addDirector(Director director) {
		repositoryDirector.insertDirector(director);
	}


}
