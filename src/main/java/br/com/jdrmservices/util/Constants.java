package br.com.jdrmservices.util;

public class Constants {
	public static final String FORMAT_DATE = "dd/MM/yyyy";
	public static final String FORMAT_TIME = "HH:mm:ss";

	public final static String INFORMACOES_SALVAS_SUCESSO = "Informações salvas com sucesso!";
	public final static String INFORMACOES_JA_CADASTRADAS = "Informações já cadastradas!";
	public final static String INFORMACOES_NAO_CADASTRADO = "Informações não cadastrado!";
	public final static String ERRO_SALVAR_SUCESSO = "Erro ao salvar as informações!";
	
	public final static String INFORMACOES_EXCLUIDAS_SUCESSO = "Informações excluídas com sucesso!";
	public final static String ERRO_EXCLUIR_INFORMACOES = "Erro ao excluir as informações!";
	
	public static final String USUARIO_SENHA_OBRIGATORIA = "A Senha é obrigatória";
	public static final String USUARIO_SENHA_INCORRETOS = "E-mail e/ou Senha estão incorretos!";
	public static final String SENHAS_NAO_CONFEREM = "Senha não conferem!";
	
	public final static String VIEW_LOGIN = "Login";
	public final static String VIEW_INDEX = "Index";
	public final static String VIEW_DASHBOARD = "dashboard/Dashboard";
	
	public final static String VIEW_PESQUISAR_EMPRESA = "empresa/PesquisarEmpresa";
	public final static String VIEW_EMPRESA_NOVO = "empresa/CadastrarEmpresa";
	public final static String VIEW_EMPRESA_REDIRECT = "redirect:/empresas/novo";
	
	public final static String VIEW_PESQUISAR_SECRETARIA = "secretaria/PesquisarSecretaria";
	public final static String VIEW_SECRETARIA_NOVO = "secretaria/CadastrarSecretaria";
	public final static String VIEW_SECRETARIA_REDIRECT = "redirect:/secretarias/novo";
	
	public final static String VIEW_PESQUISAR_FORNECEDOR = "fornecedor/PesquisarFornecedor";
	public final static String VIEW_FORNECEDOR_NOVO = "fornecedor/CadastrarFornecedor";
	public final static String VIEW_FORNECEDOR_REDIRECT = "redirect:/fornecedores/novo";
	
	public final static String VIEW_PESQUISAR_CONTRATO = "contrato/PesquisarContrato";
	public final static String VIEW_CONTRATO_NOVO = "contrato/CadastrarContrato";
	public final static String VIEW_CONTRATO_REDIRECT = "redirect:/contratos/novo";
	
	public final static String VIEW_ITEM_CONTRATO = "componentes/TabelaItensContrato";
	
	public final static String VIEW_PESQUISAR_COMPRA_DIRETA = "compradireta/PesquisarCompraDireta";
	public final static String VIEW_COMPRA_DIRETA_NOVO = "compradireta/CadastrarCompraDireta";
	public final static String VIEW_COMPRA_DIRETA_REDIRECT = "redirect:/comprasdiretas/novo";
	
	public final static String VIEW_ITEM_COMPRA_DIRETA = "componentes/TabelaItensCompraDireta";
	
	public final static String VIEW_PESQUISAR_EXECUCAO_CONTRATO = "execucaocontrato/PesquisarExecucaoContrato";
	public final static String VIEW_EXECUCAO_CONTRATO_NOVO = "execucaocontrato/CadastrarExecucaoContrato";
	public final static String VIEW_EXECUCAO_CONTRATO_REDIRECT = "redirect:/execucaocontratos/novo";
	
	public final static String VIEW_PESQUISAR_USUARIO = "usuario/PesquisarUsuario";
	public final static String VIEW_USUARIO_NOVO = "usuario/CadastrarUsuario";
	public final static String VIEW_USUARIO_REDIRECT = "redirect:/usuarios/novo";
	
	public final static String VIEW_RELATORIOS = "relatorios/EmitirRelatorio";
	
	// files messages
	public final static String VIEW_UPLOAD_FILE = "upload/UploadFile";
	public final static String ERROR_SAVE_TEMP_FILE = "Error to save temporary file";
	public final static String ERROR_RECOVERY_TEMP_FILE = "Error to recovery temporary file";
	public final static String ERROR_RECOVERY_FILE = "Error to recovery file";
	public final static String ERROR_MOVE_FILE_DEFINITIVE_LOCAL = "Error to move temporary file for definitive local";
	public final static String CREATE_DIRECTORY_SUCCESS = "Create directory success!";
	public final static String DEFAULT_DIRECTORY = "Default directory";
	public final static String TEMP_DIRECTORY = "Temporary directory";
	public final static String ERROR_CREATE_DIRECTORY_SAVE_FILE = "Error to create directory for saving file";
}
