/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seleksipegawai;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author bLueZ
 */
@Entity
@Table(name = "pendaftar", catalog = "seleksipegawai", schema = "")
@NamedQueries({
    @NamedQuery(name = "Pendaftar.findAll", query = "SELECT p FROM Pendaftar p"),
    @NamedQuery(name = "Pendaftar.findByIdPendaftar", query = "SELECT p FROM Pendaftar p WHERE p.idPendaftar = :idPendaftar"),
    @NamedQuery(name = "Pendaftar.findByNamaPendaftar", query = "SELECT p FROM Pendaftar p WHERE p.namaPendaftar = :namaPendaftar"),
    @NamedQuery(name = "Pendaftar.findByAlamatPendaftar", query = "SELECT p FROM Pendaftar p WHERE p.alamatPendaftar = :alamatPendaftar"),
    @NamedQuery(name = "Pendaftar.findByTtlPendaftar", query = "SELECT p FROM Pendaftar p WHERE p.ttlPendaftar = :ttlPendaftar"),
    @NamedQuery(name = "Pendaftar.findByKotaAsalPendaftar", query = "SELECT p FROM Pendaftar p WHERE p.kotaAsalPendaftar = :kotaAsalPendaftar"),
    @NamedQuery(name = "Pendaftar.findByIdPosisi", query = "SELECT p FROM Pendaftar p WHERE p.idPosisi = :idPosisi"),
    @NamedQuery(name = "Pendaftar.findByNilaiWawancara", query = "SELECT p FROM Pendaftar p WHERE p.nilaiWawancara = :nilaiWawancara"),
    @NamedQuery(name = "Pendaftar.findByNilaiTesTulis", query = "SELECT p FROM Pendaftar p WHERE p.nilaiTesTulis = :nilaiTesTulis"),
    @NamedQuery(name = "Pendaftar.findByNilaiPsikotes", query = "SELECT p FROM Pendaftar p WHERE p.nilaiPsikotes = :nilaiPsikotes"),
    @NamedQuery(name = "Pendaftar.findByNilaiToefl", query = "SELECT p FROM Pendaftar p WHERE p.nilaiToefl = :nilaiToefl")})
public class Pendaftar implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPendaftar")
    private Integer idPendaftar;
    @Basic(optional = false)
    @Column(name = "namaPendaftar")
    private String namaPendaftar;
    @Basic(optional = false)
    @Column(name = "alamatPendaftar")
    private String alamatPendaftar;
    @Basic(optional = false)
    @Column(name = "ttlPendaftar")
    @Temporal(TemporalType.DATE)
    private Date ttlPendaftar;
    @Basic(optional = false)
    @Column(name = "kotaAsalPendaftar")
    private String kotaAsalPendaftar;
    @Basic(optional = false)
    @Column(name = "idPosisi")
    private int idPosisi;
    @Basic(optional = false)
    @Column(name = "nilaiWawancara")
    private float nilaiWawancara;
    @Basic(optional = false)
    @Column(name = "nilaiTesTulis")
    private float nilaiTesTulis;
    @Basic(optional = false)
    @Column(name = "nilaiPsikotes")
    private float nilaiPsikotes;
    @Basic(optional = false)
    @Column(name = "nilaiToefl")
    private int nilaiToefl;

    public Pendaftar() {
    }

    public Pendaftar(Integer idPendaftar) {
        this.idPendaftar = idPendaftar;
    }

    public Pendaftar(Integer idPendaftar, String namaPendaftar, String alamatPendaftar, Date ttlPendaftar, String kotaAsalPendaftar, int idPosisi, float nilaiWawancara, float nilaiTesTulis, float nilaiPsikotes, int nilaiToefl) {
        this.idPendaftar = idPendaftar;
        this.namaPendaftar = namaPendaftar;
        this.alamatPendaftar = alamatPendaftar;
        this.ttlPendaftar = ttlPendaftar;
        this.kotaAsalPendaftar = kotaAsalPendaftar;
        this.idPosisi = idPosisi;
        this.nilaiWawancara = nilaiWawancara;
        this.nilaiTesTulis = nilaiTesTulis;
        this.nilaiPsikotes = nilaiPsikotes;
        this.nilaiToefl = nilaiToefl;
    }

    public Integer getIdPendaftar() {
        return idPendaftar;
    }

    public void setIdPendaftar(Integer idPendaftar) {
        Integer oldIdPendaftar = this.idPendaftar;
        this.idPendaftar = idPendaftar;
        changeSupport.firePropertyChange("idPendaftar", oldIdPendaftar, idPendaftar);
    }

    public String getNamaPendaftar() {
        return namaPendaftar;
    }

    public void setNamaPendaftar(String namaPendaftar) {
        String oldNamaPendaftar = this.namaPendaftar;
        this.namaPendaftar = namaPendaftar;
        changeSupport.firePropertyChange("namaPendaftar", oldNamaPendaftar, namaPendaftar);
    }

    public String getAlamatPendaftar() {
        return alamatPendaftar;
    }

    public void setAlamatPendaftar(String alamatPendaftar) {
        String oldAlamatPendaftar = this.alamatPendaftar;
        this.alamatPendaftar = alamatPendaftar;
        changeSupport.firePropertyChange("alamatPendaftar", oldAlamatPendaftar, alamatPendaftar);
    }

    public Date getTtlPendaftar() {
        return ttlPendaftar;
    }

    public void setTtlPendaftar(Date ttlPendaftar) {
        Date oldTtlPendaftar = this.ttlPendaftar;
        this.ttlPendaftar = ttlPendaftar;
        changeSupport.firePropertyChange("ttlPendaftar", oldTtlPendaftar, ttlPendaftar);
    }

    public String getKotaAsalPendaftar() {
        return kotaAsalPendaftar;
    }

    public void setKotaAsalPendaftar(String kotaAsalPendaftar) {
        String oldKotaAsalPendaftar = this.kotaAsalPendaftar;
        this.kotaAsalPendaftar = kotaAsalPendaftar;
        changeSupport.firePropertyChange("kotaAsalPendaftar", oldKotaAsalPendaftar, kotaAsalPendaftar);
    }

    public int getIdPosisi() {
        return idPosisi;
    }

    public void setIdPosisi(int idPosisi) {
        int oldIdPosisi = this.idPosisi;
        this.idPosisi = idPosisi;
        changeSupport.firePropertyChange("idPosisi", oldIdPosisi, idPosisi);
    }

    public float getNilaiWawancara() {
        return nilaiWawancara;
    }

    public void setNilaiWawancara(float nilaiWawancara) {
        float oldNilaiWawancara = this.nilaiWawancara;
        this.nilaiWawancara = nilaiWawancara;
        changeSupport.firePropertyChange("nilaiWawancara", oldNilaiWawancara, nilaiWawancara);
    }

    public float getNilaiTesTulis() {
        return nilaiTesTulis;
    }

    public void setNilaiTesTulis(float nilaiTesTulis) {
        float oldNilaiTesTulis = this.nilaiTesTulis;
        this.nilaiTesTulis = nilaiTesTulis;
        changeSupport.firePropertyChange("nilaiTesTulis", oldNilaiTesTulis, nilaiTesTulis);
    }

    public float getNilaiPsikotes() {
        return nilaiPsikotes;
    }

    public void setNilaiPsikotes(float nilaiPsikotes) {
        float oldNilaiPsikotes = this.nilaiPsikotes;
        this.nilaiPsikotes = nilaiPsikotes;
        changeSupport.firePropertyChange("nilaiPsikotes", oldNilaiPsikotes, nilaiPsikotes);
    }

    public int getNilaiToefl() {
        return nilaiToefl;
    }

    public void setNilaiToefl(int nilaiToefl) {
        int oldNilaiToefl = this.nilaiToefl;
        this.nilaiToefl = nilaiToefl;
        changeSupport.firePropertyChange("nilaiToefl", oldNilaiToefl, nilaiToefl);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPendaftar != null ? idPendaftar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pendaftar)) {
            return false;
        }
        Pendaftar other = (Pendaftar) object;
        if ((this.idPendaftar == null && other.idPendaftar != null) || (this.idPendaftar != null && !this.idPendaftar.equals(other.idPendaftar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seleksipegawai.Pendaftar[idPendaftar=" + idPendaftar + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
