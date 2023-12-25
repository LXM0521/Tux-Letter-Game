
import game.Dico;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lxm
 */
public class TestDico {
    public static void main(String[] args) {
        Dico monDico = new Dico("../../../Tux/src/tux/xml/dico.xml");
        //monDico.lireDictionnaireDOM("../../../Tux/src/tux/xml/dico.xml", "dico.xml");
        //用来测试dom是否可用，下面的add用来测试ajouteMotADico的随机提取功能
        

        // 向某些列表中添加单词
        monDico.ajouteMotADico(1, "pomme");
        monDico.ajouteMotADico(2, "banane");
        // 注意我们没有填满所有列表

        // 测试提取单词
        /*
        System.out.println("1级单词: " + monDico.getMotDepuisListeNiveau(1));
        System.out.println("2级单词: " + monDico.getMotDepuisListeNiveau(2));
        System.out.println("3级单词: " + monDico.getMotDepuisListeNiveau(3)); // 应该返回 "" 或 "Empty"
        */
        
        // 测试随机提取
        try{
            for(int i=0; i<5; i++){
                String mot = monDico.getMotDepuisListeNiveaux(i);
                System.out.println("Mot de niveau "+i+1+" "+mot+"\n");
            }   
        }catch(Exception e){
            System.out.println("erreur : "+e);
        }
    }
    
}
