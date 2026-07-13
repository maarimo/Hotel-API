package com.maarimo.hotel.repository;

import com.maarimo.hotel.entity.Hospedagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospedagemRepository extends JpaRepository<Hospedagem, Long> {

    List<Hospedagem> findByHospedeId(Long hospedeId);

    List<Hospedagem> findByQuartoId(Long quartoId);

}
