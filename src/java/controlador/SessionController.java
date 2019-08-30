/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Aprendiz
 */
@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {
    
    private Locale selectedLanguage = new Locale("es");

    /**
     * Creates a new instance of SessionController
     */
    public SessionController() {
    }
    
    @PostConstruct
    public void init () {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        Locale idiomaUsuario = ec.getRequestLocale();
        boolean support = false;
        for (Locale l : getSupportLanguages()) {
            if(l.getLanguage().equals(idiomaUsuario.getLanguage())){
                support = true; break;
            }
        }
        selectedLanguage = (support) ? idiomaUsuario: new Locale("es");
    }
    
    public Locale getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(Locale selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }
    
    public List<Locale> getSupportLanguages(){
        List<Locale> idiomas = new ArrayList<>();
        Iterator<Locale> it = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
        while(it.hasNext()){
            idiomas.add(it.next());
        }
        return idiomas;
    }
    
    public String cambiarIdioma(Locale idioma){
        if(idioma != null){
            this.selectedLanguage = idioma;
            FacesContext.getCurrentInstance().getViewRoot().setLocale(selectedLanguage);
        }
        return "";
    }
    
}
