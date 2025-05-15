/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uha.projet.connexions;

import java.util.ArrayList;

/**
 *
 * @author e1901478
 */
public class Connexions {

    private ArrayList<Etudiant> liste;
    int size;
    
    public Connexions(){
        
        liste = new ArrayList<>();
    }
    
    public void ajout(Etudiant e)
    {
        if(! liste.contains(e))
        {
            liste.add(e);
        }
    }
    
    public void ajout(String id_vpl, String code_vpl, String etudiant, String cookie)
    {
        Etudiant e = new Etudiant(id_vpl, code_vpl, etudiant, cookie);
        if(! liste.contains(e))
        {
            liste.add(e);
        }
    }
    
    public String afficheHTML()
    {
        String s = "";
        for(int i = 0; i<liste.size(); i++)
        {
            s += liste.get(i).afficheHTML();
        }
        return s;
    }
    
    
}
