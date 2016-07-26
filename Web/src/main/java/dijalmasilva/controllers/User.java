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
    public String novoUsuario(Usuario u, HttpServletRequest req) {
        Usuario usuario = service.salvar(u);
        if (usuario != null) {
            req.setAttribute("result", "Usuário cadastrado com sucesso.");
        } else {
            req.setAttribute("result", "Não foi possível cadastrar o usuário \n Username já está em uso.");
        }
        return "home";
    }

    @RequestMapping("/login")
    public String login(String login, String password, HttpServletRequest req) {

        Usuario usuarioLogado = service.login(login, password);

        if (usuarioLogado != null) {
            req.getSession().setAttribute("usuarioLogado", usuarioLogado);
            req.setAttribute("result", "Bem vindo!");
        } else {
            req.setAttribute("result", "Usuário ou senha inválidos.");
        }
        
        return "home";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpServletRequest req){
        req.getSession().invalidate();
        
        return "home";
    } 
}
