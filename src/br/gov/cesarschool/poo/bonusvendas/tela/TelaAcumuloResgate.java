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

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.negocio.AcumuloResgateMediator;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

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
		shell.setLayout(new FormLayout());
		
		txtNumeroCaixa = new Text(shell, SWT.BORDER);
		FormData fd_txtNumeroCaixa = new FormData();
		fd_txtNumeroCaixa.bottom = new FormAttachment(0, 41);
		fd_txtNumeroCaixa.right = new FormAttachment(0, 352);
		fd_txtNumeroCaixa.top = new FormAttachment(0, 23);
		fd_txtNumeroCaixa.left = new FormAttachment(0, 174);
		txtNumeroCaixa.setLayoutData(fd_txtNumeroCaixa);
		txtNumeroCaixa.setToolTipText("");
		
		Button btnAcumular = new Button(shell, SWT.RADIO);
		FormData fd_btnAcumular = new FormData();
		fd_btnAcumular.right = new FormAttachment(0, 163);
		fd_btnAcumular.top = new FormAttachment(0, 47);
		fd_btnAcumular.left = new FormAttachment(0, 86);
		btnAcumular.setLayoutData(fd_btnAcumular);
		btnAcumular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		this.btnAcumular = btnAcumular;
		btnAcumular.setText("Acumular");
		
		Button btnResgatar = new Button(shell, SWT.RADIO);
		FormData fd_btnResgatar = new FormData();
		fd_btnResgatar.right = new FormAttachment(0, 241);
		fd_btnResgatar.top = new FormAttachment(0, 47);
		fd_btnResgatar.left = new FormAttachment(0, 164);
		btnResgatar.setLayoutData(fd_btnResgatar);
		this.btnResgatar = btnResgatar;
		btnResgatar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnResgatar.setText("Regastar");
		
		txtSaldo = new Text(shell, SWT.BORDER);
		FormData fd_txtSaldo = new FormData();
		fd_txtSaldo.bottom = new FormAttachment(0, 88);
		fd_txtSaldo.right = new FormAttachment(0, 226);
		fd_txtSaldo.top = new FormAttachment(0, 70);
		fd_txtSaldo.left = new FormAttachment(0, 62);
		txtSaldo.setLayoutData(fd_txtSaldo);
		txtSaldo.setEnabled(false);
		txtSaldo.setEditable(false);
		
		Combo btnTipoResgate = new Combo(shell, SWT.NONE);
		FormData fd_btnTipoResgate = new FormData();
		fd_btnTipoResgate.right = new FormAttachment(0, 157);
		fd_btnTipoResgate.top = new FormAttachment(0, 94);
		fd_btnTipoResgate.left = new FormAttachment(0, 17);
		btnTipoResgate.setLayoutData(fd_btnTipoResgate);
		btnTipoResgate.setEnabled(false);
		this.cmbTipoResgate = btnTipoResgate;
		btnTipoResgate.setItems(new String[] {"Serviço", "Produto", "Cash"});
		btnTipoResgate.setText("Tipo de Resgate");
		
		txtValor = new Text(shell, SWT.BORDER);
		FormData fd_txtValor = new FormData();
		fd_txtValor.bottom = new FormAttachment(0, 141);
		fd_txtValor.right = new FormAttachment(0, 226);
		fd_txtValor.top = new FormAttachment(0, 123);
		fd_txtValor.left = new FormAttachment(0, 62);
		txtValor.setLayoutData(fd_txtValor);
		txtValor.setEnabled(false);
		
		Button btnEscolherTipo = new Button(shell, SWT.BORDER);
		FormData fd_btnEscolherTipo = new FormData();
		fd_btnEscolherTipo.bottom = new FormAttachment(0, 167);
		fd_btnEscolherTipo.right = new FormAttachment(0, 157);
		fd_btnEscolherTipo.top = new FormAttachment(0, 147);
		fd_btnEscolherTipo.left = new FormAttachment(0, 17);
		btnEscolherTipo.setLayoutData(fd_btnEscolherTipo);
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
		btnEscolherTipo.setText("Acumular ou Resgatar");
		
		Button btnVoltar = new Button(shell, SWT.BORDER);
		FormData fd_btnVoltar = new FormData();
		fd_btnVoltar.bottom = new FormAttachment(0, 193);
		fd_btnVoltar.right = new FormAttachment(0, 157);
		fd_btnVoltar.top = new FormAttachment(0, 173);
		fd_btnVoltar.left = new FormAttachment(0, 17);
		btnVoltar.setLayoutData(fd_btnVoltar);
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
		btnVoltar.setText("Voltar");
		
		Label lblNumeroCaixa = new Label(shell, SWT.NONE);
		FormData fd_lblNumeroCaixa = new FormData();
		fd_lblNumeroCaixa.bottom = new FormAttachment(0, 41);
		fd_lblNumeroCaixa.right = new FormAttachment(0, 185);
		fd_lblNumeroCaixa.top = new FormAttachment(0, 26);
		fd_lblNumeroCaixa.left = new FormAttachment(0, 21);
		lblNumeroCaixa.setLayoutData(fd_lblNumeroCaixa);
		lblNumeroCaixa.setText("Número da Caixa de Bonus");
		
		Label lblOperacao = new Label(shell, SWT.NONE);
		FormData fd_lblOperacao = new FormData();
		fd_lblOperacao.bottom = new FormAttachment(0, 62);
		fd_lblOperacao.top = new FormAttachment(0, 47);
		fd_lblOperacao.left = new FormAttachment(0, 21);
		lblOperacao.setLayoutData(fd_lblOperacao);
		lblOperacao.setText("Operação:");
		
		Label lblSaldo = new Label(shell, SWT.NONE);
		FormData fd_lblSaldo = new FormData();
		fd_lblSaldo.bottom = new FormAttachment(0, 85);
		fd_lblSaldo.right = new FormAttachment(0, 56);
		fd_lblSaldo.top = new FormAttachment(0, 70);
		fd_lblSaldo.left = new FormAttachment(0, 23);
		lblSaldo.setLayoutData(fd_lblSaldo);
		lblSaldo.setEnabled(false);
		lblSaldo.setText("Saldo:");
		
		Label lblValor = new Label(shell, SWT.NONE);
		FormData fd_lblValor = new FormData();
		fd_lblValor.bottom = new FormAttachment(0, 138);
		fd_lblValor.right = new FormAttachment(0, 54);
		fd_lblValor.top = new FormAttachment(0, 123);
		fd_lblValor.left = new FormAttachment(0, 21);
		lblValor.setLayoutData(fd_lblValor);
		lblValor.setEnabled(false);
		lblValor.setText("Valor:");
		
		Button btnBuscar = new Button(shell, SWT.BORDER);
		FormData fd_btnBuscar = new FormData();
		fd_btnBuscar.bottom = new FormAttachment(0, 224);
		fd_btnBuscar.right = new FormAttachment(0, 157);
		fd_btnBuscar.top = new FormAttachment(0, 199);
		fd_btnBuscar.left = new FormAttachment(0, 17);
		btnBuscar.setLayoutData(fd_btnBuscar);
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
		btnBuscar.setText("Buscar");


	}



	
}