/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.controllers;

import dijalmasilva.core.service.LugarService;
import dijalmasilva.entidades.Lugar;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dijalma
 */
@Controller
public class Session {
    
    @Inject
    private LugarService lugarService;
    
    @RequestMapping("/")
    public void index(HttpServletResponse resp) throws IOException{
        resp.sendRedirect("/home");
    }
    
    @RequestMapping("/home")
    public String home(HttpServletRequest req){
        List<Lugar> todasOcorrencias = lugarService.buscarTodos();
        req.getServletContext().setAttribute("todasOcorrencias", todasOcorrencias);
        return "home";
    }
}
