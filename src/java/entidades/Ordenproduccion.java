/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "ordenproduccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordenproduccion.findAll", query = "SELECT o FROM Ordenproduccion o")
    , @NamedQuery(name = "Ordenproduccion.findByIdOrdenProduccion", query = "SELECT o FROM Ordenproduccion o WHERE o.idOrdenProduccion = :idOrdenProduccion")
    , @NamedQuery(name = "Ordenproduccion.findByFechainicio", query = "SELECT o FROM Ordenproduccion o WHERE o.fechainicio = :fechainicio")
    , @NamedQuery(name = "Ordenproduccion.findByFechaFin", query = "SELECT o FROM Ordenproduccion o WHERE o.fechaFin = :fechaFin")})
public class Ordenproduccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrdenProduccion")
    private Integer idOrdenProduccion;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "Pedido_idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pedido pedidoidPedido;
    @JoinColumn(name = "Usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuarioid;

    public Ordenproduccion() {
    }

    public Ordenproduccion(Integer idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public Ordenproduccion(Integer idOrdenProduccion, Date fechainicio, Date fechaFin) {
        this.idOrdenProduccion = idOrdenProduccion;
        this.fechainicio = fechainicio;
        this.fechaFin = fechaFin;
    }

    public Integer getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(Integer idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Pedido getPedidoidPedido() {
        return pedidoidPedido;
    }

    public void setPedidoidPedido(Pedido pedidoidPedido) {
        this.pedidoidPedido = pedidoidPedido;
    }

    public Usuario getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Usuario usuarioid) {
        this.usuarioid = usuarioid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrdenProduccion != null ? idOrdenProduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenproduccion)) {
            return false;
        }
        Ordenproduccion other = (Ordenproduccion) object;
        if ((this.idOrdenProduccion == null && other.idOrdenProduccion != null) || (this.idOrdenProduccion != null && !this.idOrdenProduccion.equals(other.idOrdenProduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Ordenproduccion[ idOrdenProduccion=" + idOrdenProduccion + " ]";
    }
    
}
