package model.entities;

import java.time.LocalDate;

public class Cliente {

    private String nome;
    private Long cpf;
    private LocalDate dataNascimento;
    
    public Cliente(String nome, Long cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }


    public Long getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    

    




}
