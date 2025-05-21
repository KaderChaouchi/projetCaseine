/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uha.projet.connexions.controllers;

import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import uha.projet.connexions.Connexions;
import uha.projet.connexions.Etudiant;

/**
 *
 * @author e1901478
 */
/*
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
        if(!listeEtud.contains(e))
        {
            listeEtud.add(e);
        }
        
        model.addAttribute("info", listeEtud);
        return "test";
    }
    
    @GetMapping("/affiche")
    public String affiche(@ModelAttribute("donnees") ArrayList<Etudiant> listeEtud, Model model)
    {
        model.addAttribute("info", listeEtud);
        return "affiche";
    }
    
    @GetMapping("/recharge")
    public String ajoutCookie(@ModelAttribute("donnees") ArrayList<Etudiant> listEtud, @RequestParam("nom_etud") String nom_etud, @RequestParam("id_vpl") String id_vpl, @RequestParam("cookie") String cookie)
    {
        Etudiant e = new Etudiant(id_vpl, nom_etud, cookie);
        if(!listEtud.contains(e))
        {
            listEtud.add(e);
        }
        return "redirect:/affiche";
    }
}*/
/**
 * @RestController public class MainController {
 *
 * @GetMapping("/recharge")
 * @ResponseBody public ResponseEntity<String> connexion(
 * @RequestParam("nom_etud") String nom,
 * @RequestParam("id_vpl") String id_vpl,
 * @RequestParam("cookie") String cookie ) { System.out.println("Reçu : nom=" +
 * nom + ", vpl=" + id_vpl + ", cookie=" + cookie); return
 * ResponseEntity.ok("Connexion enregistrée"); } }
 *
 */
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController

public class MainController {

    ArrayList<Etudiant> liste = new ArrayList<Etudiant>();

    @GetMapping("/affiche")
    public String affiche(@ModelAttribute("donnees") ArrayList<Etudiant> listeEtud, Model model) {
        String s = "<head>\n"
                + "<title>Log de connexions</title>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<div>Voici les logs de connexions</div>"
                + "<body>"
                + "<tr>"
                + "<th> adresse ip</th>"
                + "<th> VPL</th>"
                + "<th> Nom </th>"
                + "<th> Cookie </th>"
                + "</tr><br>";
        for (int i = 0; i < liste.size(); i++) {
            s += liste.get(i).afficheHTML() + "<br>";
        }

        s += "</table>"
                + "</body>";
        return s;
    }

    @RequestMapping("/recharge")
    public RedirectView connexion(
            @RequestParam("nom_etud") String nom,
            @RequestParam("id_vpl") String id_vpl,
            @RequestParam("cookie") String cookie,
            HttpServletRequest request
    ) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr(); // fallback
        }
        Etudiant e = new Etudiant(id_vpl, nom, cookie, ip);

        if (!liste.contains(e)) {
            liste.add(e);
        }

        System.out.println("📥 Requete reçue de " + ip);
        System.out.println("👤 nom_etud = " + nom);
        System.out.println("🆔 id_vpl   = " + id_vpl);
        System.out.println("🍪 cookie   = " + cookie);
        //return ResponseEntity.ok("Reçu depuis " + remoteIp);
        return new RedirectView("/affiche");
    }
}
