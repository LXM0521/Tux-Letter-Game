/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.logging.*;
import java.text.SimpleDateFormat;
import java.io.File;
import util.XMLUtil;

/**
 *
 * @author liuxi
 */
public class Profil {
    private String nom;
    private String avatar;//化身
    private ArrayList<Partie> parties;
    private String anniversaire;
    public Document doc;

    
    /*public Profil(String nom) {
        this.nom = nom;
    }*/
    

    public Profil(){
        nom = "j";
        anniversaire = "2000/05/21";
        avatar = "player1.svg";
        parties = new ArrayList<>();
    }
    
    public Profil(String nom,String anniversaire){
        this.nom=nom;
        this.anniversaire=anniversaire;
        avatar = "player1.svg";
        parties = new ArrayList<>();
    }
    
    // 带参数的构造函数
    public Profil(String nom, String avatar, String anniversaire) {
        this.nom = nom;
        this.anniversaire=anniversaire;
        avatar = "player1.svg";
        parties = new ArrayList<>();
    }
    

    public Profil(String nomFichier) {
        try{
           DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
           domFactory.setNamespaceAware(true); // never forget this! // XXXXXX
           DocumentBuilder builder = domFactory.newDocumentBuilder();
           doc = builder.parse("src/xml/"+nomFichier+".xml"); 
        }catch(Exception e){
            
        }
        
        //doc = fromXML("src/xml/"+nomFichier+".xml");
        this.nom=doc.getElementsByTagName("pro:nom").item(0).getTextContent();
        this.avatar=doc.getElementsByTagName("pro:avatar").item(0).getTextContent();
        this.anniversaire=xmlDateToProfileDate(doc.getElementsByTagName("pro:anniversaire").item(0).getTextContent());
        parties = new ArrayList<>();
        NodeList partiex=doc.getElementsByTagName("pro:partie");
        for(int i=0;i<partiex.getLength();i++){
            Element partie=(Element)partiex.item(i);
            ajouterPartie(new Partie(partie));
        }
    }
   
    public Document fromXML(String nomFichier) {
        try {
            return XMLUtil.DocumentFactory.fromFile(nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void toXML(String nomFichier) {
        try {
            XMLUtil.DocumentTransform.writeDoc(doc, nomFichier);
        } catch (Exception ex) {
            Logger.getLogger(Profil.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    // 添加一场新的游戏
    public void ajouterPartie(Partie partie) {
        this.parties.add(partie);
    }
    
    public int getDernierNiveau(){
        Partie dernier=parties.get(parties.size()-1);
        return dernier.getNiveau();
    }
    
    public void sauvegarder(String filename){
        String path="src/xml/";
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        try{
           DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
           domFactory.setNamespaceAware(true); // never forget this! // XXXXXX
           DocumentBuilder builder = domFactory.newDocumentBuilder();
           doc = builder.parse(new File("src/xml/new.xml"));
            Element profil = (Element) doc.getElementsByTagName("pro:profil").item(0);
            Element nom = doc.createElement("pro:nom");
            Element avatar = doc.createElement("pro:avatar");
            Element anniversaire = doc.createElement("pro:anniversaire");
            Element parties = doc.createElement("pro:parties");
            nom.setTextContent(this.nom);
            avatar.setTextContent(this.avatar);
            anniversaire.setTextContent(this.anniversaire);
            profil.appendChild(nom);
            profil.appendChild(avatar);
            profil.appendChild(anniversaire);
            
           for (int i = 0; i < this.parties.size(); i++) {
                    Element partie = doc.createElement("pro:partie");
                    Element mot = doc.createElement("pro:mot");
                    Element temps = doc.createElement("pro:temps");
                    partie.setAttribute("date", profileDateToXmlDate(this.parties.get(i).getDate()));
                    if(this.parties.get(i).getPourcentageTrouve()!=100){
                        partie.setAttribute("trouvé", String.valueOf(this.parties.get(i).getPourcentageTrouve())+"%");
                    }
                    mot.setAttribute("niveau", String.valueOf(this.parties.get(i).getNiveau()));
                    mot.setTextContent(this.parties.get(i).getMot());
                    temps.setTextContent(String.valueOf(this.parties.get(i).getTemps()));
                    partie.appendChild(temps);
                    partie.appendChild(mot);
                    parties.appendChild(partie);
           }profil.appendChild(parties);
        }catch(Exception e){
            System.out.print(e);
        }
        toXML(path+filename+".xml");
    }
    
    public ArrayList<Partie> getParties(){
        return parties;
    }
    
    @Override
    public String toString(){
        String s="nom:"+nom+"\n"+"dateNaissance:"+anniversaire+"\n";
        for(int i=0;i<parties.size();i++){
            s=s+parties.get(i).toString()+"\n";
        }
        return s;
    }
    
    // Getter 和 Setter 方法
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAnniversaire() {
        return anniversaire;
    }

    public void setAnniversaire(String anniversaire) {
        this.anniversaire = anniversaire;
    }



    // 删除一场游戏
    public void removePartie(Partie partie) {
        this.parties.remove(partie);
    }

    // toString 方法
    /*@Override
    public String toString() {
        return "Profil{" +
                "nom='" + nom + '\'' +
                ", avatar='" + avatar + '\'' +
                ", anniversaire=" + anniversaire +
                ", parties=" + parties +
                '}';
    }*/
}

    

