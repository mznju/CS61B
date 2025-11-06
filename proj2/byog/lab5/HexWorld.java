package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.*;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
class Position{
    int x;
    int y;
    public Position(int x,int y){
        this.x = x;
        this.y =y;
    }
}
public class HexWorld {
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){

        for (int i = 1; i <= s ; i++) {
            for(int m = p.x-i+1,n=p.y-2*s-i+2,j=1;j<=2*i-2+s;j++){
                world[m][n] = t;
                m++;
            }
        }

        for (int i = s+1; i <= 2*s ; i++) {
            for(int m = p.x+i-2*s,n=p.y-2*s+2-i,j=1;j<=5*s-2*i;j++){
                world[m][n] = t;
                m++;
            }
        }


    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(60, 30);

        // initialize tiles
        TETile[][] world = new TETile[60][30];
        for (int x = 0; x < 60; x += 1) {
            for (int y = 0; y < 30; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(world,new Position(10,20),4,Tileset.FLOWER);
        ter.renderFrame(world);

    }
}
