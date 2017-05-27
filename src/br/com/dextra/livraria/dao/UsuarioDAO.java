package br.com.dextra.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.dextra.livraria.modelo.Usuario;

public class UsuarioDAO {

	EntityManager em = new JPAUtil().getEntityManager();

	public Usuario getUsuario(String nomeUsuario, String senha) {

		
		try {
			Usuario usuario = (Usuario) em
					.createQuery(
							"SELECT u from Usuario u where u.nome = :name and u.password = :senha")
					.setParameter("name", nomeUsuario)
					.setParameter("senha", senha).getSingleResult();

			System.out.println(usuario.getNome() + " -- " + usuario.getPassword());	
			
			return usuario;
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean inserirUsuario(Usuario usuario) {
		try {
			em.persist(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletarUsuario(Usuario usuario) {
		try {
			em.remove(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
