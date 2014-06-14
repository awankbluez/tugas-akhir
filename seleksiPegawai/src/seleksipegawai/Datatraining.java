/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package seleksipegawai;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author bLueZ
 */
@Entity
@Table(name = "datatraining", catalog = "seleksipegawai", schema = "")
@NamedQueries({
    @NamedQuery(name = "Datatraining.findAll", query = "SELECT d FROM Datatraining d"),
    @NamedQuery(name = "Datatraining.findByIdDataTraining", query = "SELECT d FROM Datatraining d WHERE d.idDataTraining = :idDataTraining"),
    @NamedQuery(name = "Datatraining.findByIdPosisi", query = "SELECT d FROM Datatraining d WHERE d.idPosisi = :idPosisi"),
    @NamedQuery(name = "Datatraining.findByTingkatPendidikan", query = "SELECT d FROM Datatraining d WHERE d.tingkatPendidikan = :tingkatPendidikan"),
    @NamedQuery(name = "Datatraining.findByNilaiWawancara", query = "SELECT d FROM Datatraining d WHERE d.nilaiWawancara = :nilaiWawancara"),
    @NamedQuery(name = "Datatraining.findByPengalKerja", query = "SELECT d FROM Datatraining d WHERE d.pengalKerja = :pengalKerja"),
    @NamedQuery(name = "Datatraining.findByNilaiPsikotes", query = "SELECT d FROM Datatraining d WHERE d.nilaiPsikotes = :nilaiPsikotes"),
    @NamedQuery(name = "Datatraining.findByNilaiToefl", query = "SELECT d FROM Datatraining d WHERE d.nilaiToefl = :nilaiToefl")})
public class Datatraining implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDataTraining")
    private Integer idDataTraining;
    @Basic(optional = false)
    @Column(name = "idPosisi")
    private int idPosisi;
    @Basic(optional = false)
    @Column(name = "tingkatPendidikan")
    private String tingkatPendidikan;
    @Basic(optional = false)
    @Column(name = "nilaiWawancara")
    private float nilaiWawancara;
    @Basic(optional = false)
    @Column(name = "pengalKerja")
    private int pengalKerja;
    @Basic(optional = false)
    @Column(name = "nilaiPsikotes")
    private float nilaiPsikotes;
    @Basic(optional = false)
    @Column(name = "nilaiToefl")
    private int nilaiToefl;

    public Datatraining() {
    }

    public Datatraining(Integer idDataTraining) {
        this.idDataTraining = idDataTraining;
    }

    public Datatraining(Integer idDataTraining, int idPosisi, String tingkatPendidikan, float nilaiWawancara, int pengalKerja, float nilaiPsikotes, int nilaiToefl) {
        this.idDataTraining = idDataTraining;
        this.idPosisi = idPosisi;
        this.tingkatPendidikan = tingkatPendidikan;
        this.nilaiWawancara = nilaiWawancara;
        this.pengalKerja = pengalKerja;
        this.nilaiPsikotes = nilaiPsikotes;
        this.nilaiToefl = nilaiToefl;
    }

    public Integer getIdDataTraining() {
        return idDataTraining;
    }

    public void setIdDataTraining(Integer idDataTraining) {
        Integer oldIdDataTraining = this.idDataTraining;
        this.idDataTraining = idDataTraining;
        changeSupport.firePropertyChange("idDataTraining", oldIdDataTraining, idDataTraining);
    }

    public int getIdPosisi() {
        return idPosisi;
    }

    public void setIdPosisi(int idPosisi) {
        int oldIdPosisi = this.idPosisi;
        this.idPosisi = idPosisi;
        changeSupport.firePropertyChange("idPosisi", oldIdPosisi, idPosisi);
    }

    public String getTingkatPendidikan() {
        return tingkatPendidikan;
    }

    public void setTingkatPendidikan(String tingkatPendidikan) {
        String oldTingkatPendidikan = this.tingkatPendidikan;
        this.tingkatPendidikan = tingkatPendidikan;
        changeSupport.firePropertyChange("tingkatPendidikan", oldTingkatPendidikan, tingkatPendidikan);
    }

    public float getNilaiWawancara() {
        return nilaiWawancara;
    }

    public void setNilaiWawancara(float nilaiWawancara) {
        float oldNilaiWawancara = this.nilaiWawancara;
        this.nilaiWawancara = nilaiWawancara;
        changeSupport.firePropertyChange("nilaiWawancara", oldNilaiWawancara, nilaiWawancara);
    }

    public int getPengalKerja() {
        return pengalKerja;
    }

    public void setPengalKerja(int pengalKerja) {
        int oldPengalKerja = this.pengalKerja;
        this.pengalKerja = pengalKerja;
        changeSupport.firePropertyChange("pengalKerja", oldPengalKerja, pengalKerja);
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
        hash += (idDataTraining != null ? idDataTraining.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datatraining)) {
            return false;
        }
        Datatraining other = (Datatraining) object;
        if ((this.idDataTraining == null && other.idDataTraining != null) || (this.idDataTraining != null && !this.idDataTraining.equals(other.idDataTraining))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seleksipegawai.Datatraining[idDataTraining=" + idDataTraining + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
