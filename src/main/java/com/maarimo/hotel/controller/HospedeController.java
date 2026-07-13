package com.maarimo.hotel.controller;

import com.maarimo.hotel.dto.request.HospedeRequest;
import com.maarimo.hotel.dto.response.HospedeResponse;
import com.maarimo.hotel.service.HospedeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedes")
public class HospedeController {

    private final HospedeService hospedeService;

    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HospedeResponse cadastrar(@RequestBody @Valid HospedeRequest request) {
        return hospedeService.cadastrar(request);
    }

    @GetMapping
    public List<HospedeResponse> listarTodos() {
        return hospedeService.listarTodos();
    }

    @GetMapping("/{id}")
    public HospedeResponse buscarPorId(@PathVariable Long id) {
        return hospedeService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public HospedeResponse atualizar(@PathVariable Long id,
                                     @RequestBody @Valid HospedeRequest request) {
        return hospedeService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        hospedeService.excluir(id);
    }
}