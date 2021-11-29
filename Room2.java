import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import ansi_terminal.Terminal;

public class Room2 {
    // the grid holds the room geometry
    private String[] grid = new String[30];

    // the size of the room
    private int rows = 30;
    private int cols = 60;

    public Room2() {
        // the actual room geometry
        // the i cells refer to where an item should be placed at
        // & symbol is for the staircase
        grid[0] = "##################                ######################    ";
        grid[1] = "##   i          ##                ##                  ##    ";
        grid[2] = "##              ##                ##  &     *         ##    ";
        grid[3] = "##            * ##                ##                  ##    ";
        grid[4] = "####  ############                ##################  ##    ";
        grid[5] = "  ##  ##                                          ##  ##    ";
        grid[6] = "  ##  ##                                          ##  ##    ";
        grid[7] = "  ##  ##                                          ##  ##    ";
        grid[8] = "  ##  #######################################     ##  ##    ";
        grid[9] = "  ##                *               i      ##     ##  ##    ";
        grid[10] = "  ##  #########                            ##     ##  ##    ";
        grid[11] = "  ##  ##     ##                            ##     ##  ##    ";
        grid[12] = "  ##  ##     ##                            ##     ##  ##    ";
        grid[13] = "  ##  ##     ##                    *       #########  ##    ";
        grid[14] = "  ##  ##     ##    i                                  ##    ";
        grid[15] = "  ##  ##     ######################  ###################    ";
        grid[16] = "  ##  ##                         ##  ##                     ";
        grid[17] = "  ##  ##                         ##  ##                     ";
        grid[18] = "  ##  ##########                ##  ##                     ";
        grid[19] = "  ##          ##                ##  ##                     ";
        grid[20] = "  ##  i       ###########        ##  ##                     ";
        grid[21] = "  ##                   ##        ##  ##################     ";
        grid[22] = "  ##      *   #######  ##        ##                  ##     ";
        grid[23] = "  ##############   ##  ##        ##################  ##     ";
        grid[24] = "                #####  ##########                ##  ##     ";
        grid[25] = "                ##             ##      ############  ####   ";
        grid[26] = "                ##             ##########              ##   ";
        grid[27] = "                ##    @                        *       ##   ";
        grid[28] = "                ##             ##########              ##   ";
        grid[29] = "                ################       ##################   ";
    }

    //
    //    // returns the player's starting location in this room
    //    public Position getPlayerStart() {
    //        for (int row = 0; row < rows; row++) {
    //            for (int col = 0; col < cols; col++) {
    //                if ( grid[row].charAt(col) == '@') {
    //                    return new Position(row, col);
    //                }
    //            }
    //        }
    //        return null;
    //    }
    //
    //    // returns a set of item boxes for this map, this is here because it depends on
    //    // the room geometry for where the boxes make sense to be
    //    public ArrayList<Box> getBoxes() {
    //        ArrayList<Box> boxes = new ArrayList<>();
    //        for (int row = 0; row < rows; row++) {
    //            for (int col = 0; col < cols; col++) {
    //                if (grid[row].charAt(col) == 'i') {
    //                    boxes.add(new Box(row, col, ItemGenerator.instance().generate()));
    //                }
    //            }
    //        }
    //        return boxes;
    //    }
    //
    //    // returns a set of enemies from this map, similarly to the boxes above
    //    public ArrayList<Enemy> getEnemies() {
    //        ArrayList<Enemy> enemies = new ArrayList<>();
    //        for (int row = 0; row < rows; row++) {
    //            for (int col = 0; col < cols; col++) {
    //                if (grid[row].charAt(col) == '*') {
    //                    enemies.add(EnemyGenerator.instance().generate(row, col));
    //                }
    //            }
    //        }
    //        return enemies;
    //    }
    //
    //    // returns the warp point
    //    public ArrayList<Warp> getWarp() {
    //        ArrayList<Warp> warps = new ArrayList<>();
    //        for (int row = 0; row < rows; row++) {
    //            for (int col = 0; col < cols; col++) {
    //                if (grid[row].charAt(col) == '&') {
    //                    warps.add(new Warp(row, col));
    //                }
    //            }
    //        }
    //        return warps;
    //    }
    //
    //    public int getRows() {
    //        return rows;
    //    }
    //
    //    public int getCols() {
    //        return cols;
    //    }
    //
    //    // draws the map to the screen
    //    public void draw() {
    //        Terminal.clear();
    //        for (int row = 0; row < rows; row++) {
    //            for (int col = 0; col < cols; col++) {
    //                char cell = grid[row].charAt(col);
    //                if (cell == '#') {
    //                    // a unicode block symbol
    //                    System.out.print('\u2588');
    //                } else {
    //                    // whatever else, just draw a blank (we DON'T draw starting items from map)
    //                    System.out.print(' ');
    //                }
    //            }
    //            System.out.print("\n\r");
    //        }
    //    }
    //
    //    // returns if a given cell in the map is walkable or not
    //    public boolean canGo(int row, int col) {
    //        return grid[row].charAt(col) != '#';
    //    }
    //
    //    public void save(PrintWriter out) {
    //        for (String s : grid) {
    //            out.println(s);
    //        }
    //    }
    //
    //    public Room2(Scanner in) {
    //        while (in.hasNext()) {
    //            for (int i = 0; i < grid.length; i++) {
    //
    //                grid[i] = in.nextLine();        
    //            }
    //        }
    //    }
    //}

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
                boxes.add(new Box(row, col, ItemGenerator.instance().generate()));
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
                enemies.add(EnemyGenerator.instance().generate(row, col));
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
    for (int row = 0; row < 30; row++) {//changed rows to 30
        for (int col = 0; col < 60; col++) {//changed cols to 60
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

public Room2(Scanner in) {
    while (in.hasNext()) {
        for (int i = 0; i < grid.length; i++) {

            grid[i] = in.nextLine();
        }
    }
}
}                        
