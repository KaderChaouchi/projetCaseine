/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uha.projet.connexions.controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import uha.projet.connexions.Etudiant;

/**
 *
 * @author e1901478
 */
/*

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
 /*
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("donnees")
public class MainController {

    //ArrayList<Etudiant> liste = new ArrayList<Etudiant>();
    @ModelAttribute("donnees")
    private static ArrayList<Etudiant> creeAttribut() {
        return new ArrayList<Etudiant>();
    }

    @GetMapping("/affiche")
    public String affiche(@ModelAttribute("donnees") ArrayList<Etudiant> listeEtud, Model model) {

        model.addAttribute("donnees", listeEtud);
        return "affiche";
    }

    /*@PostMapping("/recharge")
    public String connexion(@ModelAttribute("donnees") ArrayList<Etudiant> listEtud,
            @RequestParam("nom_etud") String nom_etud,
            @RequestParam("id_vpl") String id_vpl,
            @RequestParam("cookie") String cookie,
            HttpServletRequest request
    ) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null) {
            ip = request.getRemoteAddr(); // fallback
        }
        Etudiant e = new Etudiant(id_vpl, nom_etud, cookie, ip);

        if (!(e.getID_etudiant().equals("Utilisateur non trouvé"))) {
            if (!listEtud.contains(e)) {
                e.setCommentFromList(listEtud);
                listEtud.add(e);
            }
        }
        System.out.println("Requete reçue de " + ip);
        System.out.println("nom_etud = " + nom_etud);
        System.out.println("id_vpl   = " + id_vpl);
        System.out.println("cookie   = " + cookie);
        //return ResponseEntity.ok("Reçu depuis " + remoteIp);
        return "redirect:/affiche";
    }*//*
    @GetMapping("/recharge")
    @ResponseBody
    public String rechargeVPL(@ModelAttribute("donnees") ArrayList<Etudiant> listEtud, @RequestBody Etudiant e, HttpServletRequest request) {
        e.setIp_adress(request.getRemoteAddr());
        e.setComment("Requête VPL POST");
        listEtud.add(e);
        return "Ajout VPL : " + e;
    }

    @PostMapping("/filtre")
    public RedirectView filtrage(@ModelAttribute("donnees") ArrayList<Etudiant> listEtud,
            Model model,
            @RequestParam("vpl") String vpl,
            @RequestParam("etudiant") String etudiant) {

        ArrayList<Etudiant> filtree = new ArrayList<>();
        for (int i = 0; i < listEtud.size(); i++) {
            if (vpl != null) {
                if (etudiant != null) {
                    if (listEtud.get(i).getID_VPL().equals(vpl) && listEtud.get(i).getID_etudiant().equals(etudiant)) {
                        filtree.add(listEtud.get(i));
                    }
                } else {
                    if (listEtud.get(i).getID_VPL().equals(vpl)) {
                        filtree.add(listEtud.get(i));
                    }
                }
            } else {
                if (etudiant != null) {
                    if (listEtud.get(i).getID_etudiant().equals(etudiant)) {
                        filtree.add(listEtud.get(i));
                    }
                } else {
                    filtree.add(listEtud.get(i));
                }
            }
        }

        model.addAttribute("filtree", filtree);
        return new RedirectView("/affiche");
    }

}*/

@CrossOrigin(origins = "*")
@Controller
public class MainController {

    // Liste statique partagée - persiste entre toutes les requêtes
    private static final List<Etudiant> LISTE_ETUDIANTS = Collections.synchronizedList(new ArrayList<>());

    @GetMapping("/affiche")
    public String affiche(Model model) {
        System.out.println("Page /affiche - Nombre d'étudiants: " + LISTE_ETUDIANTS.size());
        
        // Debug: afficher tous les étudiants
        synchronized(LISTE_ETUDIANTS) {
            for (int i = 0; i < LISTE_ETUDIANTS.size(); i++) {
                System.out.println("Étudiant " + i + ": " + LISTE_ETUDIANTS.get(i).afficheHTML());
            }
        }

        // Créer une copie pour éviter les modifications concurrentes
        List<Etudiant> copieEtudiants = new ArrayList<>(LISTE_ETUDIANTS);

        model.addAttribute("donnees", copieEtudiants);
        model.addAttribute("filtree", copieEtudiants);

        return "affiche";
    }

    @GetMapping("/recharge")
    public String connexion(@RequestParam("nom_etud") String nom_etud,
            @RequestParam("id_vpl") String id_vpl,
            @RequestParam("cookie") String cookie,
            HttpServletRequest request) {

        // Récupération de l'adresse IP
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }

        System.out.println("========== NOUVELLE REQUÊTE ==========");
        System.out.println("Requête reçue de " + ip);
        System.out.println("nom_etud = '" + nom_etud + "'");
        System.out.println("id_vpl   = '" + id_vpl + "'");
        System.out.println("cookie   = '" + cookie + "'");
        System.out.println("Taille liste AVANT ajout: " + LISTE_ETUDIANTS.size());

        // Création de l'étudiant
        Etudiant nouvelEtudiant = new Etudiant(id_vpl, nom_etud, cookie, ip);

        // Vérification et ajout à la liste
        if (nom_etud != null && !nom_etud.trim().isEmpty() && !nom_etud.equals("Utilisateur non trouvé")) {

            synchronized (LISTE_ETUDIANTS) {
                // Vérifier si l'étudiant existe déjà (même nom, même VPL, même IP)
                boolean existe = false;
                for (Etudiant existant : LISTE_ETUDIANTS) {
                    if (existant.getID_etudiant().equals(nouvelEtudiant.getID_etudiant())
                            && existant.getID_VPL().equals(nouvelEtudiant.getID_VPL())
                            && existant.getIp_adress().equals(nouvelEtudiant.getIp_adress())) {
                        existe = true;
                        //System.out.println("Étudiant déjà présent (même nom, VPL et IP)");
                        break;
                    }
                }

                if (!existe) {
                    // Générer le commentaire en fonction de la liste actuelle
                    nouvelEtudiant.setCommentFromList(new ArrayList<>(LISTE_ETUDIANTS));
                    LISTE_ETUDIANTS.add(nouvelEtudiant);
                    System.out.println("✅ Nouvel étudiant ajouté: " + nouvelEtudiant.getID_etudiant());
                } else {
                    System.out.println("⚠️ Étudiant déjà présent, non ajouté");
                }
            }
        } else {
            System.out.println("❌ Étudiant non valide (nom vide ou 'Utilisateur non trouvé')");
        }

        System.out.println("Taille liste APRÈS ajout: " + LISTE_ETUDIANTS.size());
        System.out.println("=====================================");

        return "redirect:/affiche";
    }

    @PostMapping("/recharge")
    public String connexionPost(@RequestParam("nom_etud") String nom_etud,
            @RequestParam("id_vpl") String id_vpl,
            @RequestParam("cookie") String cookie,
            HttpServletRequest request) {
        System.out.println("Requête POST reçue");
        return connexion(nom_etud, id_vpl, cookie, request);
    }

    @GetMapping("/filtre")
    public String filtrage(Model model,
            @RequestParam(value = "vpl", required = false) String vpl,
            @RequestParam(value = "etudiant", required = false) String etudiant) {

        List<Etudiant> filtree = new ArrayList<>();

        synchronized (LISTE_ETUDIANTS) {
            // Logique de filtrage
            for (Etudiant e : LISTE_ETUDIANTS) {
                boolean matchVpl = (vpl == null || vpl.trim().isEmpty() || e.getID_VPL().equals(vpl.trim()));
                boolean matchEtudiant = (etudiant == null || etudiant.trim().isEmpty() || e.getID_etudiant().equals(etudiant.trim()));

                if (matchVpl && matchEtudiant) {
                    filtree.add(e);
                }
            }
        }

        // Ajout des attributs au modèle
        model.addAttribute("filtree", filtree);
        model.addAttribute("donnees", new ArrayList<>(LISTE_ETUDIANTS));

        System.out.println("Filtrage - VPL: '" + vpl + "', Etudiant: '" + etudiant + "'");
        System.out.println("Résultats filtrés: " + filtree.size() + " étudiants");

        return "affiche";
    }

    @GetMapping("/reset")
    public String reset() {
        synchronized (LISTE_ETUDIANTS) {
            LISTE_ETUDIANTS.clear();
        }
        System.out.println("Liste des étudiants réinitialisée");
        return "redirect:/affiche";
    }

    @GetMapping("/debug")
    public String debug(Model model) {
        System.out.println("=== DEBUG INFO ===");
        synchronized (LISTE_ETUDIANTS) {
            System.out.println("Nombre d'étudiants: " + LISTE_ETUDIANTS.size());
            for (int i = 0; i < LISTE_ETUDIANTS.size(); i++) {
                Etudiant e = LISTE_ETUDIANTS.get(i);
                System.out.println("Étudiant " + i + ":");
                System.out.println("  - Nom: '" + e.getID_etudiant() + "'");
                System.out.println("  - VPL: '" + e.getID_VPL() + "'");
                System.out.println("  - Cookie: '" + e.getCookie() + "'");
                System.out.println("  - IP: '" + e.getIp_adress() + "'");
                System.out.println("  - Commentaire: '" + e.getComment() + "'");
            }
        }
        System.out.println("==================");

        List<Etudiant> copieEtudiants = new ArrayList<>(LISTE_ETUDIANTS);
        model.addAttribute("donnees", copieEtudiants);
        model.addAttribute("filtree", copieEtudiants);
        return "affiche";
    }

    @GetMapping("/stats")
    public String stats(Model model) {
        List<Etudiant> copieEtudiants = new ArrayList<>(LISTE_ETUDIANTS);

        model.addAttribute("donnees", copieEtudiants);
        model.addAttribute("filtree", copieEtudiants);
        model.addAttribute("totalEtudiants", copieEtudiants.size());

        // Compter les étudiants uniques
        long etudiantsUniques = copieEtudiants.stream()
                .map(Etudiant::getID_etudiant)
                .distinct()
                .count();
        model.addAttribute("etudiantsUniques", etudiantsUniques);

        // Compter les VPL uniques
        long vplUniques = copieEtudiants.stream()
                .map(Etudiant::getID_VPL)
                .distinct()
                .count();
        model.addAttribute("vplUniques", vplUniques);

        return "affiche";
    }

    @GetMapping("/export_normal")
    public String exportToFile(@RequestParam(value = "file_name", required = true) String file_name) throws IOException {
        
        List<Etudiant> copieEtudiants = new ArrayList<>(LISTE_ETUDIANTS);
        
        String full_path = "D:\\"+file_name;
        Path path = Paths.get(full_path);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                path, StandardOpenOption.APPEND, StandardOpenOption.CREATE); PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println("Adresse ip ; ID VPL ; Nom Etudiant ; Cookie ; Commentaire");
            String s = "";
            for(int i = 0; i <copieEtudiants.size(); i++)
            {
                s = copieEtudiants.get(i).toString(); 
                printWriter.println(s);
            }
            
        }
        return "redirect:/affiche";
    }

    @GetMapping("/export_filtre")
    public String exportToFileFiltre(
            @RequestParam(value = "file_name", required = true) String file_name,
            @RequestParam(value = "vpl", required = false) String vpl,
            @RequestParam(value = "etudiant", required = false) String etudiant) throws IOException {
        List<Etudiant> filtree = new ArrayList<>();

        synchronized (LISTE_ETUDIANTS) {
            // Logique de filtrage
            for (Etudiant e : LISTE_ETUDIANTS) {
                boolean matchVpl = (vpl == null || vpl.trim().isEmpty() || e.getID_VPL().equals(vpl.trim()));
                boolean matchEtudiant = (etudiant == null || etudiant.trim().isEmpty() || e.getID_etudiant().equals(etudiant.trim()));

                if (matchVpl && matchEtudiant) {
                    filtree.add(e);
                }
            }
        }
        String full_path = "D:\\"+file_name;
        Path path = Paths.get(full_path);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                path, StandardOpenOption.APPEND, StandardOpenOption.CREATE); PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println("Adresse ip ; ID VPL ; Nom Etudiant ; Cookie ; Commentaire");
            String s = "";
            for(int i = 0; i <filtree.size(); i++)
            {
                s = filtree.get(i).toString(); 
                printWriter.println(s);
            }
            
        }

        return "redirect:/affiche";
    }
}
