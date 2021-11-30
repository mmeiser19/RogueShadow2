// Character.java

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import ansi_terminal.*;

/** Manages Character-type entities
 *
 */
public abstract class Character extends Entity {

    // the character's health points
    protected int hp;

    public Character(int row, int col, char display, Color color, int hp) {
        super(row, col, display, color);
        this.hp = hp;
    }

    // get the hp, damage, protection and name of character
    public int getHealth() {
        return hp;
    }
    public abstract int getDamage();
    public abstract int getProtection();
    public abstract String getName();

    // damage to another player
    private boolean dealDamage(Character other, Room room) {
        // this character does damage to the other character
        int damageDone = getDamage() - other.getProtection();

        // prevent negative damage
        if (damageDone < 0) {
            damageDone = 0;
        }

        // actually damage them
        other.hp -= damageDone;

        // prevent negative hp
        if (other.hp < 0) {
            other.hp = 0;
        }

        // print the info on this
        Terminal.warpCursor(room.getRows(), 0);
        if (other.hp > 0) {
            System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                    + ", leaving " + other.hp + " health.\n\r");
            return false;
        } else {
            System.out.print(getName() + " does " + damageDone + " damage to " + other.getName()
                    + ", killing them.\n\r");
            return true;
        }
    }

    // performs one round of battle between 2 characters
    public boolean fight(Character other, Room room, ArrayList<Enemy> enemies) {
        // do damage to them first
        boolean killed = dealDamage(other, room);
        if (killed) {
            enemies.remove(other);
        }
        System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();

        // don't allow dead enemies to fight back
        if (killed) {
            return true;
        }

        // now take damage from them
        if (other.dealDamage(this, room)) {
            return false;
        }
        System.out.printf("Press any key to return...\n\r");
        Terminal.getKey();
        return true;
    }

    /** Resets the player's hp when they move to the next floor
     *
     */
    public void resetHP() {
        hp = 50;
    }

    /** Saves the character to the save file
     *
     * @param out the printwriter
     */
    public void save(PrintWriter out) {
        super.save(out);
        out.println(hp);
    }

    /** Loads the character from the save file
     *
     * @param in the scanner
     */
    public Character(Scanner in) {
        super(in);
        String temp = in.nextLine();
        hp = Integer.parseInt(temp);
    }
}
