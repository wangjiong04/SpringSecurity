package com.study.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.springsecurity.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}