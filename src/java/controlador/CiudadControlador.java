/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Ciudad;
import entidades.Pais;
import facade.CiudadFacade;
import facade.PaisFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Aprendiz
 */
@Named(value = "ciudadControlador")
@SessionScoped
public class CiudadControlador implements Serializable {

    /**
     * Creates a new instance of CiudadControlador
     */
    public CiudadControlador() {
    }
    
    @EJB
    CiudadFacade ciudadFacade;
    Ciudad ciudad = new Ciudad();
    @EJB
    PaisFacade paisFacade;
    Pais pais = new Pais();
    
    public List<Ciudad> listar() {
        return ciudadFacade.findAll();
    }
    
}
