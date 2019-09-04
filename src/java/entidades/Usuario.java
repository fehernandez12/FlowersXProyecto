/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aprendiz
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByTitular", query = "SELECT u FROM Usuario u WHERE u.titular = :titular")
    , @NamedQuery(name = "Usuario.findByRazonSocial", query = "SELECT u FROM Usuario u WHERE u.razonSocial = :razonSocial")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password")
    , @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "titular")
    private String titular;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "razonSocial")
    private String razonSocial;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    //@NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "estado")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioid", fetch = FetchType.EAGER)
    private List<Ordenproduccion> ordenproduccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioid", fetch = FetchType.EAGER)
    private List<Novedad> novedadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioid", fetch = FetchType.EAGER)
    private List<Solicitud> solicitudList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioid", fetch = FetchType.EAGER)
    private List<Pedido> pedidoList;
    @JoinColumn(name = "Rol_idRol", referencedColumnName = "idRol")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rol rolidRol;
    @JoinColumn(name = "ciudad_idciudad", referencedColumnName = "idciudad")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ciudad ciudadIdciudad;
    @JoinColumn(name = "pais_idpais", referencedColumnName = "idpais")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pais paisIdpais;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String titular, String razonSocial, String email, String password, int estado) {
        this.id = id;
        this.titular = titular;
        this.razonSocial = razonSocial;
        this.email = email;
        this.password = password;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    public Rol getRolidRol() {
        return rolidRol;
    }

    public void setRolidRol(Rol rolidRol) {
        this.rolidRol = rolidRol;
    }

    public Ciudad getCiudadIdciudad() {
        return ciudadIdciudad;
    }

    public void setCiudadIdciudad(Ciudad ciudadIdciudad) {
        this.ciudadIdciudad = ciudadIdciudad;
    }

    public Pais getPaisIdpais() {
        return paisIdpais;
    }

    public void setPaisIdpais(Pais paisIdpais) {
        this.paisIdpais = paisIdpais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ id=" + id + " ]";
    }
    
}
