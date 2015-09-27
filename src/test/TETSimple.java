/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.theeyetribe.client.GazeManager;
import com.theeyetribe.client.GazeManager.ApiVersion;
import com.theeyetribe.client.GazeManager.ClientMode;
import com.theeyetribe.client.IGazeListener;
import com.theeyetribe.client.data.GazeData;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author YafimZelm
 */
public class TETSimple {
    public static JFrame f;
    static int bordersX[] = {-200 , 336 , 932 , 1500};
    static int bordersY[] = {-200 ,133 , 466 , 800};
    static int pos = -1;
   // public static MyButton[][] MatButtons;
    public static JButton[][] MatImages;
    public static int dim = 3;
    public static void main(String[] args)
{
   final GazeManager gm = GazeManager.getInstance();        
   boolean success = gm.activate(ApiVersion.VERSION_1_0, ClientMode.PUSH);
   
   final GazeListener gazeListener = new GazeListener();
    gm.addGazeListener(gazeListener);
      
        //int matrix[][] = new int[3][3];
      //  MatButtons = new MyButton[dim][dim]; 
      MatImages = new JButton[dim][dim];
      

        f = new JFrame("Window containing a matrix");
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(dim, dim));
        BufferedImage buttonIcon = null;
        String ImageName = "/Users/YafimZelm/NetBeansProjects/eyetribe1/src/resImg/crimenal.jpg";
        try {
            buttonIcon = ImageIO.read(new File(ImageName));
        } catch (IOException ex) {
            Logger.getLogger(TETSimple.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                while (true){
                try {
                    sleep(4000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TETSimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                int c = (int)( Math.random()*dim)+0;
                int r = (int)( Math.random()*dim)+0;
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
                
                
                MatImages[c][r].setVisible(true);
                for (int i = 0 ; i < 100 ; i ++){
                    try {
                        sleep(100);
                        if (pos == localPos ){
                            i = 100;
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TETSimple.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    MatImages[c][r].setVisible(false);
                    
                
                
            }
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

