package br.gov.cesarschool.poo.bonusvendas.tela;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import br.gov.cesarschool.poo.bonusvendas.daos.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.negocio.AcumuloResgateMediator;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class TelaAcumuloResgate {
	
	protected Shell shell;
	
	private CaixaDeBonusDAO caixaDeBonusDAO = new CaixaDeBonusDAO();
	private AcumuloResgateMediator mediator = AcumuloResgateMediator.getInstance();

	private Text txtNumeroCaixa;
	private Text txtSaldo;
	private Text txtValor;
	private Button btnAcumular;
	private Button btnResgatar;
	private Combo cmbTipoResgate;
	private Button btnEscolherTipo;
	private Button btnVoltar;
	private Button btnBuscar;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaAcumuloResgate window = new TelaAcumuloResgate();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(550, 350);
		shell.setText("SWT Application");
		
		txtNumeroCaixa = new Text(shell, SWT.BORDER);
		txtNumeroCaixa.setToolTipText("");
		txtNumeroCaixa.setBounds(174, 23, 178, 18);
		
		Button btnAcumular = new Button(shell, SWT.RADIO);
		btnAcumular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		this.btnAcumular = btnAcumular;
		btnAcumular.setBounds(86, 47, 77, 15);
		btnAcumular.setText("Acumular");
		
		Button btnResgatar = new Button(shell, SWT.RADIO);
		this.btnResgatar = btnResgatar;
		btnResgatar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnResgatar.setBounds(164, 47, 77, 15);
		btnResgatar.setText("Regastar");
		
		txtSaldo = new Text(shell, SWT.BORDER);
		txtSaldo.setEnabled(false);
		txtSaldo.setEditable(false);
		txtSaldo.setBounds(62, 70, 164, 18);
		
		Combo btnTipoResgate = new Combo(shell, SWT.NONE);
		btnTipoResgate.setEnabled(false);
		this.cmbTipoResgate = btnTipoResgate;
		btnTipoResgate.setItems(new String[] {"Serviço", "Produto", "Cash"});
		btnTipoResgate.setBounds(17, 94, 140, 23);
		btnTipoResgate.setText("Tipo de Resgate");
		
		txtValor = new Text(shell, SWT.BORDER);
		txtValor.setEnabled(false);
		txtValor.setBounds(62, 123, 164, 18);
		
		Button btnEscolherTipo = new Button(shell, SWT.BORDER);
		btnEscolherTipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(btnAcumular.getSelection()) {
					btnEscolherTipo.setText("Acumular");
					mediator.acumularBonus(Long.parseLong(txtNumeroCaixa.getText()), Double.parseDouble(txtValor.getText()));
					JOptionPane.showMessageDialog(null, 
					"O valor foi creditado com sucesso");
				} else if(btnResgatar.getSelection()) {
					btnEscolherTipo.setText("Resgatar");
					mediator.resgatar(Long.parseLong(txtNumeroCaixa.getText()), Double.parseDouble(txtValor.getText()), TipoResgate.values()[btnTipoResgate.getSelectionIndex()]);
					JOptionPane.showMessageDialog(null, 
					"o valor foi resgatado com sucesso!");
				}
			}
		});
		btnEscolherTipo.setEnabled(false);
		this.btnEscolherTipo = btnEscolherTipo;
		btnEscolherTipo.setBounds(17, 147, 140, 20);
		btnEscolherTipo.setText("Acumular ou Resgatar");
		
		Button btnVoltar = new Button(shell, SWT.BORDER);
		btnVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				txtValor.setText(null);
				txtValor.setEnabled(false);
				btnTipoResgate.setEnabled(false);
				btnEscolherTipo.setEnabled(false);
				btnVoltar.setEnabled(false);
				txtNumeroCaixa.setText(null);
				txtSaldo.setText(null);
				txtSaldo.setEnabled(false);
				btnAcumular.setSelection(false);
				btnResgatar.setSelection(false);
			}
		});
		btnVoltar.setEnabled(false);
		this.btnVoltar = btnVoltar;
		btnVoltar.setBounds(17, 173, 140, 20);
		btnVoltar.setText("Voltar");
		
		Label lblNumeroCaixa = new Label(shell, SWT.NONE);
		lblNumeroCaixa.setBounds(21, 26, 164, 15);
		lblNumeroCaixa.setText("Número da Caixa de Bonus");
		
		Label lblOperacao = new Label(shell, SWT.NONE);
		lblOperacao.setBounds(21, 47, 59, 15);
		lblOperacao.setText("Operação:");
		
		Label lblSaldo = new Label(shell, SWT.NONE);
		lblSaldo.setEnabled(false);
		lblSaldo.setBounds(23, 70, 33, 15);
		lblSaldo.setText("Saldo:");
		
		Label lblValor = new Label(shell, SWT.NONE);
		lblValor.setEnabled(false);
		lblValor.setBounds(21, 123, 33, 15);
		lblValor.setText("Valor:");
		
		Button btnBuscar = new Button(shell, SWT.BORDER);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				CaixaDeBonus caixaDeBonus = caixaDeBonusDAO.buscar(Long.parseLong(txtNumeroCaixa.getText()));
				if(caixaDeBonus == null) {
					JOptionPane.showMessageDialog(null, 
					"Caixa de bonus inexistente!");
				} else {
					txtSaldo.setText(String.valueOf(caixaDeBonus.getSaldo()));
					txtSaldo.setEnabled(true);
					lblSaldo.setEnabled(true);
					if(btnAcumular.getSelection()) {
						txtValor.setEnabled(true);
						btnEscolherTipo.setEnabled(true);
						btnVoltar.setEnabled(true);
					} else if(btnResgatar.getSelection()) {
						btnTipoResgate.setEnabled(true);
						txtValor.setEnabled(true);
						btnEscolherTipo.setEnabled(true);
						btnVoltar.setEnabled(true);
					}
				}
				


			}
		});
		this.btnBuscar = btnBuscar;
		btnBuscar.setBounds(17, 199, 140, 25);
		btnBuscar.setText("Buscar");


	}



	
}