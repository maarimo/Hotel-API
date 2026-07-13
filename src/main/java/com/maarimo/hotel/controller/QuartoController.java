package com.maarimo.hotel.controller;

import com.maarimo.hotel.dto.request.QuartoRequest;
import com.maarimo.hotel.dto.response.QuartoResponse;
import com.maarimo.hotel.service.QuartoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    private final QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuartoResponse cadastrar(@RequestBody @Valid QuartoRequest request) {
        return quartoService.cadastrar(request);
    }

    @GetMapping
    public List<QuartoResponse> listarTodos() {
        return quartoService.listarTodos();
    }

    @GetMapping("/{id}")
    public QuartoResponse buscarPorId(@PathVariable Long id) {
        return quartoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public QuartoResponse atualizar(@PathVariable Long id,
                                    @RequestBody @Valid QuartoRequest request) {
        return quartoService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        quartoService.excluir(id);
    }
}
