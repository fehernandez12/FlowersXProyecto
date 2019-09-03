/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Producto;
import facade.ProductoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Aprendiz
 */
@Named(value = "graficosControlador")
@SessionScoped
public class GraficosControlador implements Serializable {

    /**
     * Creates a new instance of GraficosControlador
     */
    public GraficosControlador() {
    }
    
    BarChartModel graficoBarra;
    
    @EJB
    ProductoFacade productoFacade;
    Producto producto = new Producto();
    
    /* @PostConstruct
    public void init() {
        graficarBarra();
    } */

    public BarChartModel getGraficoBarra() {
        return graficoBarra;
    }

    public void setGraficoBarra(BarChartModel graficoBarra) {
        this.graficoBarra = graficoBarra;
    }

    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
    public void graficarBarra() {
        List<Producto> listaProductos = productoFacade.findAll();
        graficoBarra = new BarChartModel();
        ChartSeries serieProducto = new ChartSeries();
        serieProducto.setLabel("Productos");
        for (Producto producto1 : listaProductos) {
            serieProducto.set(producto1.getNombreProducto(), producto1.getExistencias());
        }
        graficoBarra.addSeries(serieProducto);
        graficoBarra.setTitle("Existencias de producto");
    }
    
}
