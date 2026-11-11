package com.karlmt.spring.app3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karlmt.spring.app3.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
