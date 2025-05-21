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
    private String ip_adress;

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

    public String getIp_adress() {
        return ip_adress;
    }

    public void setIp_adress(String ip_adress) {
        this.ip_adress = ip_adress;
    }
    
    
    public Etudiant()
    {
        ID_VPL = "00000";
        ID_etudiant = " ";
        Cookie = " ";
        ip_adress = "0.0.0.0";
    }
    
    public Etudiant(String id_vpl, String etudiant, String cookie, String ip_add)
    {
        ID_VPL = id_vpl;
        ID_etudiant = etudiant;
        Cookie = cookie;
        ip_adress = ip_add;
    }
    
public boolean isDoubleOf(Etudiant e)
    {
        return (ID_VPL.equals(e.getID_VPL())
                && ID_etudiant.equals(e.getID_etudiant())
                && !(Cookie.equals(e.getCookie())));
    }
    
    public String afficheHTML()
    {
        String s = "<tr><th>"+ ip_adress+ "</th> <th> "+ ID_VPL + " </th> <th> " + ID_etudiant+ " </th> <th> " + Cookie + " </th></tr>";
        return s;
    }
    
    /**
     *
     * @param o
     * @return boolean : true if equals, false if not
     */
    @Override
    public boolean equals(Object o)
    {
        if(o==null || o.getClass() != getClass())
            return false;        
        Etudiant e = (Etudiant)o;
        return ID_VPL.equals(e.getID_VPL()) && ID_etudiant.equals(e.getID_etudiant()) && Cookie.equals(e.getCookie()) && ip_adress.equals(e.getIp_adress());
    }
}