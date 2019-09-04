/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import facade.PedidoFacade;
import facade.ProductoFacade;
import facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "pedidoControlador")
@SessionScoped
public class PedidoControlador implements Serializable {

    /**
     * Creates a new instance of PedidoControlador
     */
    public PedidoControlador() {
    }
    @EJB
    PedidoFacade pedidoFacade;
    Pedido pedido = new Pedido();
    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario = new Usuario();
    List<Usuario> listaUsuarios;
    @EJB
    ProductoFacade productoFacade;
    Producto producto = new Producto();
    List<Producto> listaProductos = new ArrayList();

    public PedidoFacade getPedidoFacade() {
        return pedidoFacade;
    }

    public void setPedidoFacade(PedidoFacade pedidoFacade) {
        this.pedidoFacade = pedidoFacade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public ProductoFacade getProductoFacade() {
        return productoFacade;
    }

    public void setProductoFacade(ProductoFacade productoFacade) {
        this.productoFacade = productoFacade;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    public List<Pedido> listar() {
        return pedidoFacade.findAll();
    }
    
    public void agregarAlCarrito(Producto p) {
        listaProductos.add(p);
    }
    
    public String crearPedido() {
        pedido.setUsuarioid(usuarioFacade.find(usuario.getId()));
        pedidoFacade.create(pedido);
        pedido = new Pedido();
        return "gestionar-pedido";
    }

    public String preEditarPedido(Pedido pedido) {
        this.pedido = pedido;
        return "editar-pedido";
    }

    public String editarPedido() {
        pedido.setUsuarioid(usuarioFacade.find(usuario.getId()));
        pedidoFacade.edit(pedido);
        pedido = new Pedido();
        return "gestionar-pedido";
    }

    public void eliminarPedido(Pedido pedido) {
        pedidoFacade.remove(pedido);
        //return "Lista";
    }
    
}
