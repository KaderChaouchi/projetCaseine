/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uha.projet.connexions;

/**
 *
 * @author e1901478
 */
public class Etudiant {
    private String ID_VPL;
    private String ID_etudiant;
    private String Cookie;

    public String getID_VPL() {
        return ID_VPL;
    }

    public void setID_VPL(String ID_VPL) {
        this.ID_VPL = ID_VPL;
    }

    public String getID_etudiant() {
        return ID_etudiant;
    }

    public void setID_etudiant(String ID_etudiant) {
        this.ID_etudiant = ID_etudiant;
    }

    public String getCookie() {
        return Cookie;
    }

    public void setCookie(String Cookie) {
        this.Cookie = Cookie;
    }
    
    
    public Etudiant()
    {
        ID_VPL = "00000";
        ID_etudiant = " ";
        Cookie = " ";
    }
    
    public Etudiant(String id_vpl, String code_vpl, String etudiant, String cookie)
    {
        ID_VPL = id_vpl;
        ID_etudiant = etudiant;
        Cookie = cookie;
    }
    
    public boolean isDoubleOf(Etudiant e)
    {
        return (ID_VPL.equals(e.getID_VPL())
                && ID_etudiant.equals(e.getID_etudiant())
                && !(Cookie.equals(e.getCookie())));
    }
    
    public String afficheHTML()
    {
        String s =  ""+ ID_VPL + " , " + ID_etudiant+ " , " + Cookie + "";
        return s;
    }
    
}
