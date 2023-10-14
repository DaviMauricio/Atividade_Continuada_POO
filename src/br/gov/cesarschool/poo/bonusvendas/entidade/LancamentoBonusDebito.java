package br.gov.cesarschool.poo.bonusvendas.entidade;
import java.time.LocalDateTime;

public class LancamentoBonusDebito extends LancamentoBonus{
	private TipoResgate tipoResgate;
	private static final long serialVersionUID = 1L;

	
		public LancamentoBonusDebito (long numeroCaixaDeBonus,double valor, LocalDateTime dataHoraLancamento, TipoResgate tipoResgate) {
			super(numeroCaixaDeBonus, valor, dataHoraLancamento);
			this.tipoResgate=tipoResgate;
		}
		public TipoResgate getTipoResgate() {
			return tipoResgate;
	}

	
}