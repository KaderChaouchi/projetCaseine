/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uha.projet.connexions.controllers;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import uha.projet.connexions.Connexions;
import uha.projet.connexions.Etudiant;


/**
 *
 * @author e1901478
 */

@Controller
@SessionAttributes("donnees")
public class MainController {
    
    @ModelAttribute("donnees")
    public ArrayList<Etudiant> creeAttribut()
    {
        return new ArrayList<Etudiant>();
    }
    
    @GetMapping("/log")
    public String log(@ModelAttribute("donnees") Connexions listeEtud, Model model)
    {
        model.addAttribute("liste", listeEtud);
        return "log";
    }
    
    @GetMapping("/test")
    public String test(@ModelAttribute("donnees") ArrayList<Etudiant> listeEtud, Model model)
    {
        Etudiant e = new Etudiant("123", "John Smith", "789");
        listeEtud.add(e);
        
        model.addAttribute("info", listeEtud);
        return "test";
    }
    
    @GetMapping("/affiche")
    public String affiche(@ModelAttribute("donnees") ArrayList<Etudiant> listeEtud, Model model)
    {
        model.addAttribute("info", listeEtud);
        return "affiche";
    }
    
    @PostMapping("/recharge")
    public String ajoutCookie(@ModelAttribute("donnees") ArrayList<Etudiant> listEtud, String nom_etud, String id_vpl, String cookie)
    {
        Etudiant e = new Etudiant(id_vpl, nom_etud, cookie);
        if(!listEtud.contains(e))
        {
            listEtud.add(e);
        }
        return "redirect:/affiche";
    }
}
