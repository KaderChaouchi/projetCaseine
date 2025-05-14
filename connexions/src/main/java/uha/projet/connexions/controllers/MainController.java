/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uha.projet.connexions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import uha.projet.connexions.Connexions;


/**
 *
 * @author e1901478
 */

@Controller
@SessionAttributes("donnees")
public class MainController {
    
    @ModelAttribute("donnees")
    public Connexions creeAttribut()
    {
        return new Connexions();
    }
    
    @GetMapping("/log")
    @ModelAttribute("donnees")
    public String log(@ModelAttribute("donnees") Connexions listeEtud, Model model)
    {
        model.addAttribute("liste", listeEtud);
        return "log";
    }
}
