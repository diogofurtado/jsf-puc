package br.com.dextra.livraria.bean;

import javax.faces.bean.ManagedBean;

import br.com.dextra.livraria.dao.DAO;
import br.com.dextra.livraria.modelo.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		// Limpando a tela
		this.autor = new Autor();
		
		return null;
	}
}
