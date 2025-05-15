/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uha.projet.connexions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String log(@ModelAttribute("donnees") Connexions listeEtud, Model model)
    {
        model.addAttribute("liste", listeEtud);
        return "log";
    }
    
    @GetMapping("/test")
    @ModelAttribute("donnees")
    public String test(@ModelAttribute("donnees") Connexions listeEtud, Model model)
    {
        listeEtud.ajout("123", "John Smith", "789");
        
        model.addAttribute("info", listeEtud);
        return "test";
    }
    
    @GetMapping("/affiche")
    public String affiche(@ModelAttribute("donnees") Connexions listeEtud, Model model)
    {
        model.addAttribute("info", listeEtud);
        return "affiche";
    }
    
    @PostMapping("/recharge")
    public String ajoutCookie(@ModelAttribute("donnees") Connexions listEtud, String nom_etud, String id_vpl, String cookie)
    {
        listEtud.ajout(id_vpl, nom_etud, cookie);
        return "redirect:/affiche";
    }
    
    
}
