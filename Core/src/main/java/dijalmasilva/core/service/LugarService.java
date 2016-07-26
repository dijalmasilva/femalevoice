/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.service;

import dijalmasilva.entidades.Lugar;
import dijalmasilva.entidades.Usuario;
import dijalmasilva.enums.TipoDaOcorrencia;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author dijalma
 */
@Service
public interface LugarService {
    
    Lugar salvar(Lugar l, Usuario u);
    
    void remover(Long id);
    
    Lugar buscar(Long id);
    
    List<Lugar> buscarTodos();
    
    List<Lugar> buscarPorData(LocalDate data);
    
    List<Lugar> buscarPorTipo(TipoDaOcorrencia tipo);
}
