/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aprendiz
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "FlowersXPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario login (Usuario usuario) {
        Usuario usuarioLogin = null;
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.email=?1 AND u.password=?2");
            query.setParameter(1, usuario.getEmail());
            query.setParameter(2, usuario.getPassword());
            List<Usuario> list = query.getResultList();
            if (!list.isEmpty()) {
                usuarioLogin = list.get(0);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return usuarioLogin;
    }
    
}
