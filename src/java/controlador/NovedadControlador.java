package controlador;

import entidades.Novedad;
import entidades.Pedido;
import entidades.Usuario;
import facade.NovedadFacade;
import facade.PedidoFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "novedadControlador")
@SessionScoped
public class NovedadControlador implements Serializable {

    public NovedadControlador() {
    }
    @EJB
    NovedadFacade novedadFacade;
    Novedad novedad = new Novedad();
    private List<Novedad> listaNovedades;
    @EJB
    PedidoFacade pedidoFacade;
    Pedido pedido = new Pedido();
    @EJB
    UsuarioFacade usuarioFacade;
    Usuario usuario = new Usuario();

    public Novedad getNovedad() {
        return novedad;
    }

    public void setNovedad(Novedad novedad) {
        this.novedad = novedad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Novedad> consultarNovedad() {
        return novedadFacade.findAll();
    }

    public String crearNovedad() {
        novedad.setPedidoidPedido(pedidoFacade.find(pedido.getIdPedido()));
        novedad.setUsuarioid(usuarioFacade.find(usuario.getId()));
        novedadFacade.create(novedad);
        novedad = new Novedad();
        return "gestionar-novedad";
    }

    public String preEditarNovedad(Novedad novedad) {
        this.novedad = novedad;
        return "editar-novedad.xhtml";
    }

    public void editarNovedad() {
        novedadFacade.edit(novedad);
        novedad = new Novedad();
    }

    public void eliminarNovedad(Novedad novedad) {
        novedadFacade.remove(novedad);
    }

    public List<Novedad> getListaNovedades() {
        return listaNovedades;
    }

    public void setListaNovedades(List<Novedad> listaNovedades) {
        this.listaNovedades = listaNovedades;
    }

}
