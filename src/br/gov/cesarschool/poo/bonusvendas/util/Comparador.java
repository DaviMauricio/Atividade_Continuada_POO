package br.gov.cesarschool.poo.bonusvendas.util;

public interface Comparador {
    /**
     * Compara dois objetos.
     *
     * @param o1 O primeiro objeto a ser comparado.
     * @param o2 O segundo objeto a ser comparado.
     * @return int Retorna 1 se o1 for maior que o2, 0 se o1 for igual a o2, e -1 se o1 for menor que o2.
     */
    int comparar(Object o1, Object o2);
}