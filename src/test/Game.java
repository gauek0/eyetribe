/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.theeyetribe.client.GazeManager;
import com.theeyetribe.client.IGazeListener;
import com.theeyetribe.client.data.GazeData;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author dell
 */
public class Game {
 static int countCrim=0,countCrimHit=0,countHost=0,countHostHit=0, selfHits = 0;
    static double hostageTh = 0.7;
    static EndingDialog ed;
    public static JFrame f;
    public static JPanel p;
    static int bordersX[] = {-200 , 336 , 932 , 1500};
    static int bordersY[] = {-200 ,133 , 466 , 800};
    static int pos = -1;
   // public static MyButton[][] MatButtons;
    public static JButton[][] MatImages;
    public static int dim = 3;
    public Game(){
         countCrim=0;
         countCrimHit=0;
         countHost=0;
         countHostHit=0;
         selfHits = 0;
    }
    public static void main1()
{
   final GazeManager gm = GazeManager.getInstance();        
   boolean success = gm.activate(GazeManager.ApiVersion.VERSION_1_0, GazeManager.ClientMode.PUSH);
   
   final GazeListener gazeListener = new GazeListener();
    gm.addGazeListener(gazeListener);
      
        //int matrix[][] = new int[3][3];
      //  MatButtons = new MyButton[dim][dim]; 
      MatImages = new JButton[dim][dim];
      

        f = new JFrame("Window containing a matrix");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        p = new JPanel();
        p.setLayout(new GridLayout(dim, dim));
        BufferedImage buttonIcon = null;
        BufferedImage buttonIconHit = null;
        BufferedImage buttonIconHost = null;
        BufferedImage buttonIconHostHit = null;
        JPanel p2 = new JPanel();
   
//C:\Users\dell\Documents\NetBeansProjects\eyetribe\src\resImg\crimenal.jpg
            String ImageName = "C:\\Users\\dell\\Documents\\NetBeansProjects\\eyetribe\\src\\resImg\\crimenal.jpg";
           String ImageNameHit ="C:\\Users\\dell\\Documents\\NetBeansProjects\\eyetribe\\src\\resImg\\crimenalBlood.png";
            String ImageNameHost = "C:\\Users\\dell\\Documents\\NetBeansProjects\\eyetribe\\src\\resImg\\hostage2.jpg";
            String ImageNameHostHit = "C:\\Users\\dell\\Documents\\NetBeansProjects\\eyetribe\\src\\resImg\\hostage2Hit.jpg";

           // String ImageName = "/resImg/crimenal.jpg";
         //   String ImageNameHit ="/resImg/crimenalBlood";
       
     
        try {
            buttonIcon = ImageIO.read(new File(ImageName));
            buttonIconHit = ImageIO.read(new File(ImageNameHit));
             buttonIconHost = ImageIO.read(new File(ImageNameHost));
            buttonIconHostHit = ImageIO.read(new File(ImageNameHostHit));
            /// buttonIcon = ImageIO.read(TETSimple.class.getResourceAsStream(ImageName));
           /// buttonIconHit = ImageIO.read(TETSimple.class.getResourceAsStream(ImageNameHit));
        } catch (IOException ex) {
            Logger.getLogger(TETSimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        final ImageIcon crimenalHit = new ImageIcon(buttonIconHit);
        final ImageIcon crimenal = new ImageIcon(buttonIcon);
        final ImageIcon hostage = new ImageIcon(buttonIconHost);
        final ImageIcon hostageHit = new ImageIcon(buttonIconHostHit);

        for (int r = 0; r < dim; r++) {
            for (int c = 0; c < dim; c++) {
              //  MyButton button = new MyButton("1");
               // MatButtons[r][c] = button;
                JButton button = new JButton(new ImageIcon(buttonIcon));
                p.add(button);
                button.setVisible(false);
                MatImages[r][c] = button;
            }
        }
        f.add(p);
        f.pack();
        f.setVisible(true);
        Thread a = new Thread(){
            public void run(){
                while (selfHits<3 && countHostHit<3){
                try {
                    sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TETSimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                int c = (int)( Math.random()*dim)+0;
                int r = (int)( Math.random()*dim)+0;
                double rand = Math.random();
                boolean isHostage;
                boolean isHit = false;
                if (rand > hostageTh){
                    isHostage = true;
                }
                else isHostage = false;
                int localPos = -1;
                if (c == 0 && r == 0) localPos = 0;
                if (c == 0 && r == 1) localPos = 1;
                if (c == 0 && r == 2) localPos = 2;
                if (c == 1 && r == 0) localPos = 3;
                if (c == 1 && r == 1) localPos = 4;
                if (c == 1 && r == 2) localPos = 5;
                if (c == 2 && r == 0) localPos = 6;
                if (c == 2 && r == 1) localPos = 7;
                if (c == 2 && r == 2) localPos = 8;
                if (isHostage){
                    MatImages[c][r].setIcon(hostage);
                    countHost++;
                }
                else        {
                    MatImages[c][r].setIcon(crimenal);
                    countCrim++;
                }

                
                MatImages[c][r].setVisible(true);
                for (int i = 0 ; i < 20 ; i ++){
                    try {
                        sleep(100);
                        if (pos == localPos ){
                            //i = 100;
                              if (isHostage){
                                  MatImages[c][r].setIcon(hostageHit);
                                  countHostHit++;
                              }
                              else{
                                  MatImages[c][r].setIcon(crimenalHit);
                                   countCrimHit++;
                                   isHit = true;
                                   
                              }
                            sleep (200);
                            i = 100;

                            
                        }
                       
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TETSimple.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    MatImages[c][r].setVisible(false);
                    
               if(!isHostage&&!isHit){
                   selfHits++;
                   p.setBackground(Color.red);
                    try {
                        sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TETSimple.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    p.setBackground(Color.WHITE);
                   
               } 
                
            }
                
                ed = new EndingDialog();
                ed.setVisible(true);
                
            }
        };
        a.start();
        
    
}
    

    private static class GazeListener implements IGazeListener
{
    @Override
    public void onGazeUpdate(GazeData gazeData)
    {
        //System.out.println(gazeData.toString());
        System.out.println(gazeData.rawCoordinates.x + "    " +gazeData.rawCoordinates.y + "   " + gazeData.isFixated);
        //if(gazeData.rawCoordinates.x <366 && gazeData.rawCoordinates.x >-200 ){
        if (gazeData.isFixated && gazeData.isFixated){
        if(gazeData.rawCoordinates.x > bordersX[0] && gazeData.rawCoordinates.x < bordersX[1] ){
            if(gazeData.rawCoordinates.y > bordersY[0] && gazeData.rawCoordinates.y < bordersY[1] ){
                pos = 0;
                
            }
            else if(gazeData.rawCoordinates.y > bordersY[1] && gazeData.rawCoordinates.y < bordersY[2] ){
                pos = 3;
            }
            else if(gazeData.rawCoordinates.y > bordersY[2] && gazeData.rawCoordinates.y < bordersY[3] ){
                pos = 6;
            }
           
        }
         if(gazeData.rawCoordinates.x > bordersX[1] && gazeData.rawCoordinates.x < bordersX[2] ){
            if(gazeData.rawCoordinates.y > bordersY[0] && gazeData.rawCoordinates.y < bordersY[1] ){
                pos = 1;
            }
            else if(gazeData.rawCoordinates.y > bordersY[1] && gazeData.rawCoordinates.y < bordersY[2] ){
                pos = 4;
            }
            else if(gazeData.rawCoordinates.y > bordersY[2] && gazeData.rawCoordinates.y < bordersY[3] ){
                pos = 7;
            }
           
        }
          if(gazeData.rawCoordinates.x > bordersX[2] && gazeData.rawCoordinates.x < bordersX[3] ){
            if(gazeData.rawCoordinates.y > bordersY[0] && gazeData.rawCoordinates.y < bordersY[1] ){
                pos = 2;
            }
            else if(gazeData.rawCoordinates.y > bordersY[1] && gazeData.rawCoordinates.y < bordersY[2] ){
                pos = 5;
            }
            else if(gazeData.rawCoordinates.y > bordersY[2] && gazeData.rawCoordinates.y < bordersY[3] ){
                pos = 8;
            }
           
        }
        }
       

     
    


       
    }
    public static class MyButton extends Button{
        public MyButton(String label){
            super();
            this.setLabel(label);
        }
        public void ChangeNum(int num){
            this.setLabel(""+num);
            
        }
        
    }
    }   
}
