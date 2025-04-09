package com.gabim.agendamento_api.controller;

import com.gabim.agendamento_api.model.Agendamento;
import com.gabim.agendamento_api.service.AgendamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento) {
        return service.criarAgendamento(agendamento);

    }

    @GetMapping
    public List<Agendamento> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}/confirmar")
    public Agendamento confirmar(@PathVariable Long id) {
        return service.confirmarAgendamento(id);
    }
     @PutMapping("/{id}/concluir")
    public ResponseEntity<Agendamento> marcarComoConcluido(@PathVariable Long id) {
        Optional<Agendamento> optional = agendamentoRepository.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Agendamento agendamento = optional.get();
        agendamento.setConcluido(true);
        agendamentoRepository.save(agendamento);

        return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletarAgendamento(id);
    }
}
