/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.entidades;

import com.vividsolutions.jts.geom.Point;
import dijalmasilva.enums.TipoDaOcorrencia;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 *
 * @author dijalma
 */
@Entity
public class Lugar implements Serializable {

    @Id
    @GeneratedValue
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoDaOcorrencia tipo;
    private boolean vitima;
    private boolean anonima;
    @OneToOne
    private Usuario usuario;
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;
    @Convert(converter = ConvertData.class)
    private LocalDate data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoDaOcorrencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoDaOcorrencia tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isVitima() {
        return vitima;
    }

    public void setVitima(boolean vitima) {
        this.vitima = vitima;
    }

    public Point getLocation() {
        return location;
    }

    public Double latitude(){
        return location.getX();
    }
    
    public Double longitude(){
        return location.getY();
    }
    
    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean isAnonima() {
        return anonima;
    }

    public void setAnonima(boolean anonima) {
        this.anonima = anonima;
    }

    public Usuario getUsuario() {
        if (anonima == true) {
            return null;
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lugar other = (Lugar) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lugar{" + "id=" + id + ", descricao=" + descricao + ", tipo=" + tipo + ", vitima=" + vitima + ", anonima=" + anonima + ", usuario=" + usuario + '}';
    }

}
