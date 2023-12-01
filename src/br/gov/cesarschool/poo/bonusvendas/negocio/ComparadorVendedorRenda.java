package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;

public class ComparadorVendedorRenda implements Comparador {
    private static ComparadorVendedorRenda instance;

    private ComparadorVendedorRenda() {
    }

    public static ComparadorVendedorRenda getInstance() {
        if (instance == null) {
            instance = new ComparadorVendedorRenda();
        }
        return instance;
    }

    @Override
    public int comparar(Object o1, Object o2) {
        Vendedor v1 = (Vendedor) o1;
        Vendedor v2 = (Vendedor) o2;
        if (v1.getRenda() > v2.getRenda()) {
            return 1;
        } else if (v1.getRenda() < v2.getRenda()) {
            return -1;
        } else {
            return 0;
        }
    }
}