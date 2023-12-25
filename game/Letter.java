/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import env3d.advanced.EnvNode;
import java.util.Random;

/**
 *
 * @author liuxi
 */
public class Letter extends EnvNode{
    private char lettre;
    
    private boolean isEspace;
    public Letter(char l, Room room){
        lettre = Character.toLowerCase(l);
        setX(room.getWidth()*Math.random());// positionnement au milieu de la largeur de la room
        setZ(room.getDepth()*Math.random()); // positionnement au milieu de la profondeur de la room
        setScale(4.0);
        setY(getScale() * 1.1); // positionnement en hauteur basé sur la taille de Tux
        if (this.isEspace) {
            setTexture("models/letter/cube.png"); 
        } else {
            String texturePath = "models/letter/" + l + ".png";
            setTexture(texturePath); 
        }
        setModel("models/cube/cube.obj"); 
    }
    
    
    //两个字母模型碰撞
    // Parametre lettre est comme un autre model pour calculer
    protected boolean testeRoomCollision(double x, double z) {
        double dlst = Math.sqrt(Math.pow(this.getX() - x, 2)  + Math.pow(this.getZ() - z, 2));
        if (dlst < this.getScale()*2) { // 检测距离是否小于两个模型尺寸之和
            return true; // 碰撞发生
        }else{
            return false; // 无碰撞
            }
        }
    //////如果发生碰撞应该怎么办？？？
    
    
    //在避免碰撞的情况下生成随机位置
    protected double[] genereLocation(){
        Random rand = new Random();
        double[] location = new double[2];
        boolean collision;
        
        do{
            double x = rand.nextDouble()*100;//nextDouble本质是生成一个0到1之间的随机浮数
            double z = rand.nextDouble()*100;
            
            //verifier il y a de la collision ou pas 
            collision = testeRoomCollision(x, z);
            
            //si non, ajouter dans le tableau de location
            if(!collision){
                location[0] = x;
                location[1] = z;
            }
        }while(!collision);//如果发生碰撞直接随机位置
        
        return location;
    }

}
