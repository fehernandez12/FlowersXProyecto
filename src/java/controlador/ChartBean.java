/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author ACER
 */
import entidades.Producto;
import facade.ProductoFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@RequestScoped
public class ChartBean implements Serializable {

    @EJB
    ProductoFacade productoFacade;
    private Producto producto = new Producto();
    
    private CartesianChartModel categoryModel;
    
    
    
    public ChartBean() {
        createCategoryModel();
    }

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    private void createCategoryModel() {
        categoryModel = new CartesianChartModel();
        producto = new Producto();
        
        ChartSeries boys = new ChartSeries();
        
        boys.setLabel("Boys");
        boys.set("2018", 4000);
        boys.set("2019", 4000);
        boys.set("2014", 4000);
        
        ChartSeries girls = new ChartSeries();
        
        girls.setLabel("Red");
        girls.set("2017", 5000);
        girls.set("2012", 4000);
        girls.set("2013", 4000);
        
        categoryModel.addSeries(boys);
        categoryModel.addSeries(girls);
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
