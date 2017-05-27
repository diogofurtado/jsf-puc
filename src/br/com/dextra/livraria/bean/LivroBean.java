package br.com.dextra.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.dextra.livraria.dao.DAO;
import br.com.dextra.livraria.modelo.Autor;
import br.com.dextra.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private Integer autorId;

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}
	
	public List<Autor> getAutores(){
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public String gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		return null;
	}
	
	
	public String gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			//throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
		}

		new DAO<Livro>(Livro.class).adiciona(this.livro);
		// limpando a tela
		this.livro = new Livro(); 
		return null;
	}
	
	// Vamos criar um validator que verifica se o valor do campo ISBN esta correto
	// comeca com 1
	public void validaISBN(FacesContext fc,UIComponent component, Object value) 
			throws ValidatorException, Exception {
		
		String valor = value.toString();
		
		if (!valor.startsWith("1")) {
			throw new ValidatorException(
					new FacesMessage("Atenção! Codigo ISBN deve começar com 1!"));
		}
	}
	
	public String formAutor() {
        System.out.println("Chamanda o formulario do Autor");
        return "autor?faces-redirect=true";
    }


}
