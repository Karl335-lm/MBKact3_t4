package com.karlmt.spring.app3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karlmt.spring.app3.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}