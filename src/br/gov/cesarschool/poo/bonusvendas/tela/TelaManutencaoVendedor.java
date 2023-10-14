package br.gov.cesarschool.poo.bonusvendas.tela;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;

import br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.StringUtil;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;

import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Text;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
//import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;

public class TelaManutencaoVendedor {

	protected Shell shell;
	private VendedorMediator mediator = VendedorMediator.getInstance();
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text txtCpf;
	private Text txtNomeCompleto;
	private Text txtDataDeNascimento;
	private Text txtRenda;
	private Text txtLogradouro;
	private Text txtNumero;
	private Text txtComplemento;
	private Text txtCep;
	private Text txtCidade;
	
	private boolean ignoreModifyEvent = false;
	private boolean ignoreModifyEventCEP = false;
	private boolean ignoreModifyEventRenda = false;
	private boolean ignoreModifyEventData = false;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaManutencaoVendedor window = new TelaManutencaoVendedor();
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
		//display.dispose();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 539);
		shell.setText("SWT Application");
		
		txtCpf = formToolkit.createText(shell, "New Text", SWT.NONE);
		txtCpf.setText("");
		txtCpf.setBounds(125, 37, 276, 21);
		addCPFFormatter(txtCpf);
		
		txtNomeCompleto = formToolkit.createText(shell, "New Text", SWT.NONE);
		txtNomeCompleto.setText("");
		txtNomeCompleto.setBounds(125, 64, 276, 21);
		addStringValidation(txtNomeCompleto);
		
		Button btnRadioFem = new Button(shell, SWT.RADIO);
		btnRadioFem.setBounds(125, 91, 90, 16);
		formToolkit.adapt(btnRadioFem, true, true);
		btnRadioFem.setText("Feminino");
		
		Button btnRadioMasc = new Button(shell, SWT.RADIO);
		btnRadioMasc.setBounds(228, 91, 90, 16);
		formToolkit.adapt(btnRadioMasc, true, true);
		btnRadioMasc.setText("Masculino");
		
		txtDataDeNascimento = formToolkit.createText(shell, "New Text", SWT.NONE);
		txtDataDeNascimento.setToolTipText("Data de Nascimento");
		txtDataDeNascimento.setText("");
		txtDataDeNascimento.setBounds(125, 113, 276, 21);
		addDataFormatter(txtDataDeNascimento);
		
		Label lblCpf = formToolkit.createLabel(shell, "CPF", SWT.NONE);
		lblCpf.setBounds(10, 40, 55, 15);
		
		Label lblNomeCompleto = formToolkit.createLabel(shell, "Nome completo", SWT.NONE);
		lblNomeCompleto.setBounds(10, 67, 99, 15);
		
		Label lblSexo = formToolkit.createLabel(shell, "Sexo", SWT.NONE);
		lblSexo.setBounds(10, 91, 55, 15);
		
		Label lblDataDeNascimento = formToolkit.createLabel(shell, "Data de Nascimento", SWT.NONE);
		lblDataDeNascimento.setBounds(10, 113, 107, 15);
		
		Label lblRenda = formToolkit.createLabel(shell, "Renda", SWT.NONE);
		lblRenda.setBounds(10, 143, 55, 15);
		
		txtRenda = formToolkit.createText(shell, "New Text", SWT.NONE);
		txtRenda.setText("");
		txtRenda.setBounds(125, 140, 276, 21);
		addRendaFormatter(txtRenda);
		
		Label lblLogradouro = formToolkit.createLabel(shell, "Logradouro", SWT.NONE);
		lblLogradouro.setBounds(10, 171, 73, 15);
		
		txtLogradouro = formToolkit.createText(shell, "New Text", SWT.NONE);
		txtLogradouro.setText("");
		txtLogradouro.setBounds(125, 165, 276, 21);
		addStringValidation(txtLogradouro);
		
		Label lblNmero = formToolkit.createLabel(shell, "Número", SWT.NONE);
		lblNmero.setBounds(10, 192, 55, 15);
		
		txtNumero = formToolkit.createText(shell, "New Text", SWT.NONE);
		txtNumero.setText("");
		txtNumero.setBounds(125, 189, 276, 21);
		addNumeroFormatter(txtNumero);
		
		Label lblComplemento = formToolkit.createLabel(shell, "Complemento", SWT.NONE);
		lblComplemento.setBounds(10, 213, 99, 15);
		
		txtComplemento = formToolkit.createText(shell, "New Text", SWT.NONE);
		txtComplemento.setText("");
		txtComplemento.setBounds(125, 213, 276, 21);
		
		Label lblCep = formToolkit.createLabel(shell, "CEP", SWT.NONE);
		lblCep.setBounds(10, 243, 55, 15);
		
		txtCep = formToolkit.createText(shell, "New Text", SWT.NONE);
		txtCep.setText("");
		txtCep.setBounds(125, 240, 276, 21);
		addCEPFormatter(txtCep);
		
		Label lblCidade = formToolkit.createLabel(shell, "Cidade", SWT.NONE);
		lblCidade.setBounds(10, 270, 55, 15);
		
		txtCidade = formToolkit.createText(shell, "New Text", SWT.NONE);
		txtCidade.setText("");
		txtCidade.setBounds(125, 267, 276, 21);
		addStringValidation(txtCidade);
		
		Label lblEstado = formToolkit.createLabel(shell, "Estado", SWT.NONE);
		lblEstado.setBounds(10, 297, 55, 15);
		
		Combo comboEstado = new Combo(shell, SWT.NONE);
		comboEstado.setItems(new String[] {"Acre", "Amapá", "Amazonas", "Alagoas", "Aracajú", "Bahia", "Ceará", "Distrito Federal", "Espírito Santo", "Goias", "Maranhão", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Sul", "Rio Grande do Norte", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Tocantins"});
		comboEstado.setBounds(125, 294, 276, 23);
		formToolkit.adapt(comboEstado);
		formToolkit.paintBordersFor(comboEstado);
		
		Button btnEnviar = new Button(shell, SWT.NONE);
		btnEnviar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// validação CPF
				String cpf = txtCpf.getText();
				cpf = cpf.replace(".", "");
				cpf = cpf.replace("-", "");
				
				if(ValidadorCPF.ehCpfValido(cpf) == false) {
					JOptionPane.showMessageDialog(null, "CPF inválido");
					return;
				}
				if(mediator.buscar(cpf) != null) {
					JOptionPane.showMessageDialog(null, "CPF já cadastrado!");
					return;
				}
				// validação Nome completo
				String nome = txtNomeCompleto.getText();
				if(StringUtil.ehNuloOuBranco(nome)) {
					JOptionPane.showMessageDialog(null, "Nome Completo inválido");
					return;
				}
				// Validação sexo
				Sexo sexo = null;
				if (btnRadioFem.getSelection()) {
					sexo = Sexo.FEMININO;
				}else if(btnRadioMasc.getSelection()) {
					sexo = Sexo.MASCULINO;
				}else {
					JOptionPane.showMessageDialog(null, "Sexo inválido!");
					return;
				}
				// Validção data de nascimento
				String data = txtDataDeNascimento.getText();
				String[] dmy = data.split("/");
				if(dmy.length != 3) {
					JOptionPane.showMessageDialog(null, "Data de Nascimento Inválida!");
					return;
				}
				
				int d = Integer.parseInt(dmy[1]);
				int m = Integer.parseInt(dmy[2]);
				int y = Integer.parseInt(dmy[3]);
				
				if(m > 12 || m < 1 || d > 31 || d < 1 || y > 2005 || y < 1990) {
					JOptionPane.showMessageDialog(null, "Data de Nascimento Inválida!");
					return;
				}
				// Validação renda
				if (StringUtil.ehNuloOuBranco(txtRenda.getText())) {
					JOptionPane.showMessageDialog(null, "Renda inválida!");
					return;
				}
				// Validação logradouro
				if (StringUtil.ehNuloOuBranco(txtLogradouro.getText())) {
					JOptionPane.showMessageDialog(null, "Logradouro inválido!");
					return;
				}
				// Validação numero
				if (StringUtil.ehNuloOuBranco(txtNumero.getText())) {
					JOptionPane.showMessageDialog(null, "Número inválido!");
					return;
				}
				// Validação complemento
				if (StringUtil.ehNuloOuBranco(txtComplemento.getText())) {
					JOptionPane.showMessageDialog(null, "Complemento inválido!");
					return;
				}
				// Validação CEP
				if (StringUtil.ehNuloOuBranco(txtCep.getText())) {
					JOptionPane.showMessageDialog(null, "CEP inválido!");
					return;
				}
				// Validação Estado
				if (StringUtil.ehNuloOuBranco(comboEstado.getText())) {
					JOptionPane.showMessageDialog(null, "Estado inválido!");
					return;
				}
				
				Endereco endereco = new Endereco(txtLogradouro.getText(), Integer.parseInt(txtNumero.getText()), txtComplemento.getText(), txtCep.getText(), txtCidade.getText(), comboEstado.getText(), "Brasil");
				Vendedor vendedor = new Vendedor(cpf, nome, sexo, LocalDate.of(y, m, y), Double.parseDouble(txtRenda.getText()), endereco);
				
				mediator.incluir(vendedor);
				JOptionPane.showMessageDialog(null, "Vendedor cadastrado com sucesso!");
				shell.close();
				
				TelaAcumuloResgate telaAcumuloResgate = new TelaAcumuloResgate();
				telaAcumuloResgate.open();
				
			
			}
		});
		btnEnviar.setBounds(173, 373, 75, 25);
		formToolkit.adapt(btnEnviar, true, true);
		btnEnviar.setText("Enviar");

	}
	
	// Validação para String que só podem conter letras
	private void addStringValidation(final Text text) {
        text.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                if (!e.text.matches("[a-zA-Z .]*")) {
                    e.doit = false;
                }
            }
        });
    }
	
	private void addCPFFormatter(final Text text) {
	    text.addModifyListener(new ModifyListener() {
	        @Override
	        public void modifyText(ModifyEvent e) {
	            if (ignoreModifyEvent) {
	                return;
	            }

	            ignoreModifyEvent = true;

	            String currentText = text.getText().replaceAll("[^0-9]", "");
	            StringBuilder formattedText = new StringBuilder();
	            int length = currentText.length();

	            for (int i = 0; i < length; i++) {
	                formattedText.append(currentText.charAt(i));
	                if (i == 2 || i == 5) {
	                    formattedText.append(".");
	                } else if (i == 8) {
	                    formattedText.append("-");
	                }
	            }

	         
	            if (length > 11) {
	                currentText = currentText.substring(0, 11);
	                length = 11;
	            }
	            
	            
	            if (length == 11) {
	                formattedText.setLength(0); 
	                formattedText.append(currentText.substring(0, 3));
	                formattedText.append(".");
	                formattedText.append(currentText.substring(3, 6));
	                formattedText.append(".");
	                formattedText.append(currentText.substring(6, 9));
	                formattedText.append("-");
	                formattedText.append(currentText.substring(9, 11));
	            }

	            text.setText(formattedText.toString());
	            text.setSelection(formattedText.length());

	            ignoreModifyEvent = false;
	        }
	    });
	}
	
	private void addCEPFormatter(final Text text) {
	    text.addModifyListener(new ModifyListener() {
	        @Override
	        public void modifyText(ModifyEvent e) {
	            if (ignoreModifyEventCEP) {
	                return;
	            }

	            ignoreModifyEventCEP = true;

	            String currentText = text.getText().replaceAll("[^0-9]", "");
	            StringBuilder formattedText = new StringBuilder();
	            int length = currentText.length();

	            if (length > 8) {
	                currentText = currentText.substring(0, 8);
	                length = 8;
	            }

	            for (int i = 0; i < length; i++) {
	                if (i == 2) {
	                    formattedText.append(".");
	                } else if (i == 5) {
	                    formattedText.append("-");
	                }
	                formattedText.append(currentText.charAt(i));
	            }

	            text.setText(formattedText.toString());
	            text.setSelection(formattedText.length());

	            ignoreModifyEventCEP = false;
	        }
	    });
	}
	
	private void addRendaFormatter(final Text text) {
	    text.addModifyListener(new ModifyListener() {
	        @Override
	        public void modifyText(ModifyEvent e) {
	            if (ignoreModifyEventRenda) {
	                return;
	            }

	            ignoreModifyEventRenda = true;

	            String currentText = text.getText().replaceAll("[^0-9]", "");
	            StringBuilder formattedText = new StringBuilder();
	            int length = currentText.length();

	            for (int i = 0; i < length; i++) {
	                if (i == length - 2) {
	                    formattedText.append(".");
	                }
	                formattedText.append(currentText.charAt(i));
	            }

	            text.setText(formattedText.toString());
	            text.setSelection(formattedText.length());

	            ignoreModifyEventRenda = false;
	        }
	    });

	 
	    text.addVerifyListener(new VerifyListener() {
	        @Override
	        public void verifyText(VerifyEvent e) {
	            String newText = text.getText().substring(0, e.start) + e.text + text.getText(e.end, text.getText().length() - 1);
	            if (!newText.matches("\\d*\\.?\\d*")) {
	                e.doit = false;
	            }
	        }
	    });
	}
	
	private void addDataFormatter(final Text text) {
        text.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                if (ignoreModifyEventData) {
                    return;
                }

                ignoreModifyEventData = true;

                String currentText = text.getText().replaceAll("[^0-9]", "");
                StringBuilder formattedText = new StringBuilder();
                int length = currentText.length();

                if (length > 8) {
                    currentText = currentText.substring(0, 8);
                    length = 8;
                }

                for (int i = 0; i < length; i++) {
                    if (i == 2 || i == 4) {
                        formattedText.append("/");
                    }
                    formattedText.append(currentText.charAt(i));
                }


                text.setText(formattedText.toString());
                text.setSelection(formattedText.length());
                
               

                ignoreModifyEventData = false;
            }
        });
    }
	
	private void addNumeroFormatter(final Text text) {
        text.addVerifyListener(new VerifyListener() {
            @Override
            public void verifyText(VerifyEvent e) {
                String newText = e.text;
                String currentText = text.getText();
                String modifiedText = currentText.substring(0, e.start) + newText + currentText.substring(e.end);

                if (!modifiedText.matches("^\\d{0,7}$")) {
                    e.doit = false;
                }
            }
        });
    }
	
	
	
}