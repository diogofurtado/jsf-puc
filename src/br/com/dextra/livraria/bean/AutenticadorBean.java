package br.com.dextra.livraria.bean;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
public class AutenticadorBean {

	
	
	/*
	 * Por simplicidade, utilizaremos um atributo de um managed bean para armazenar os usu�rios
	 * da aplica��o e suas respectivas senhas. A implementa��o que ser� apresentada a seguir pode ser
	 * alterada para que esses dados sejam armazenadas em um arquivo ou em um banco de dados.
	 */
	
	
	// Criando lista de usuarios autorizados
	private static Map<String,String> mapa =  new HashMap<String,String>();
	
	private String usuario;
	private String senha;
	private String tipoUsuario;
	private List<String> tipos = new ArrayList<String>();
	
	
	// Vamos acrescentar algums usuarios altorizados
	static {
		AutenticadorBean.mapa.put("dextra", "dextra");
		AutenticadorBean.mapa.put("jorge", "jorge");
		AutenticadorBean.mapa.put("aluno", "aluno");
	}
	
	
	// Criando metodo de autenticacao
	public String autentica() {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (AutenticadorBean.mapa.containsKey(this.usuario) 
			&& AutenticadorBean.mapa.get(this.usuario).equals(this.senha)) {
			
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession)ec.getSession(false);
			session.setAttribute("usuario",this.usuario);
			
			// Vamos popular algums tipos de usuarios
			this.setTipos("Administrator");
			this.setTipos("DBA");
			this.setTipos("Programmer");
			this.setTipos("Network Admin");
			this.setTipos("SO");
			
			// Setando usuario "Administrator"
			this.setTipoUsuario(this.tipos.get(0));
									
			return "/autor";
		} else {
			FacesMessage fm = new FacesMessage("usuário e/ou senha inválidos");
			fm.setSeverity ( FacesMessage . SEVERITY_ERROR );
			fc.addMessage (null , fm);
			return "/login";
		}
		
	}
	
	// Criando metodo de logout
	public String registraSaida() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		return "/login";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static Map<String, String> getMapa() {
		return mapa;
	}

	public static void setMapa(Map<String, String> mapa) {
		AutenticadorBean.mapa = mapa;
	}

	public List<String> getTipos() {
		return tipos;
	}

	public void setTipos(String string) {
		this.tipos.add(string);
	}

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}	
	
}
