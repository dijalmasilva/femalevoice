/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.controllers;

import dijalmasilva.core.service.UsuarioService;
import dijalmasilva.entidades.Usuario;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dijalma
 */
@Controller
@RequestMapping("/user")
public class User {
    
    @Inject
    private UsuarioService service;
    
    @RequestMapping("/new")
    public String novoUsuario(Usuario u, HttpServletRequest req){
        Usuario usuario = service.salvar(u);
        if (usuario != null){
            req.setAttribute("result", "Usuário cadastrado com sucesso.");
        }else{
            req.setAttribute("result", "Não foi possível cadastrar o usuário.");
        }
        return "home";
    }
}
