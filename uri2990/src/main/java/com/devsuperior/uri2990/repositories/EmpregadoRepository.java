package com.devsuperior.uri2990.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.entities.Empregado;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

	@Query(nativeQuery = true, value = "SELECT "
			+ "a.cpf, "
			+ "a.enome, "
			+ "b.dnome "
			+ "FROM empregados a "
			+ "JOIN departamentos b on a.dnumero = b.dnumero "
			+ "where a.cpf NOT in "
			+ "( "
			+ "SELECT a.cpf	 "
			+ "FROM empregados a "
			+ "JOIN trabalha b on a.cpf = b.cpf_emp "
			+ ") "
			+ "order by a.cpf")
	List<EmpregadoDeptProjection> search1();
	
	
	@Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(obj.cpf,obj.enome,obj.departamento.dnome)"		
			+ "FROM Empregado obj "		
			+ "where obj.cpf NOT in "
			+ "( "
			+ "SELECT obj.cpf	 "
			+ "FROM Empregado obj "
			+ "JOIN obj.projetosOndeTrabalha "
			+ ") "
			+ "order by obj.cpf")
	List<EmpregadoDeptDTO> search2();
	
	@Query(nativeQuery = true, value = "SELECT a.cpf, a.enome,b.dnome "
			+ "FROM empregados a "		
			+ "JOIN departamentos b on a.dnumero = b.dnumero "
			+ "LEFT JOIN trabalha c on a.cpf = c.cpf_emp "
			+ "where c.cpf_emp IS NULL "		
			+ "order by a.cpf")
	List<EmpregadoDeptProjection> search3();
}
