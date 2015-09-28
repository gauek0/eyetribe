/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

//import static com.sun.glass.ui.Cursor.setVisible;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YafimZelm
 */
public class TETSimple {
    static Game game;

    public static void main(String[] args) {
        game = new Game();
        game.main1();
    }

    public static void restart() {
//       
      game.f.setVisible(false); //you can't see me!
      game.f.dispose();
      game.ed.setVisible(false);
      game.ed.dispose();
      game = new Game();
        game.main1();

    }
}
