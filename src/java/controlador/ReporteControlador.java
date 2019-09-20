/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Reporte;
import entidades.Usuario;
import facade.UsuarioFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Aprendiz
 */
@Named(value = "reporteControlador")
@SessionScoped
public class ReporteControlador implements Serializable {

    @EJB
    UsuarioFacade usuarioFacade;
    private List<Usuario> listaUsuario;
    private String razonSocial;
    
    JasperPrint jasperPrint;

    /**
     * Creates a new instance of ReporteControlador
     */
    public ReporteControlador() {
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void verReporte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

        //Instancia hacia la clase reporteClientes        
        Reporte reporte = new Reporte();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/Admin/reportes/Reporte-Usuario.jasper");
        reporte.getReporte(ruta, this.razonSocial);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaUsuario);
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//reportes//");
        jasperPrint = JasperFillManager.fillReport(ruta + "\\Admin\\reportes\\Reporte-Usuario.jasper", new HashMap(), beanCollectionDataSource);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();

    }
}
