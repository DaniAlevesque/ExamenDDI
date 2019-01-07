package es.salesianos.service;

import java.util.List;

import es.salesianos.model.Actor;
import es.salesianos.repository.RepositoryActor;


public class ActorService {
	private RepositoryActor repositoryActor = new RepositoryActor();

	public RepositoryActor getRepository() {
		return repositoryActor;
	}

	public void setRepository(RepositoryActor repositoryActor) {
		this.repositoryActor = repositoryActor;
	}
	
	public List<Actor> listAllActors() {
		return repositoryActor.searchAllActors();
	}

	public void searchAndDeleteActor(Integer codActor) {
		repositoryActor.searchAndDeleteActor(codActor);
	}

	public void addActor(Actor actor) {
		repositoryActor.insertActor(actor);
	}

	public List<Actor> filterAllActor(int beginDate, int endDate) {
		return repositoryActor.filterAllActor(beginDate, endDate);
	}

	public List<Actor> selectAllActor() {
		return repositoryActor.selectAllActor();
	}

	public Actor filterAllDirector(String name) {
		return repositoryActor.filterAllDirector(name);
	}
}
