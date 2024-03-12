package model.enums;

import java.util.Arrays;
import java.util.List;

import model.exception.BancoException;

public enum TipoConta {
    CORRENTE("CC"),
    POUPANCA("CP");

    private String codigo;

    TipoConta(String codigo) {
        this.codigo = codigo;
    }

    public static TipoConta getPorCodigo(String cod) {
        List<TipoConta> tipos = Arrays.asList(TipoConta.values());
        return tipos
            .stream()
            .filter(t -> t.codigo.equals(cod))
            .findFirst()
            .orElseThrow(() -> new BancoException("Este tipo de conta não existe!"));
    }

}
