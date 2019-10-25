/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Producto;
import java.util.ArrayList;
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
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "FlowersXPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    public List<Producto> listar() {
        Query q = em.createNativeQuery("SELECT idProducto,nombreProducto,nombreIngles,existencias FROM producto", Producto.class);
        List<Producto> lst = q.getResultList();
        return lst;
    }
    
    public List<Object[]> masVendidos() {
        Query q = em.createNativeQuery("select producto.idProducto as IdProducto, producto.nombreProducto as NombreProducto, COUNT(*) as Cantidad, producto.existencias as Existencias, producto_has_pedido.producto_idProducto as ProductoIdProducto FROM producto INNER JOIN producto_has_pedido ON producto.idProducto = producto_has_pedido.producto_idProducto GROUP BY producto_has_pedido.producto_idProducto ORDER BY cantidad DESC, NombreProducto ASC LIMIT 5");
        List<Object[]> listaObjetos = q.getResultList();
        return listaObjetos;
    }

}
