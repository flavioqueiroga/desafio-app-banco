package model.services;

import java.util.Set;
import java.util.function.Predicate;


import model.entities.Extrato;

public class ExtratoServices {

    public double filtroDoExtrato(Set<Extrato> extrato, Predicate<Extrato> criteria){
        double total = 0.0;
        for (Extrato e : extrato) {
            if (criteria.test(e)){
                total += e.getValor();
            }
        }
        return total;
    }

   





}
