// Game.java
// contains logic for running the Game

import java.util.ArrayList;
import ansi_terminal.*;
import java.util.Scanner;

public class Game {
    private Room room;
    private Player player;
    private ArrayList<Box> boxes;
    private ArrayList<Enemy> enemies;

    private String name = "Hero";

    public Game() {
        room = new Room();
        player = new Player(room.getPlayerStart());
        boxes = room.getBoxes();
        enemies = room.getEnemies();
    }

    // this method prints the games plot
    protected void storyPlot() {
        // clearing the terminal screen
        for (int i = 0; i < 100; i++) {
            Terminal.warpCursor(i, 0);
            System.out.print("                                                                                                                               ");
        }

        // printing the games name
        // code generated from www.coolgenerator.com/ascii-text-generator   font used: ANSI shadow
        Terminal.warpCursor(6, 26);
        System.out.print("██████╗  ██████╗  ██████╗ ██╗   ██╗███████╗    ███████╗██╗  ██╗ █████╗ ██████╗  ██████╗ ██╗    ██╗");
        Terminal.warpCursor(7, 26);
        System.out.print("██╔══██╗██╔═══██╗██╔════╝ ██║   ██║██╔════╝    ██╔════╝██║  ██║██╔══██╗██╔══██╗██╔═══██╗██║    ██║");
        Terminal.warpCursor(8, 26);
        System.out.print("██████╔╝██║   ██║██║  ███╗██║   ██║█████╗      ███████╗███████║███████║██║  ██║██║   ██║██║ █╗ ██║");
        Terminal.warpCursor(9, 26);
        System.out.print("██╔══██╗██║   ██║██║   ██║██║   ██║██╔══╝      ╚════██║██╔══██║██╔══██║██║  ██║██║   ██║██║███╗██║");
        Terminal.warpCursor(10, 26);
        System.out.print("██║  ██║╚██████╔╝╚██████╔╝╚██████╔╝███████╗    ███████║██║  ██║██║  ██║██████╔╝╚██████╔╝╚███╔███╔╝");
        Terminal.warpCursor(11, 26);
        System.out.print("╚═╝  ╚═╝ ╚═════╝  ╚═════╝  ╚═════╝ ╚══════╝    ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝  ╚═════╝  ╚══╝╚══╝ ");


        // printing the stories plot
        Terminal.warpCursor(14, 36);
        System.out.print("*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*^*");
        Terminal.warpCursor(15, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(16, 36);//40
        System.out.print("*   You the adventurer must set forth into the dark world of Rogue Shadow.  *");
        Terminal.pause(1.5);
        Terminal.warpCursor(17, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(18, 36);//38
        System.out.print("* A land of mystical wonder and danger presents itself around every corner. *");
        Terminal.pause(1.5);
        Terminal.warpCursor(19, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(20, 36);//38
        System.out.print("* The goal of the game is to survive and collect as many items as possible. *");
        Terminal.pause(1.5);
        Terminal.warpCursor(21, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(22, 36);//46
        System.out.print("*         Beware of the dungeons enemies or you will surely perish!         *");
        Terminal.warpCursor(23, 36);
        System.out.print("<                                                                           >");
        Terminal.warpCursor(24, 36);
        System.out.print("*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*v*");
    }
    
    // sets the name of the player during the title screen
    protected void setName() {
        boolean done = false;
        Terminal.warpCursor(26, 58);
        System.out.print("What is your name adventurer? ");
        //Changed the way the program reads the player name in so that the line stays in positon
        Scanner in = new Scanner(System.in);
        Terminal.warpCursor(26, 88);
        name = in.next();
    }


    // prints a help menu to the left of the map
    private void showHelp() {
        String[] cmds = {"Commands:",
            "---------",
            "Move: Arrow Keys",
            "Pickup an item: p",
            "Drop an item: d",
            "List items: l",
            "Equip weapon: w",
            "Equip armor: a",
            "Quit: q"
        };
        Terminal.setForeground(Color.GREEN);
        for (int row = 0; row < cmds.length; row++) {
            Terminal.warpCursor(row + 1, room.getCols());
            System.out.print(cmds[row]);
        }
        Terminal.reset();
    }

    // right under the map we keep a line for status messages
    private void setStatus(String mesg) {
        // clear anything old first
        Terminal.warpCursor(room.getRows(), 0);
        for (int i = 0; i < 100; i++) {
            System.out.print(" ");
        }

        // then print the message
        Terminal.warpCursor(room.getRows(), 0);
        System.out.print(mesg);
    }

    // code for when the player tries to pickup an item
    private void pickup() {
        Box thing = checkForBox();
        if (thing == null) {
            setStatus("There is nothing here to pick up...");
            Terminal.pause(1.25);
        } else {
            if (player.getInventory().add(thing.getItem())) {
                setStatus("You added the " + thing.getItem().getName() + " to your inventory.");
                boxes.remove(thing);
            } else {
                setStatus("This is too large for you to add!");
            }
            Terminal.pause(1.25);
        }
    }

    // code for when the player tries to drop an item
    private void drop() {
        if (checkForBox() == null) {
            Item dropped = player.getInventory().drop();
            if (dropped != null) {
                boxes.add(new Box(player.getRow(), player.getCol(), dropped));
            }
            redrawMapAndHelp();
        } else {
            setStatus("You cannot drop something on an existing item...");
            Terminal.pause(1.25);
        }
    }

    // handle the key which was read - return false if we quit the game
    private boolean handleKey(Key key) {
        switch (key) {
            case p:
                pickup();
                break;

            case l:
                player.getInventory().print();
                redrawMapAndHelp();
                break;

            case d:
                drop();
                break;

            case w:
                player.getInventory().equipWeapon();
                redrawMapAndHelp();
                break;

            case a:
                player.getInventory().equipArmor();
                redrawMapAndHelp();
                break;

                // handle movement
            case LEFT: player.move(0, -1, room);
                       break;
            case RIGHT: player.move(0, 1, room);
                        break;
            case UP: player.move(-1, 0, room);
                     break;
            case DOWN: player.move(1, 0, room);
                       break;

                       // and finally the quit command
            case q:
                       return false;
        }

        return true;
    }

    // this is called when we need to redraw the room and help menu
    // this happens after going into a menu like for choosing items
    private void redrawMapAndHelp() {
        room.draw();
        showHelp();
    }

    // returns a Box if the player is on it -- otherwise null
    private Box checkForBox() {
        Position playerLocation = player.getPosition();

        for (Box box : boxes) {
            if (playerLocation.equals(box.getPosition())) {
                return box;
            }
        }

        return null;
    }

    // check for battles and return false if player has died
    private boolean checkBattles() {
        Position playerLocation = player.getPosition();

        // look for an enemy that is close
        Enemy opponent = null;
        for (Enemy enemy : enemies) {
            if (playerLocation.isAdjacent(enemy.getPosition())) {
                opponent = enemy;
            }
        }

        // now do the battle
        if (opponent != null) {
            opponent.setBattleActive();
            return player.fight(opponent, room, enemies);
        }

        return true;
    }

    public void run() {
        // draw these for the first time now
        redrawMapAndHelp();

        boolean playing = true;
        while (playing) {
            // draw the entities
            for (Box box : boxes) {
                box.draw();
            }
            for (Enemy enemy : enemies) {
                enemy.draw();
            }
            player.draw();

            // read a key from the user
            Terminal.warpCursor(room.getRows() + 1, 0);
            Key key = Terminal.getKey();
            playing = handleKey(key);

            // clear status by default
            setStatus("");

            // move the enemies
            for (Enemy enemy : enemies) {
                enemy.walk(room);
            }

            // check for battles
            if (!checkBattles()) {
                setStatus("You have been killed :(\n\r");
                playing = false;
            }

            // check if we are on a box and print what's in it
            Box thingHere = checkForBox();
            if (thingHere != null) {
                setStatus("Here you find: " + thingHere.getItem().getName());
            }
        }
    }
}
