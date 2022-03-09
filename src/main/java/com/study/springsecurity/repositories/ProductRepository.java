package com.study.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springsecurity.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}