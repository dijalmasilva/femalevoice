/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.repository;

import dijalmasilva.entidades.Lugar;
import dijalmasilva.enums.TipoDaOcorrencia;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dijalma
 */
@Repository
public interface LugarRepository extends CrudRepository<Lugar, Long>{
 
    List<Lugar> findByData(LocalDate data);
    
    List<Lugar> findByTipo(TipoDaOcorrencia tipo);
}
