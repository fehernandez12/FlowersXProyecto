/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Novedad;
import entidades.Pago;
import entidades.Pedido;
import facade.NovedadFacade;
import facade.PagoFacade;
import facade.PedidoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "pagoControlador")
@SessionScoped
public class PagoControlador implements Serializable {

    /**
     * Creates a new instance of PagoControlador
     */
   @EJB
    PagoFacade pagoFacade;
    Pago pago = new Pago();
    @EJB
    PedidoFacade pedidoFacade;
    Pedido pedido = new Pedido();
    @EJB
    NovedadFacade novedadFacade;
    Novedad novedad = new Novedad();
    public PagoControlador() {
    }
     public Pago getPago(){
        return pago;
    }
    
    public void setpago(Pago pago){
        this.pedido = pedido;
    }
    public Novedad getNovedad(){
        return novedad;
    }
    
    public void setNovedad(Novedad novedad){
        this.novedad = novedad;
    }
    public Pedido getPedido(){
        return pedido;
    }
    
    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }
    
    public List<Pago> consultarPago() {
        return pagoFacade.findAll();
    }
    
    public String crearPago() {
        
        pago.setPedidoidPedido(pedidoFacade.find(pedido.getIdPedido()));
       pago.setNovedadidNovedad(novedadFacade.find(novedad.getIdNovedad()));
        pagoFacade.create(pago);
        pago = new Pago();
        return "gestionar-pago";
    }
    
     public String preEditarPago(Pago pago) {
        this.pago = pago;
        return "editar-pago";
    }

    public String editarPago() {
        pago.setPedidoidPedido(pedidoFacade.find(pedido.getIdPedido()));
        pago.setNovedadidNovedad(novedadFacade.find(novedad.getIdNovedad()));
       
        pagoFacade.edit(pago);
        pago = new Pago();
        return "gestionar-pago";
    }

   public void eliminarPago(Pago pago) {
        pagoFacade.remove(pago);
        //return "Lista";
    }
}

