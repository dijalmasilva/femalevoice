/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.forms;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateSequence;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.impl.PackedCoordinateSequence;
import dijalmasilva.entidades.Lugar;
import dijalmasilva.enums.TipoDaOcorrencia;
import java.sql.Date;

/**
 *
 * @author dijalma
 */
public class LugarForm {

    private String descricao;
    private Date data;
    private TipoDaOcorrencia tipo;
    private boolean vitima;
    private boolean anonima;
    private String latitude;
    private String longitude;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TipoDaOcorrencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoDaOcorrencia tipo) {
        this.tipo = tipo;
    }

    public boolean isVitima() {
        return vitima;
    }

    public void setVitima(boolean vitima) {
        this.vitima = vitima;
    }

    public boolean isAnonima() {
        return anonima;
    }

    public void setAnonima(boolean anonima) {
        this.anonima = anonima;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LugarForm{" + "descricao=" + descricao + ", data=" + data + ", tipo=" + tipo + ", vitima=" + vitima + ", anonima=" + anonima + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

    public Lugar convertToLugar() {
        Lugar lugar = new Lugar();
        lugar.setAnonima(anonima);
        lugar.setData(data.toLocalDate());
        lugar.setDescricao(descricao);
        lugar.setLocation(toPoint(latitude, longitude));
        lugar.setTipo(tipo);
        lugar.setVitima(vitima);
        System.out.println(lugar);
        return lugar;
    }

    private Point toPoint(String lat, String lng) {
        double x = Double.parseDouble(lat);
        double y = Double.parseDouble(lng);

        GeometryFactory factory = new GeometryFactory();
        Coordinate coordinates = new Coordinate(x, y);
        Coordinate[] arrayCoordinates = {coordinates};
        CoordinateSequence c = new PackedCoordinateSequence.Double(arrayCoordinates);
        return new Point(c, factory);
    }
}
