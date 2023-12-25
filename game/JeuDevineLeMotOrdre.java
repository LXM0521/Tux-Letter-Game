/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author lxm
 */
public class JeuDevineLeMotOrdre extends Jeu {
    private int nbLettresRestantes;
    private Chronometre chrono;
    
    public JeuDevineLeMotOrdre(){
        super();
    }
    
    private boolean tuxTrouveLettre(){
        if(!super.lettres.isEmpty()){
            return collision(super.lettres.get(0));
        }else{
            return false;
        }
        /*Letter premiereLetter = ListeLetter.get(0);// 获取列表中的第一个字母 如果开始使用这里的index应该成为一个变量？
        if(collision(premiereLetter)){
            return true; 
        }
        return false;*/
    }
    
    
    @Override
    protected void demarrePartie(Partie partie) {
       nbLettresRestantes=super.lettres.size();
       System.out.println(nbLettresRestantes);
       chrono = new Chronometre(30);
       chrono.start();
    }
    
    @Override
    protected void appliqueRegles(Partie partie){
        if(tuxTrouveLettre()) {
            super.lettres.remove(0);
            nbLettresRestantes--;
            System.out.println(nbLettresRestantes);
            partie.setPourcentageTrouve(getNbLettresRestantes());
        }
        /*if (this.chrono.remainsTime() && tuxTrouveLettre()) {
            // 减少剩余的字母数
            nbLettresRestantes--;
            // 检查是否所有的字母都已被找到
            if (nbLettresRestantes == 0) {
                // 如果是，游戏成功结束
                terminerPartie(partie);
                }
            // 如果还有剩余的字母，游戏继续
        } else if (!this.chrono.remainsTime()) {
            // 如果没有时间，无论 tux 是否找到字母，游戏失败结束
            terminerPartie(partie);
        // 如果还有时间但 tux 没有找到字母，不做任何操作，游戏继续
        }*/
    }
    
    @Override
    protected void terminerPartie(Partie partie) {
       chrono.stop();
       partie.setTemps(getTemps());
       super.lettres.clear();
       System.out.print(partie.toString());
       // 保存游戏结果
       //partie.setPourcentageTrouve(nbLettersRestantes);
    }
    
    @Override
    protected boolean fin() {
       return !chrono.remainsTime();
    }
    
    private int getNbLettresRestantes(){
        return nbLettresRestantes;
    }
    
    private int getTemps(){
        return (int)(chrono.getTime()/1000);
    }
    
}
