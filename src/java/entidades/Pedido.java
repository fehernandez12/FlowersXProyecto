/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido")
    , @NamedQuery(name = "Pedido.findByFechaDeCreacion", query = "SELECT p FROM Pedido p WHERE p.fechaDeCreacion = :fechaDeCreacion")
    , @NamedQuery(name = "Pedido.findByFechaDeEntrega", query = "SELECT p FROM Pedido p WHERE p.fechaDeEntrega = :fechaDeEntrega")
    , @NamedQuery(name = "Pedido.findByCantidadProducto", query = "SELECT p FROM Pedido p WHERE p.cantidadProducto = :cantidadProducto")
    , @NamedQuery(name = "Pedido.findBySubTotal", query = "SELECT p FROM Pedido p WHERE p.subTotal = :subTotal")
    , @NamedQuery(name = "Pedido.findByTotal", query = "SELECT p FROM Pedido p WHERE p.total = :total")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fechaDeCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaDeCreacion;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fechaDeEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaDeEntrega;
    @Column(name = "cantidadProducto")
    private Integer cantidadProducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "subTotal")
    private Double subTotal;
    @Column(name = "total")
    private Double total;
    @JoinTable(name = "producto_has_pedido", joinColumns = {
        @JoinColumn(name = "pedido_idPedido", referencedColumnName = "idPedido")}, inverseJoinColumns = {
        @JoinColumn(name = "producto_idProducto", referencedColumnName = "idProducto")})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Producto> productoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoidPedido", fetch = FetchType.EAGER)
    private List<Ordenproduccion> ordenproduccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoidPedido", fetch = FetchType.EAGER)
    private List<Novedad> novedadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoidPedido", fetch = FetchType.EAGER)
    private List<Solicitud> solicitudList;
    @JoinColumn(name = "Usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuarioid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoidPedido", fetch = FetchType.EAGER)
    private List<Pago> pagoList;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(Integer idPedido, Date fechaDeCreacion, Date fechaDeEntrega) {
        this.idPedido = idPedido;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Date getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(Date fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @XmlTransient
    public List<Ordenproduccion> getOrdenproduccionList() {
        return ordenproduccionList;
    }

    public void setOrdenproduccionList(List<Ordenproduccion> ordenproduccionList) {
        this.ordenproduccionList = ordenproduccionList;
    }

    @XmlTransient
    public List<Novedad> getNovedadList() {
        return novedadList;
    }

    public void setNovedadList(List<Novedad> novedadList) {
        this.novedadList = novedadList;
    }

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    public Usuario getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Usuario usuarioid) {
        this.usuarioid = usuarioid;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pedido[ idPedido=" + idPedido + " ]";
    }
    
}
