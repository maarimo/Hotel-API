package com.maarimo.hotel.repository;

import com.maarimo.hotel.entity.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {

    boolean existsByCpf(String cpf);

}