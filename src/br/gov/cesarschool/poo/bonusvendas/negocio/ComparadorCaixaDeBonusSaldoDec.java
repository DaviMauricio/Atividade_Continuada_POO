package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;

public class ComparadorCaixaDeBonusSaldoDec implements Comparador {
    private static ComparadorCaixaDeBonusSaldoDec instance;

    private ComparadorCaixaDeBonusSaldoDec() {
    }

    public static ComparadorCaixaDeBonusSaldoDec getInstance() {
        if (instance == null) {
            instance = new ComparadorCaixaDeBonusSaldoDec();
        }
        return instance;
    }

    @Override
    public int comparar(Object o1, Object o2) {
        CaixaDeBonus c1 = (CaixaDeBonus) o1;
        CaixaDeBonus c2 = (CaixaDeBonus) o2;
        if (c1.getSaldo() > c2.getSaldo()) {
            return -1;
        } else if (c1.getSaldo() < c2.getSaldo()) {
            return 1;
        } else {
            return 0;
        }
    }
}
