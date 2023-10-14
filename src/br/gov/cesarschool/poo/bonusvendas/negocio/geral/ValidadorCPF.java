package br.gov.cesarschool.poo.bonusvendas.negocio.geral;

import java.util.InputMismatchException;

public class ValidadorCPF {
	
	// nenhum atributo
	
	private ValidadorCPF() {
        // Construtor privado,sem parametros sem implementa��o.
    }


	public static boolean ehCpfValido(String cpf) {
		//pagina de referenica: https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
		//cpf tem que ter 11 digitos
		//cpf nao pode ser uma seguencia de numeros iguais
		// chat gpt deu uma solu��o utilizando map.char() mas eu achei essa mais facil de entender
		
		if (cpf == null || cpf.length() != 11) {
			return false;
		}
		
		if (cpf.equals("00000000000") ||
	            cpf.equals("11111111111") ||
	            cpf.equals("22222222222") || cpf.equals("33333333333") ||
	            cpf.equals("44444444444") || cpf.equals("55555555555") ||
	            cpf.equals("66666666666") || cpf.equals("77777777777") ||
	            cpf.equals("88888888888") || cpf.equals("99999999999"))
	            return(false);
		
	
		char primeiroDigito10; // 10 � a referencia do peso
		char segundoDigito11; // 11 � a referencia do peso
        int soma, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            soma = 0;
            peso = 10;
            for (i=0; i<9; i++) {
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0
        // (48 eh a posicao de '0' na tabela ASCII)
            num = (int)(cpf.charAt(i) - 48);
            soma = soma + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11))
                primeiroDigito10 = '0';
            else primeiroDigito10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            soma = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            soma = soma + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11))
                 segundoDigito11 = '0';
            else segundoDigito11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((primeiroDigito10 == cpf.charAt(9)) && (segundoDigito11 == cpf.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
        }
		// fun��o extra pra gente saber o cpf mas pode deletar depos 
	
        public static String imprimeCPF(String CPF) {
            return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
            CPF.substring(6, 9) + "-" + CPF.substring(9, 11));

	
	}
	
}