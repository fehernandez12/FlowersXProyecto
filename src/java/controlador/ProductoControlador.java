/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Pedido;
import entidades.Producto;
import facade.PedidoFacade;
import facade.ProductoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "productoControlador")
@SessionScoped
public class ProductoControlador implements Serializable {

    /**
     * Creates a new instance of ProductoControlador
     */
    public ProductoControlador() {
    }
    
    @EJB
    ProductoFacade productoFacade;
    Producto producto = new Producto();
    List<Producto> listaProductos;
    
    @EJB
    PedidoFacade pedidoFacade;
    Pedido pedido = new Pedido();
    List<Pedido> listaPedidos;

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

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
    
    public String crearProducto() {
        productoFacade.create(producto);
        producto = new Producto();
        return "gestionar-catalogo";
    }
    
    public String editarProducto() {
        productoFacade.edit(producto);
        producto = new Producto();
        return "gestionar-catalogo";
    }
    
    public String preEditarProducto(Producto producto) {
        this.producto = producto;
        return "editar-catalogo";
    }
    
    public void eliminarPedido(Pedido pedido) {
        pedidoFacade.remove(pedido);
        //return "Lista";
    }
    
    public List<Producto> listar() {
        return productoFacade.findAll();
    }
}
