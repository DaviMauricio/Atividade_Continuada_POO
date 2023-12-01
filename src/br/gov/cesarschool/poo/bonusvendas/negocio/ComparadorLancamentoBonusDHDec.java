/* Nova classe pública: ComparadorLancamentoBonusDHDec
(pacote br.gov.cesarschool.poo.bonusvendas.negocio)
➔ Deve ser um Singleton com método getInstance().
➔ Deve implementar a interface java.util.Comparator do JAVA. Não usar o recurso de
tipo parametrizado (o <> sugerido pelas IDEs).
➔ Deve implementar o método compare considerando que serão recebidos dois
objetos do tipo LancamentoBonus, retornando a comparação das
dataHoraLancamento dos lançamentos, ou seja, se uma dataHoraLancamento (que é
do tipo LocalDateTime) é maior, menor ou igual a outra dataHoraLancamento. Como
este comparador vai ser usado para ordenar lançamentos em ordem DECRESCENTE
DE DATA HORA LANÇAMENTO, a lógica de retorno deve ser invertida em relação à
especificação do método compare da interface java.util.Comparator. */

package br.gov.cesarschool.poo.bonusvendas.negocio;

import java.util.Comparator;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

public class ComparadorLancamentoBonusDHDec implements Comparator<LancamentoBonus> {
    private static ComparadorLancamentoBonusDHDec instance;

    private ComparadorLancamentoBonusDHDec() {
    }

    public static ComparadorLancamentoBonusDHDec getInstance() {
        if (instance == null) {
            instance = new ComparadorLancamentoBonusDHDec();
        }
        return instance;
    }

    @Override
    public int compare(LancamentoBonus o1, LancamentoBonus o2) {
        if (o1.getDataHoraLancamento().isAfter(o2.getDataHoraLancamento())) {
            return -1;
        } else if (o1.getDataHoraLancamento().isBefore(o2.getDataHoraLancamento())) {
            return 1;
        } else {
            return 0;
        }
    }
}