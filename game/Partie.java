/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author liuxi
 */
public class Partie {    
    private String date;
    private int niveau;
    private String mot;
    private double temps;
    private int pourcentageTrouve;

   // Constructeur pour une nouvelle partie
    public Partie(String date, String mot, int niveau) {
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
        this.pourcentageTrouve = 0; // Initialisé à 0, sera mis à jour pendant le jeu
        this.temps = 0; // Initialisé à 0, sera mis à jour à la fin de la partie
    }
    
    // Constructeur pour charger une partie depuis un élément XML
    public Partie(Element partie) {
        this.date=xmlDateToProfileDate(partie.getAttribute("date"));
        if(partie.hasAttribute("trouvé")){
            String t=partie.getAttribute("trouvé");
            t=t.substring(0, t.length()-1);
            this.pourcentageTrouve=Integer.parseInt(t);
        }else{
            this.pourcentageTrouve=100;
        }
        Element motp=(Element)partie.getElementsByTagName("mot").item(0);
        this.mot=motp.getTextContent();
        this.niveau=Integer.parseInt(motp.getAttribute("niveau"));
        NodeList temps=partie.getElementsByTagName("temps");
        if(temps.getLength()>0){
            this.temps=Double.valueOf(((Element)temps.item(0)).getTextContent());
        }else{
            this.temps=0;
        }
        
    }
    
    // Crée un élément XML représentant cette partie
    public Element getPartie(Document doc) {
        Element partie=doc.createElement("partie");
        partie.setAttribute(date, profileDateToXmlDate(date));
        if(pourcentageTrouve!=100){
            partie.setAttribute("trouvé", pourcentageTrouve+"%");
        }else{
            Element temps=doc.createElement("temps");
            temps.setTextContent(String.valueOf(this.temps));
            partie.appendChild(temps);
        }
        Element mot=doc.createElement("mot");
        mot.setAttribute("niveau", String.valueOf(niveau));
        mot.setTextContent(this.mot);
        partie.appendChild(mot);
        return partie;
    }

    // Getters pour les attributs immuables
    public String getDate() {
        return date;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getMot() {
        return mot;
    }

    // Getters et setters pour les attributs modifiables
    public double getTemps() {
        return temps;
    }
    

    public void setTemps(int temps) {
        if(temps>3){
            this.temps=temps;
        }
    }
    
    public void setMot(String mot){
        this.mot = mot;
    }

    public int getPourcentageTrouve() {
        return pourcentageTrouve;
    }

   // Met à jour le pourcentage de lettres trouvées
    public void setPourcentageTrouve(int nbLettresRestantes) {
        if (mot != null && mot.length() > 0) {
            this.pourcentageTrouve = 100 * (mot.length() - nbLettresRestantes) / mot.length();
        }
    }
    
    // Retourne une chaîne représentant la partie
    @Override
    public String toString() {
        return "Partie{" +
                "date='" + date + '\'' +
                ", mot='" + mot + '\'' +
                ", niveau=" + niveau +
                ", trouve=" +pourcentageTrouve  +"%"+
                ", temps=" + temps +
                '}';
    }
    
    public static String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));

        return date;
    }
    
    public static String profileDateToXmlDate(String profileDate) {
        String date;
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));

        return date;
    }



    
}
