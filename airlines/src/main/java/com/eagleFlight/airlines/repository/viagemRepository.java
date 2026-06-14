package com.eagleFlight.airlines.repository;

import com.eagleFlight.airlines.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface viagemRepository extends JpaRepository<Viagem, Long> {
}
