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
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.event.ValueChangeEvent;

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
    Usuario usuarioCorreo;
    @EJB
    PaisFacade paisFacade;
    Pais pais = new Pais();
    private List<Pais> listaPaises;
    @EJB
    CiudadFacade ciudadFacade;
    Ciudad ciudad = new Ciudad();
    private List<Ciudad> listaCiudades;

    private Part file;
    private String paisNombre;
    private String ciudadNombre;
    private boolean existe;
    FacesContext context = FacesContext.getCurrentInstance();

    Mailer mailer = new Mailer();

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getPaisNombre() {
        return paisNombre;
    }

    public void setPaisNombre(String paisNombre) {
        this.paisNombre = paisNombre;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
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

    public Usuario getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(Usuario usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
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

    public String crearUsuario() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        usuario.setId(1);
        usuario.setRolidRol(rolFacade.find(rol.getIdRol()));
        usuario.setPaisIdpais(paisFacade.find(pais.getIdpais()));
        usuario.setCiudadIdciudad(ciudadFacade.find(ciudad.getIdciudad()));
        MessageDigest m = MessageDigest.getInstance("MD5");
        String password = UsuarioControlador.randomAlphaNumeric(10);
        m.reset();
        m.update(password.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        usuario.setPassword(hashtext);
        String mensaje = "Ha sido registrado en FlowersX exitosamente.<br>Sus credenciales de acceso son:<br>Correo: " + usuario.getEmail() + "<br>Password: " + password;
        usuarioFacade.create(usuario);
        mailer.configurar();
        mailer.enviarMensaje(usuario.getEmail(), "Registro en FlowersX - Santa Marta Flowers S.A.S.", mensaje);
        usuario = new Usuario();
        return "gestionar-usuarios";
    }

    public String cambiarPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        usuario.setId(1);
        usuario.setTitular(usuarioLogueado.getTitular());
        usuario.setRazonSocial(usuarioLogueado.getRazonSocial());
        usuario.setEmail(usuarioLogueado.getEmail());
        usuario.setEstado(1);
        usuario.setRolidRol(usuarioLogueado.getRolidRol());
        usuario.setPaisIdpais(usuarioLogueado.getPaisIdpais());
        usuario.setCiudadIdciudad(usuarioLogueado.getCiudadIdciudad());
        MessageDigest m = MessageDigest.getInstance("MD5");
        String password = usuario.getPassword();
        m.reset();
        m.update(password.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        usuario.setPassword(hashtext);
        usuarioFacade.edit(usuario);
        String mensaje = "Has cambiado tu password en FlowersX exitosamente.<br>Tu nueva password es: " + password;
        usuarioFacade.create(usuario);
        mailer.configurar();
        mailer.enviarMensaje(usuario.getEmail(), "Cambio de password en FlowersX - Santa Marta Flowers S.A.S.", mensaje);
        usuario = new Usuario();
        return "main-usuario";
    }

    public void nuevoPassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        usuarioCorreo = usuarioFacade.buscarPorCorreo(usuario.getEmail());
        if (usuarioCorreo != null) {
            MessageDigest m = MessageDigest.getInstance("MD5");
            String password = UsuarioControlador.randomAlphaNumeric(10);
            m.reset();
            m.update(password.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            usuarioCorreo.setPassword(hashtext);
            String mensaje = "<h1>Has solicitado una nueva password</h1><br><p>¡Gracias por contactarnos, " + usuarioCorreo.getTitular() + "!</p><p>Tu nueva password es: " + password + "</p>";
            usuarioFacade.edit(usuarioCorreo);
            mailer.configurar();
            mailer.enviarMensaje(usuario.getEmail(), "Nueva password en FlowersX - Santa Marta Flowers S.A.S.", mensaje);
            if (mailer.isFueEnviado()) {
                context.addMessage(null, new FacesMessage("¡Hecho!",  "Tu nueva password ha sido enviada.") );
            } else {
                context.addMessage(null, new FacesMessage("¡Error!",  "No hemos podido procesar tu solicitud.") );
            }
            usuario = new Usuario();
        } else {
            context.addMessage(null, new FacesMessage("¡Error!", "El usuario no está registrado."));
        }
    }

    public String preEditarUsuario(Usuario usuario) {
        this.usuario = usuario;
        return "editar-usuario";
    }
    
    public String preEditarPassword(Usuario usuario) {
        this.usuario = usuario;
        return "editar-password";
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
            usuarioLogueado = usuarioFacade.find(usuarioLogueado.getId());
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

    /* public void ciudadListener(ValueChangeEvent valueChangeEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        String newPais = (String) valueChangeEvent.getNewValue();
        UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("crearUsuario:ciudad");
        for (Pais pais : listaPaises) {
            if (pais.getNombre().equals(newPais)) {
                listaCiudades = ciudadFacade.consultarCiudades(pais);
            }
        }
        ciudadInputText.setValue(listaCiudades);
        facesContext.renderResponse();
    } */
}
