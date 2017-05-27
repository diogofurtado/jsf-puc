package br.com.dextra.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.dextra.livraria.dao.UsuarioDAO;
import br.com.dextra.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBeanDB {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuario = new Usuario();
	FacesContext fc = FacesContext.getCurrentInstance();
	
	public String envia() {

		
		usuario = usuarioDAO.getUsuario(usuario.getNome(), usuario.getPassword());
		if (usuario == null) {
			
			usuario = new Usuario();			
			FacesMessage fm = new FacesMessage("usuário e/ou senha inválidos");
			fm.setSeverity ( FacesMessage . SEVERITY_ERROR );
			fc.addMessage (null , fm);
			
			return null;
			
		} else {
			
			System.out.println("Nao ta nulo!!");
			return "autor?faces-redirect=true";
		}


	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}


