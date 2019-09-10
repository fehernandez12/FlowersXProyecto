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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p")
    , @NamedQuery(name = "Pago.findByIdPago", query = "SELECT p FROM Pago p WHERE p.idPago = :idPago")
    , @NamedQuery(name = "Pago.findByNumero", query = "SELECT p FROM Pago p WHERE p.numero = :numero")
    , @NamedQuery(name = "Pago.findByCodigoDeSeguridad", query = "SELECT p FROM Pago p WHERE p.codigoDeSeguridad = :codigoDeSeguridad")
    , @NamedQuery(name = "Pago.findByFechaDeVencimiento", query = "SELECT p FROM Pago p WHERE p.fechaDeVencimiento = :fechaDeVencimiento")})
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPago")
    private Integer idPago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigoDeSeguridad")
    private String codigoDeSeguridad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaDeVencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDeVencimiento;
    @JoinColumn(name = "Pedido_idPedido", referencedColumnName = "idPedido")
    @ManyToOne(optional = false)
    private Pedido pedidoidPedido;

    public Pago() {
    }

    public Pago(Integer idPago) {
        this.idPago = idPago;
    }

    public Pago(Integer idPago, String numero, String codigoDeSeguridad, Date fechaDeVencimiento) {
        this.idPago = idPago;
        this.numero = numero;
        this.codigoDeSeguridad = codigoDeSeguridad;
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoDeSeguridad() {
        return codigoDeSeguridad;
    }

    public void setCodigoDeSeguridad(String codigoDeSeguridad) {
        this.codigoDeSeguridad = codigoDeSeguridad;
    }

    public Date getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(Date fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public Pedido getPedidoidPedido() {
        return pedidoidPedido;
    }

    public void setPedidoidPedido(Pedido pedidoidPedido) {
        this.pedidoidPedido = pedidoidPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPago != null ? idPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.idPago == null && other.idPago != null) || (this.idPago != null && !this.idPago.equals(other.idPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pago[ idPago=" + idPago + " ]";
    }
    
}
