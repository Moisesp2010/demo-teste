package com.demoteste.demoteste.controller;


import com.demoteste.demoteste.entities.Pessoa;
import com.demoteste.demoteste.repositories.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<Pessoa> getPessoas(){
        return (ResponseEntity<Pessoa>) pessoaRepository.findAll();
    }

    private void body(List<Pessoa> all) {
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pessoa não encontrada! Id: " + id)
        );

        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping
    public Pessoa insertPessoa(@RequestBody Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updatePessoaById(@PathVariable Long id, @RequestBody Pessoa p){
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(()
                -> new RuntimeException(" " + id));

        pessoa.setNome(p.getNome());
        pessoa.setDocumento(p.getDocumento());
        pessoa.setEmail(p.getEmail());
        pessoa.setTelefone(p.getTelefone());
        pessoa.setTipoDocumento(p.getTipoDocumento());

        final Pessoa updatedPessoa = pessoaRepository.save(pessoa);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePessoa(@PathVariable Long id){
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(()
                -> new RuntimeException(" " + id));

        pessoaRepository.delete(pessoa);
        return ResponseEntity.ok().body(true);
    }


}
