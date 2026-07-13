package com.maarimo.hotel.service;

import com.maarimo.hotel.dto.request.HospedeRequest;
import com.maarimo.hotel.dto.response.HospedeResponse;
import com.maarimo.hotel.entity.Hospede;
import com.maarimo.hotel.exception.ResourceNotFoundException;
import com.maarimo.hotel.repository.HospedeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospedeService {

    private final HospedeRepository hospedeRepository;

    public HospedeService(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public HospedeResponse cadastrar(HospedeRequest request) {

        if (hospedeRepository.existsByCpf(request.getCpf())) {
            throw new RuntimeException("Já existe um hóspede com esse CPF.");
        }

        Hospede hospede = new Hospede();
        hospede.setNome(request.getNome());
        hospede.setCpf(request.getCpf());
        hospede.setTelefone(request.getTelefone());
        hospede.setEmail(request.getEmail());

        hospede = hospedeRepository.save(hospede);

        return converterParaResponse(hospede);
    }

    public List<HospedeResponse> listarTodos() {
        return hospedeRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public HospedeResponse buscarPorId(Long id) {

        Hospede hospede = hospedeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede não encontrado."));

        return converterParaResponse(hospede);
    }

    public HospedeResponse atualizar(Long id, HospedeRequest request) {

        Hospede hospede = hospedeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede não encontrado."));

        if (!hospede.getCpf().equals(request.getCpf())
                && hospedeRepository.existsByCpf(request.getCpf())) {
            throw new RuntimeException("Já existe um hóspede com esse CPF.");
        }

        hospede.setNome(request.getNome());
        hospede.setCpf(request.getCpf());
        hospede.setTelefone(request.getTelefone());
        hospede.setEmail(request.getEmail());

        hospede = hospedeRepository.save(hospede);

        return converterParaResponse(hospede);
    }

    public void excluir(Long id) {

        Hospede hospede = hospedeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hóspede não encontrado."));

        hospedeRepository.delete(hospede);
    }

    private HospedeResponse converterParaResponse(Hospede hospede) {

        HospedeResponse response = new HospedeResponse();

        response.setId(hospede.getId());
        response.setNome(hospede.getNome());
        response.setCpf(hospede.getCpf());
        response.setTelefone(hospede.getTelefone());
        response.setEmail(hospede.getEmail());

        return response;
    }
}