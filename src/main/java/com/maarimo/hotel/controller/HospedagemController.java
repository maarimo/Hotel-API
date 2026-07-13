package com.maarimo.hotel.controller;

import com.maarimo.hotel.dto.request.HospedagemRequest;
import com.maarimo.hotel.dto.response.HospedagemResponse;
import com.maarimo.hotel.service.HospedagemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedagens")
public class HospedagemController {

    private final HospedagemService hospedagemService;

    public HospedagemController(HospedagemService hospedagemService) {
        this.hospedagemService = hospedagemService;
    }

    @PostMapping("/check-in")
    public HospedagemResponse realizarCheckIn(@RequestBody @Valid HospedagemRequest request) {
        return hospedagemService.realizarCheckIn(request);
    }

    @PutMapping("/{id}/check-out")
    public void realizarCheckOut(@PathVariable Long id) {
        hospedagemService.realizarCheckOut(id);
    }

    @GetMapping
    public List<HospedagemResponse> listarTodas() {
        return hospedagemService.listarTodas();
    }

    @GetMapping("/{id}")
    public HospedagemResponse buscarPorId(@PathVariable Long id) {
        return hospedagemService.buscarPorId(id);
    }

    @GetMapping("/hospede/{id}")
    public List<HospedagemResponse> buscarPorHospede(@PathVariable Long id) {
        return hospedagemService.buscarPorHospede(id);
    }

    @GetMapping("/quarto/{id}")
    public List<HospedagemResponse> buscarPorQuarto(@PathVariable Long id) {
        return hospedagemService.buscarPorQuarto(id);
    }
}
