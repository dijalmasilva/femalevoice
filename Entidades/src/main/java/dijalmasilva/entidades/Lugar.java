/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijalmasilva.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author dijalma
 */
@Entity
public class Lugar implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    private String descricao;
    
}
