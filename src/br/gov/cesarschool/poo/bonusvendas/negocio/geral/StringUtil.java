package br.gov.cesarschool.poo.bonusvendas.negocio.geral;

public class StringUtil {

	public static boolean ehNuloOuBranco(String str) {
		
		if(str == null) {
			return true;
		}
		
		String strSemEspacos = str.trim();
		
        return strSemEspacos.isEmpty();
			
	}

}
