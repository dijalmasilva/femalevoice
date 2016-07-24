/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.core.service;

import dijalmasilva.core.repository.UsuarioRepository;
import dijalmasilva.entidades.Usuario;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioRepository dao;
    
    @Override
    public Usuario salvar(Usuario u) {
        return dao.save(u);
    }

    @Override
    public void remover(Long id) {
        dao.delete(id);
    }

    @Override
    public void remover(Usuario u) {
        dao.delete(u);
    }

    @Override
    public Usuario atualizar(Usuario u) {
        return dao.save(u);
    }
    
}
