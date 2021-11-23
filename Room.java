// Room.java
// provides code for the drawing of a room
// also provides starting locations for the player, boxes, and enemies

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import ansi_terminal.Terminal;

//Testing
//import java.util.Arrays;
import java.lang.reflect.Array;

public class Room {

    // the grid holds the room geometry
    private String[] grid;
    //private String[] grid = new String[30];

    // the size of the room
    private int rows = 30;
    private int cols = 60;

    public Room() {
        // this initializes the room to one specific space
        rows = 30;
        cols = 60;

        // the actual room geometry
        // the i cells refer to where an item should be placed at
        grid  = new String[] {
                "##################                ######################    ",
                "##              ##                ##      i           ##    ",
                "##  @           ###########       ##        *         ##    ",
                "##                       ##       ##                  ##    ",
                "##              #######  ##       ##################  ##    ",
                "##              ##   ##  ##                       ##  ##    ",
                "##################   ##  ##################       ##  ##    ",
                "                     ##                  ##       ##  ##    ",
                "                     ##   *  i           ##       ##  ##    ",
                "                     ##                  ##       ##  ##    ",
                "                     ##############  ######       ##  ##    ",
                "                                 ##  ##           ##  ##    ",
                "                                 ##  ##           ##  ##    ",
                "                       ############  ###############  ######",
                "                       ##                                 ##",
                "                       ##     *                           ##",
                "    #####################                  *              ##",
                "    ##                                                    ##",
                "    ##  #################                          i      ##",
                "    ##  ##             ##                                 ##",
                "    ##  ##             #################  ##################",
                "    ##  ##                            ##  ##                ",
                "    ##  ##                            ##  ##                ",
                "    ##  ##                       #######  #######           ",
                "    ##  ##                       ##            ##           ",
                "######  ####                     ##  i  *      ##           ",
                "##        ##                     ##         &  ##           ",
                "## i  *   ##                     ################           ",
                "##        ##                                                ",
                "############                                                "
        };
    }

    // returns the player's starting location in this room
    public Position getPlayerStart() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if ( grid[row].charAt(col) == '@') {
                    return new Position(row, col);
                }
            }
        }

        return null;
    }

    // returns a set of item boxes for this map, this is here because it depends on
    // the room geometry for where the boxes make sense to be
    public ArrayList<Box> getBoxes() {
        ArrayList<Box> boxes = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == 'i') {
                    //Item i = ItemGenerator.instance().generate();
                    Item i = ItemGenerator.generate();
                    boxes.add(new Box(row, col, i ));
                }
            }
        }

        return boxes;
    }

    // returns a set of enemies from this map, similarly to the boxes above
    public ArrayList<Enemy> getEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '*') {
                    enemies.add(EnemyGenerator.generate(row, col));
                }
            }
        }
        return enemies;
    }

    // returns the warp point
    public ArrayList<Warp> getWarp() {
        ArrayList<Warp> warps = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row].charAt(col) == '&') {
                    warps.add(new Warp(row, col));
                }
            }
        }
        return warps;
    }


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // draws the map to the screen
    public void draw() {
        Terminal.clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char cell = grid[row].charAt(col);
                if (cell == '#') {
                    // a unicode block symbol
                    System.out.print('\u2588');
                } else {
                    // whatever else, just draw a blank (we DON'T draw starting items from map)
                    System.out.print(' ');
                }
            }

            System.out.print("\n\r");
        }
    }

    // returns if a given cell in the map is walkable or not
    public boolean canGo(int row, int col) {
        return grid[row].charAt(col) != '#';
    }

    public void save(PrintWriter out) {
        for (String s : grid) {
            out.println(s);
        }
    }

    public Room(Scanner in) {
        grid = new String[30];
        while (in.hasNext()) {
            for (int i = 0; i < grid.length; i++) { // changed from i < rows
                
                //Test
                // bad test will cause an infinite loop
                //Terminal.warpCursor(40,0);
                //System.out.print("The field grid in Room.java "+this.grid.toString()+"\n\r");
                //Terminal.pause(2);
                
                //grid[i] = in.nextLine();
                String temp = in.nextLine();
                Array.set(grid,i,temp);

                //This way wont work because it is only for a range or all elements but not idividual
                //Arrays.fill(grid,temp);
            }
            // more testing
                Terminal.warpCursor(40,0);
                System.out.print("The field grid in Room.java "+grid.toString()+"\n\r");
                Terminal.pause(2);
            
        }
    }
    
    //**** used for testing
    // REMOVE BEFORE SUBMITTING
    public String toString() {
        for (int i = 0; i < grid.length; i++) {
            Terminal.warpCursor(40,0);
            System.out.print(grid[i]);
        }
        String placeHolder = "";
        return placeHolder;
    }
}
