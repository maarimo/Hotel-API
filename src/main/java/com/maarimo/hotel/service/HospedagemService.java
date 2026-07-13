package com.maarimo.hotel.service;

import com.maarimo.hotel.dto.request.HospedagemRequest;
import com.maarimo.hotel.dto.response.HospedagemResponse;
import com.maarimo.hotel.entity.*;
import com.maarimo.hotel.exception.ResourceNotFoundException;
import com.maarimo.hotel.repository.HospedagemRepository;
import com.maarimo.hotel.repository.HospedeRepository;
import com.maarimo.hotel.repository.QuartoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospedagemService {

    private final HospedagemRepository hospedagemRepository;
    private final HospedeRepository hospedeRepository;
    private final QuartoRepository quartoRepository;

    public HospedagemService(HospedagemRepository hospedagemRepository,
                             HospedeRepository hospedeRepository,
                             QuartoRepository quartoRepository) {
        this.hospedagemRepository = hospedagemRepository;
        this.hospedeRepository = hospedeRepository;
        this.quartoRepository = quartoRepository;
    }

    public HospedagemResponse realizarCheckIn(HospedagemRequest request) {

        Hospede hospede = hospedeRepository.findById(request.getHospedeId())
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede não encontrado."));

        Quarto quarto = quartoRepository.findById(request.getQuartoId())
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado."));

        if (quarto.getStatus() != StatusQuarto.DISPONIVEL) {
            throw new RuntimeException("O quarto não está disponível.");
        }

        if (!request.getDataSaida().isAfter(request.getDataEntrada())) {
            throw new RuntimeException("A data de saída deve ser posterior à data de entrada.");
        }

        long dias = ChronoUnit.DAYS.between(
                request.getDataEntrada(),
                request.getDataSaida()
        );

        BigDecimal valorTotal = quarto.getValorDiaria()
                .multiply(BigDecimal.valueOf(dias));

        Hospedagem hospedagem = new Hospedagem();

        hospedagem.setHospede(hospede);
        hospedagem.setQuarto(quarto);
        hospedagem.setDataEntrada(request.getDataEntrada());
        hospedagem.setDataSaida(request.getDataSaida());
        hospedagem.setValorTotal(valorTotal);
        hospedagem.setStatus(StatusHospedagem.ATIVA);

        quarto.setStatus(StatusQuarto.OCUPADO);

        quartoRepository.save(quarto);
        hospedagem = hospedagemRepository.save(hospedagem);

        return converterParaResponse(hospedagem);
    }

    public List<HospedagemResponse> listarTodas() {
        return hospedagemRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public HospedagemResponse buscarPorId(Long id) {

        Hospedagem hospedagem = hospedagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospedagem não encontrada."));

        return converterParaResponse(hospedagem);
    }

    public void realizarCheckOut(Long id) {

        Hospedagem hospedagem = hospedagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospedagem não encontrada."));

        if (hospedagem.getStatus() == StatusHospedagem.FINALIZADA) {
            throw new RuntimeException("A hospedagem já foi finalizada.");
        }

        hospedagem.setStatus(StatusHospedagem.FINALIZADA);

        Quarto quarto = hospedagem.getQuarto();
        quarto.setStatus(StatusQuarto.DISPONIVEL);

        hospedagemRepository.save(hospedagem);
        quartoRepository.save(quarto);
    }

    public List<HospedagemResponse> buscarPorHospede(Long hospedeId) {
        return hospedagemRepository.findByHospedeId(hospedeId)
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public List<HospedagemResponse> buscarPorQuarto(Long quartoId) {
        return hospedagemRepository.findByQuartoId(quartoId)
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    private HospedagemResponse converterParaResponse(Hospedagem hospedagem) {

        HospedagemResponse response = new HospedagemResponse();

        response.setId(hospedagem.getId());
        response.setHospede(hospedagem.getHospede().getNome());
        response.setQuarto(hospedagem.getQuarto().getNumero());
        response.setDataEntrada(hospedagem.getDataEntrada());
        response.setDataSaida(hospedagem.getDataSaida());
        response.setValorTotal(hospedagem.getValorTotal());
        response.setStatus(hospedagem.getStatus());

        return response;
    }
}