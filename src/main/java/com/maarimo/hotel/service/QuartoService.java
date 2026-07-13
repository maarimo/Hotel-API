package com.maarimo.hotel.service;

import com.maarimo.hotel.dto.request.QuartoRequest;
import com.maarimo.hotel.dto.response.QuartoResponse;
import com.maarimo.hotel.entity.Quarto;
import com.maarimo.hotel.entity.StatusQuarto;
import com.maarimo.hotel.exception.ResourceNotFoundException;
import com.maarimo.hotel.repository.QuartoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuartoService {

    private final QuartoRepository quartoRepository;

    public QuartoService(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    public QuartoResponse cadastrar(QuartoRequest request) {

        if (quartoRepository.existsByNumero(request.getNumero())) {
            throw new RuntimeException("Já existe um quarto com esse número.");
        }

        Quarto quarto = new Quarto();
        quarto.setNumero(request.getNumero());
        quarto.setTipo(request.getTipo());
        quarto.setValorDiaria(request.getValorDiaria());
        quarto.setStatus(StatusQuarto.DISPONIVEL);

        quarto = quartoRepository.save(quarto);

        return converterParaResponse(quarto);
    }

    public List<QuartoResponse> listarTodos() {
        return quartoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public QuartoResponse buscarPorId(Long id) {

        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado."));

        return converterParaResponse(quarto);
    }

    public QuartoResponse atualizar(Long id, QuartoRequest request) {

        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado."));

        if (!quarto.getNumero().equals(request.getNumero())
                && quartoRepository.existsByNumero(request.getNumero())) {
            throw new RuntimeException("Já existe um quarto com esse número.");
        }

        quarto.setNumero(request.getNumero());
        quarto.setTipo(request.getTipo());
        quarto.setValorDiaria(request.getValorDiaria());

        quarto = quartoRepository.save(quarto);

        return converterParaResponse(quarto);
    }

    public void excluir(Long id) {

        Quarto quarto = quartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quarto não encontrado."));

        quartoRepository.delete(quarto);
    }

    private QuartoResponse converterParaResponse(Quarto quarto) {

        QuartoResponse response = new QuartoResponse();

        response.setId(quarto.getId());
        response.setNumero(quarto.getNumero());
        response.setTipo(quarto.getTipo());
        response.setValorDiaria(quarto.getValorDiaria());
        response.setStatus(quarto.getStatus());

        return response;
    }
}