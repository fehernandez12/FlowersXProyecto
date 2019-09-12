/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Ordenproduccion;
import entidades.Pedido;
import entidades.Usuario;
import facade.OrdenproduccionFacade;
import facade.PedidoFacade;
import facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author ACER
 */
@Named(value = "ordenProduccionControlador")
@SessionScoped
public class OrdenProduccionControlador implements Serializable {

    /**
     * Creates a new instance of OrdenProduccion
     */
    public OrdenProduccionControlador() {
    }

    @EJB
    OrdenproduccionFacade ordenproduccionFacade;
    Ordenproduccion ordenproduccion = new Ordenproduccion();
    private List<Ordenproduccion> listaOrdenProduccion;

    @EJB
    PedidoFacade pedidoFacade;
    Pedido pedido = new Pedido();
    List<Pedido> listaPedido;

    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario = new Usuario();
    List<Usuario> listaUsuarios;

    public Ordenproduccion getOrdenproduccion() {
        return ordenproduccion;
    }

    public void setOrdenproduccion(Ordenproduccion ordenproduccion) {
        this.ordenproduccion = ordenproduccion;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Ordenproduccion> getListaOrdenProduccion() {
        return listaOrdenProduccion;
    }

    public void setListaOrdenProduccion(List<Ordenproduccion> listaOrdenProduccion) {
        this.listaOrdenProduccion = listaOrdenProduccion;
    }
    public List<Ordenproduccion> consultarOrdenproduccion() {
        return ordenproduccionFacade.findAll();
    }

     public String crearOrdenProduccion() {
        ordenproduccion.setUsuarioid(usuarioFacade.find(usuario.getId()));
        ordenproduccion.setPedidoidPedido(pedidoFacade.find(pedido.getIdPedido()));
        ordenproduccionFacade.create(ordenproduccion);
        ordenproduccion = new Ordenproduccion();
        return "gestionar-ordenProduccion";
    }
    public String preEditarOrdenProduccion(Ordenproduccion ordenproduccion) {
        this.ordenproduccion = ordenproduccion;
        return "editar-ordenProduccion";
    }

    public String editarOrdenProduccion() {
        ordenproduccion.setUsuarioid(usuarioFacade.find(usuario.getId()));
        ordenproduccion.setPedidoidPedido(pedidoFacade.find(pedido.getIdPedido()));
        ordenproduccionFacade.edit(ordenproduccion);
        ordenproduccion = new Ordenproduccion();
        return "gestionar-ordenProduccion";
    }

    public void eliminarOrdenProduccion(Ordenproduccion ordenproduccion) {
        ordenproduccionFacade.remove(ordenproduccion);
        //return "Lista";
    }
    
    
}
