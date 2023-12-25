/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 *
 * @author liuxi
 */
public class Room {
    private int depth;
    private int height;
    private int width;
    private String textureBottom;
    private String textureNorth;
    private String textureEast;
    private String textureWest;
    private String textureTop;
    private String textureSouth;
    
    public Room (){
        this.depth = 100;
        this.width = 100;
        this.height = 60;
        
        this.textureBottom = "textures/black.png";
        this.textureNorth = "textures/skybox/default/north.png";
        this.textureEast = "textures/skybox/default/east.png";
        this.textureWest = "textures/skybox/default/west.png";
        ///c'est le background
    }
    
    public Room(String filename){
        String path="src/xml/";
        String filepath = path + filename;
        try{
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc=db.parse(filepath);
            Element dimensions=(Element)doc.getElementsByTagName("tux:dimensions").item(0);
            this.depth=Integer.parseInt( ((Element) dimensions.getElementsByTagName("tux:depth").item(0)).getTextContent() );
            this.width=Integer.parseInt( ((Element) dimensions.getElementsByTagName("tux:width").item(0)).getTextContent() );
            this.height=Integer.parseInt( ((Element) dimensions.getElementsByTagName("tux:height").item(0)).getTextContent() );
            Element mapping=(Element)doc.getElementsByTagName("tux:mapping").item(0);
            this.textureBottom=((Element) mapping.getElementsByTagName("tux:textureBottom").item(0)).getTextContent();
            this.textureNorth=((Element) mapping.getElementsByTagName("tux:textureNorth").item(0)).getTextContent();
            this.textureEast=((Element) mapping.getElementsByTagName("tux:textureEast").item(0)).getTextContent();
            this.textureWest=((Element) mapping.getElementsByTagName("tux:textureWest").item(0)).getTextContent();
        }catch(Exception e){
            System.out.println("Erreur6: "+e);
        }
    }

    public double getDepth() {
        return depth;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public String getTextureBottom() {
        return textureBottom;
    }

    public String getTextureNorth() {
        return textureNorth;
    }

    public String getTextureEast() {
        return textureEast;
    }

    public String getTextureWest() {
        return textureWest;
    }

    public String getTextureTop() {
        return textureTop;
    }

    public String getTextureSouth() {
        return textureSouth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setTextureBottom(String textureBottom) {
        this.textureBottom = textureBottom;
    }

    public void setTextureNorth(String textureNorth) {
        this.textureNorth = textureNorth;
    }

    public void setTextureEast(String textureEast) {
        this.textureEast = textureEast;
    }

    public void setTextureWest(String textureWest) {
        this.textureWest = textureWest;
    }

    public void setTextureTop(String textureTop) {
        this.textureTop = textureTop;
    }

    public void setTextureSouth(String textureSouth) {
        this.textureSouth = textureSouth;
    }
    
    
    
    
    
}
