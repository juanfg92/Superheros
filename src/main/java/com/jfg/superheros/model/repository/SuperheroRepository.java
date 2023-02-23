package com.jfg.superheros.model.repository;

import com.jfg.superheros.model.entity.Superhero;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SuperheroRepository extends CrudRepository<Superhero, Integer>, JpaSpecificationExecutor<Superhero> {


    Optional<Superhero> findByNameIgnoreCase(String name);


    Optional<Superhero> findById(Integer id);


    Set<Superhero> findAll();

    @Query(value = "SELECT s FROM Superhero s " +
            "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%',:name,'%'))")
    Set<Superhero> findByNameContent(@Param("name") String name);


    @Query(value = "SELECT s FROM Superhero s " +
            "WHERE LOWER(s.name) LIKE LOWER(:name) AND " +
            "s.id != :id")
    Optional<Superhero> findByNameContentDifferentId(@Param("name") String name, @Param("id") Integer id);

}