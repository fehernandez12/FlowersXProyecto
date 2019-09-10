/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import entidades.Ciudad;
import entidades.Pais;
import entidades.Permiso;
import entidades.Rol;
import entidades.Usuario;
import facade.CiudadFacade;
import facade.PaisFacade;
import facade.RolFacade;
import facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Guillermo
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

    /**
     * Creates a new instance of UsuarioControlador
     */
    public UsuarioControlador() {
    }

    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario = new Usuario();
    private List<Usuario> listaUsuarios;
    @EJB
    RolFacade rolFacade;
    Rol rol = new Rol();
    Usuario usuarioLogueado;
    @EJB
    PaisFacade paisFacade;
    Pais pais = new Pais();
    @EJB
    CiudadFacade ciudadFacade;
    Ciudad ciudad = new Ciudad();
    
    private Part file;
    
    Mailer mailer = new Mailer();

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
    

    public List<Usuario> consultarUsuario() {
        return usuarioFacade.findAll();
    }

    public String crearUsuario() throws UnsupportedEncodingException {
        usuario.setRolidRol(rolFacade.find(rol.getIdRol()));
        usuario.setPaisIdpais(paisFacade.find(pais.getIdpais()));
        usuario.setCiudadIdciudad(ciudadFacade.find(ciudad.getIdciudad()));
        usuario.setPassword(SolicitudControlador.randomAlphaNumeric(10));
        String mensaje = "Ha sido registrado en FlowersX exitosamente.\nSus credenciales de acceso son:\nCorreo: " + usuario.getEmail() + "\nPassword: " + usuario.getPassword();
        usuarioFacade.create(usuario);
        mailer.configurar();
        mailer.enviarMensaje(usuario.getEmail(), "Registro en FlowersX - Santa Marta Flowers S.A.S.", mensaje);
        usuario = new Usuario();
        return "gestionar-usuarios";
    }

    public String preEditarUsuario(Usuario usuario) {
        this.usuario = usuario;
        return "editar-usuario";
    }

    public String editarUsuario() {
        usuario.setRolidRol(rolFacade.find(rol.getIdRol()));
        usuarioFacade.edit(usuario);
        usuario = new Usuario();
        return "gestionar-usuarios";
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarioFacade.remove(usuario);
        //return "Lista";
    }

    public String validarLogin() {
        String redireccionar = "";
        try {
            usuarioLogueado = usuarioFacade.login(usuario);
            if (usuarioLogueado != null) {
                rol = usuarioLogueado.getRolidRol();
                for (/*  */Permiso permiso : usuarioLogueado.getRolidRol().getPermisoList()) {
                    System.out.println("Permisos: " + permiso.getNombre());
                }
                /* usuarioLogueado.getRolidRol().getPermisoList().forEach((permiso) -> {
                    System.out.println("Permisos: " + permiso.getNombre());
                }); */
                System.out.println("Usuario Logueado: " + usuarioLogueado.getTitular());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesionLogin", usuarioLogueado);
                redireccionar = "menu.xhtml";
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return redireccionar;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaSolicitudes(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
