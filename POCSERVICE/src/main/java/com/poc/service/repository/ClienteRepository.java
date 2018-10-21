package com.poc.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.service.api.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
