/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.service;

import dijalmasilva.core.repository.LugarRepository;
import dijalmasilva.entidades.Lugar;
import dijalmasilva.entidades.Usuario;
import dijalmasilva.enums.TipoDaOcorrencia;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LugarServiceImpl implements LugarService {

    @Inject
    private LugarRepository dao;

    @Override
    public Lugar salvar(Lugar l, Usuario u) {
        if (l.isAnonima()) {
            l.setUsuario(null);
        } else {
            l.setUsuario(u);
        }
        return dao.save(l);
    }

    @Override
    public void remover(Long id) {
        dao.delete(id);
    }

    @Override
    public Lugar buscar(Long id) {
        return dao.findOne(id);
    }

    @Override
    public List<Lugar> buscarTodos() {
        return (List<Lugar>) dao.findAll();
    }

    @Override
    public List<Lugar> buscarPorData(LocalDate data) {
        return dao.findByData(data);
    }

    @Override
    public List<Lugar> buscarPorTipo(TipoDaOcorrencia tipo) {
        return dao.findByTipo(tipo);
    }

}
