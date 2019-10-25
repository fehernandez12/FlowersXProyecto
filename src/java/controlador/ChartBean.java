package controlador;

import entidades.Producto;
import facade.ProductoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author ACER
 */
@Named(value = "chartBean")
@ViewScoped
public class ChartBean implements Serializable {

    @EJB
    private ProductoFacade productoFacade;
    private List<Producto> listaProducto;
    private BarChartModel barra;

    public ChartBean() {
    }

    public void Listar() {
        listaProducto = productoFacade.findAll();
        /*Poder hacer el grafico*/
        graficar();
    }

    public void graficar() {

        barra = new BarChartModel();
        /*Conecion a Base de datos */
        
        for (int i = 0; i < productoFacade.listar().size(); i++) {
            ChartSeries serie = new BarChartSeries();
            serie.setLabel(productoFacade.listar().get(i).getNombreProducto());
            serie.set(productoFacade.listar().get(i).getNombreProducto(), productoFacade.listar().get(i).getExistencias());
            barra.addSeries(serie);
        }
        
 /*
        for (Producto producto : listaProducto) {
            ChartSeries serie = new ChartSeries();
            if (producto.getExistencias() > 0) {
                serie.setLabel(producto.getNombreProducto());
                serie.set(producto.getNombreProducto(), producto.getExistencias());
                barra.addSeries(serie);
            }
        }
*/
        barra.setTitle("Existencias");
        barra.setLegendPosition("ne");
        barra.setAnimate(true);
        

        /*Eje x se nombres de los ejes*/
        Axis xAxis = barra.getAxis(AxisType.X);
        xAxis.setLabel("Productos");

        Axis yAxis = barra.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad de existencias");
        yAxis.setMin(0);
        yAxis.setMax(90000);
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    

    public BarChartModel getBarra() {
        return barra;
    }

    public void setBarra(BarChartModel barra) {
        this.barra = barra;
    }

}
