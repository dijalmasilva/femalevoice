/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.service;

import dijalmasilva.entidades.Usuario;
import org.springframework.stereotype.Service;

/**
 *
 * @author dijalma
 */
@Service
public interface UsuarioService {
    
    Usuario salvar(Usuario u);
    
    void remover(Long id);
    
    void remover(Usuario u);
    
    Usuario atualizar(Usuario u);
}
