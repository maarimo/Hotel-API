package com.maarimo.hotel.repository;

import com.maarimo.hotel.entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    boolean existsByNumero(Integer numero);

}
