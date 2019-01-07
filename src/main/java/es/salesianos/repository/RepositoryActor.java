package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.salesianos.connection.ConnectionH2;
import es.salesianos.connection.ConnectionManager;
import es.salesianos.model.Actor;
import es.salesianos.model.Director;
import es.salesianos.model.Film;
import es.salesianos.model.FilmActors;



public class RepositoryActor {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();


	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void insertActor(Actor actor) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO ACTOR (NAME,YEAROFBIRTHDATE)" +
					"VALUES (?, ?)");
			preparedStatement.setString(1, actor.getName());
			preparedStatement.setInt(2, actor.getYearofbirthday());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		
		
		manager.close(conn);
	}

	public List<Actor> searchAllActors() {
		List<Actor> listOwners = new ArrayList<Actor>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM ACTOR");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorInDataBase = new Actor();
				
				actorInDataBase.setCod(resultSet.getInt(1));
				actorInDataBase.setName(resultSet.getString(2));
				actorInDataBase.setYearofbirthday(resultSet.getInt(3));
			
				
				listOwners.add(actorInDataBase);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(resultSet);
			close(prepareStatement);
			manager.close(conn);
		}

		return listOwners;
	}

	public Actor searchAndDeleteActor(Integer codActor) {
		Actor ownerInDatabase = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("DELETE FROM ACTOR WHERE COD = ?");
			prepareStatement.setInt(1, codActor);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(prepareStatement);
		}
		manager.close(conn);
		return ownerInDatabase;
	}
	

	public List<Actor> filterAllActor(int beginDate, int endDate) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Actor> list = new ArrayList<Actor>();
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM ACTOR WHERE yearOfBirthDate BETWEEN (?) AND (?)");
			preparedStatement.setInt(1, beginDate);
			preparedStatement.setInt(2, endDate);
			System.out.println("llego");
			System.out.println(beginDate);
			System.out.println(endDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actor = new Actor();
				actor.setCod(resultSet.getInt(1));
				actor.setName(resultSet.getNString(2));
				actor.setYearofbirthday(resultSet.getInt(3));
				list.add(actor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}
	
	public List<Actor> selectAllActor() {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		List<Actor> list = new ArrayList<Actor>();
		try {
			preparedStatement = conn
					.prepareStatement("SELECT * FROM ACTOR");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actor = new Actor();
				actor.setCod(resultSet.getInt(1));
				actor.setName(resultSet.getNString(2));
				actor.setYearofbirthday(resultSet.getInt(3));
				list.add(actor);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return list;
	}

	public Actor filterAllDirector(String name) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		Actor actor = null;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM Actor WHERE name = (?)");
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Actor actorfromDataBase = new Actor();
				actorfromDataBase.setCod(resultSet.getInt(1));
				actorfromDataBase.setName(resultSet.getString(2));
				actorfromDataBase.setYearofbirthday(resultSet.getInt(3));
				actor = actorfromDataBase;
			}

			preparedStatement = conn.prepareStatement("SELECT * FROM FILMACTOR where codactor=" + actor.getCod());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				FilmActors peliculaActorfromDataBase = new FilmActors();
				peliculaActorfromDataBase.setCodPelicula(resultSet.getInt(4));
				actor.getPeliculaActor().add(peliculaActorfromDataBase);
			}

			int index = 0;
			for (FilmActors peliculaActor : actor.getPeliculaActor()) {

				preparedStatement = conn
						.prepareStatement("SELECT * FROM FILM where cod=" + peliculaActor.getCodPelicula());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Film peliculafromDataBase = new Film();
					peliculafromDataBase.setTITTLE(resultSet.getString(2));
					peliculafromDataBase.setCODOWNER(resultSet.getInt(3));
					actor.getPeliculaActor().get(index).setFilm(peliculafromDataBase);
				}
				index++;
			}
			index = 0;
			for (FilmActors peliculaActor : actor.getPeliculaActor()) {
				preparedStatement = conn.prepareStatement(
						"SELECT * FROM DIRECTOR where COD=" + peliculaActor.getFilm().getCODOWNER());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Director directorfromDataBase = new Director();
					directorfromDataBase.setName(resultSet.getString(2));
					actor.getPeliculaActor().get(index).getFilm().setDirector(directorfromDataBase);
				}
				index++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
			manager.close(conn);
		}
		return actor;

	}

}
