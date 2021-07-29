package com.devsuperior.uri2611.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query(nativeQuery = true, value = "SELECT "
			+ "a.id, "
			+ "a.name "
			+ "FROM Movies a "
			+ "JOIN genres b ON a.id_genres = b.id "
			+ "WHERE b.description = :genreName")
	List<MovieMinProjection> search1(String genreName);
	
	@Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDTO(obj.id, obj.name)"			
			+ "FROM Movie obj "			
			+ "WHERE obj.genre.description = :genreName")
	List<MovieMinDTO> search2(String genreName);

}


