/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author lxm
 */
public class Dico extends DefaultHandler {
    private ArrayList<String> listeNiveau1;
    private ArrayList<String> listeNiveau2;
    private ArrayList<String> listeNiveau3;
    private ArrayList<String> listeNiveau4;
    private ArrayList<String> listeNiveau5;
    private String cheminFichierDico;
    private StringBuffer buffer;

    
    public Dico(String cheminFichierDico){
        // Initialiser les ArrayLists
        this.listeNiveau1 = new ArrayList<>();
        this.listeNiveau2 = new ArrayList<>();
        this.listeNiveau3 = new ArrayList<>();
        this.listeNiveau4 = new ArrayList<>();
        this.listeNiveau5 = new ArrayList<>();

        // Copier le chemin du fichier dans l'attribut
        this.cheminFichierDico = cheminFichierDico;
        
        //lireDictionnaireDOM(this.cheminFichierDico,"dico.xml");
        lireDictionnaireSAX();
    }
    
    
    public String getMotDepuisListeNiveaux(int niveau){
        String mot="";
        niveau = verifieNiveau(niveau); // verifie le niveau validee;

        switch (niveau) {
            case 1:
              mot = getMotDepuisListe(listeNiveau1);
              break;
            case 2:
              mot = getMotDepuisListe(listeNiveau2);
              break;
            case 3:
              mot = getMotDepuisListe(listeNiveau3);
              break;
            case 4:
              mot = getMotDepuisListe(listeNiveau4);
              break;
            case 5:
              mot = getMotDepuisListe(listeNiveau5);
              break;
            default:
              System.out.println("NiveauFalse"); 
                                      //Si le niveau n'est pas compris dasn 1 et 5, retourne "NiveauFalse"
        }
        return mot;
        
    }
    
    public void ajouteMotADico(int niveau, String mot){
        niveau = verifieNiveau(niveau); 

    switch (niveau) {
        case 1:
            listeNiveau1.add(mot);
            break;
        case 2:
            listeNiveau2.add(mot);
            break;
        case 3:
            listeNiveau3.add(mot);
            break;
        case 4:
            listeNiveau4.add(mot);
            break;
        case 5:
            listeNiveau5.add(mot);
            break;
        default:
            // 可以选择什么也不做，或者在级别无效时抛出异常
            System.out.println("Erreur du niveau");
        }
    }
    
    //vérifie le niveau est valide
    private int verifieNiveau(int niveau){
        if(niveau < 1){
            niveau = 1;
        }else if(niveau > 5){
            niveau = 5;
        }
        return niveau;////满足要求的情况下
    }
    
    //prends un mot depuis la liste de mot
    private String getMotDepuisListe(ArrayList<String> list){
        if(list.isEmpty()){
            return "Empty";
        }
        ///他没在这里写判断是否为empty的情况，应该放在了其他地方
        Random random = new Random();
        //list.size retourner le nombre d'element dans la list
        //random.nextInt(n) c'est une method de java.util.Random, il retourne un entier aleatoire de 0 a n
        //list.size() est le longeur de list
        //list.get(index) prends l'elememnt de l'index
        return list.get(random.nextInt(list.size()));//Attention! La difference du length et l'indice
        
    }

    
    public void lireDictionnaireDOM(String path, String filename){
        try {
            File xmlFile = new File(path + filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(path+filename);

            // 假设 XML 文件中有多个 <mot> 元素，每个元素都有一个级别属性
            NodeList mots = doc.getElementsByTagName("di:mot");
            //注意这里的元素使用了命名空间，所以有两种处理方式getElementsByTagName 或 getElementsByTagNameNS
            //不关心命名空间时使用 getElementsByTagName("mot")，但是一般应该采用第二种方式
            //getElementsByTagNameNS("namespaceURI", "mot")，其中 "namespaceURI" 是 di 前缀所代表的命名空间的实际 URI

            for (int i = 0; i < mots.getLength(); i++) {
                Element mot = (Element) mots.item(i);
                String texte = mot.getTextContent();
                int niveau = Integer.parseInt(mot.getAttribute("niveau"));

                ajouteMotADico(niveau, texte);
            }
        } catch (Exception e) {
            System.out.println("Erreur2: "+e);
        }
    }
    
    public void lireDictionnaireSAX(){
        try{
            SAXParserFactory pf=SAXParserFactory.newInstance();
            SAXParser p=pf.newSAXParser(); 
            File f=new File("src/xml/dico.xml");
            DefaultHandler dh=new DefaultHandler(){
                int niveau;
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if(qName.equals("di:mot")){
                        niveau=Integer.parseInt(attributes.getValue(0));           
                    }else{
                        buffer=new StringBuffer();
                    }
                    buffer.setLength(0);
                }
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if(qName.equals("di:mot")){
                        switch(verifieNiveau(niveau)){
                        case 1:
                          listeNiveau1.add(buffer.toString());
                          break;
                        case 2:
                          listeNiveau2.add(buffer.toString());
                          break;
                        case 3:
                          listeNiveau3.add(buffer.toString());
                          break;
                        case 4:
                          listeNiveau4.add(buffer.toString());
                          break;
                        case 5:
                          listeNiveau5.add(buffer.toString());
                          break;
                        default:
                          System.out.println("Erreur du niveau");
                        }
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if(buffer!=null){
                        buffer.append(ch, start, length);
                    }
                }
            };
            p.parse(f, dh);
        }catch(Exception e){
            System.out.println("Erreur1: "+e);
        }
        
    }
   
}
