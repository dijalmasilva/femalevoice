/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.controllers;

import dijalmasilva.core.service.LugarService;
import dijalmasilva.entidades.Lugar;
import dijalmasilva.entidades.Usuario;
import dijalmasilva.forms.LugarForm;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author dijalma
 */
@Controller
@RequestMapping("/lugar")
public class LugarController {
    
    @Inject
    private LugarService service;
    
    @RequestMapping("/new")
    public String newLugar(LugarForm l, HttpServletRequest req){
        
        if (l == null){
            req.setAttribute("result", "Algum dado inserido está inválido.");
        }else{
            Usuario usuarioLogado = (Usuario) req.getSession().getAttribute("usuarioLogado");
            Lugar newPlace = service.salvar(l.convertToLugar(), usuarioLogado);
            if (newPlace != null){
                List<Lugar> todasOcorrencias = service.buscarTodos();
                req.getServletContext().setAttribute("todasOcorrencias", todasOcorrencias);
                req.setAttribute("result", "Ocorrência registrada com sucesso!");
            }else{
                req.setAttribute("result", "Não foi possível registrar a ocorrência. \n Tente novamente mais tarde.!");
            }
        }
        
        return "home";
    }
    
    @RequestMapping("/place/{id}")
    public @ResponseBody Lugar seeLugar(@PathVariable Long id){
        return service.buscar(id);
    } 
}
