/*
 * Milestone 1
 * Description: a basic combat loop for a boxing match
 * Name: Mohammad Abdelrahman
 * ID: 920158652
 * Class: CSC 211-02
 * Semester: fall 2020
 */

import java.util.Scanner;

public class CombatLoop {
    public static int JohnCenaMaxHp = 100, JohnCenaCurrHp = 100, RandyOrtonMaxHp = 100, RandyOrtonCurrHp = 100;
    public static int JohnCenaMinDmg = 10, JohnCenaMaxDmg = 20, RandyOrtonMinDmg = 10, RandyOrtonMaxDmg = 15;
    public static String p1Name = "John Cena", p2Name = "Randy Orton";
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to WWE");
        String currPlayer = "None";

        int round = 1;
        while(JohnCenaCurrHp > 0 && RandyOrtonCurrHp > 0){ // keep the game on till one of the players die
            currPlayer = p1Name;
            if (round%2 == 0)
                currPlayer = p2Name;

                printHealthBar(JohnCenaCurrHp, p1Name);
                printHealthBar(RandyOrtonCurrHp, p2Name);

                System.out.printf("%s's turn.\n", currPlayer.toUpperCase());

                if (round%2 != 0) {
                    System.out.println("Select an Attack by entering 1-4.");
                    printAttack();
                    int attack = sc.nextInt();
                    RandyOrtonCurrHp = takeDmg(RandyOrtonCurrHp, useAttack(p1Name, attack,RandyOrtonCurrHp ));

                } else {

                    int dmg = calculateDmg(RandyOrtonMinDmg,RandyOrtonMaxDmg);
                    System.out.printf("%s attacks %s for %d damage!\n", p2Name.toUpperCase(), p1Name.toUpperCase(), dmg);
                    JohnCenaCurrHp = takeDmg(JohnCenaCurrHp, dmg);
                }

                round++;
        }
        if(JohnCenaCurrHp <= 0){
            System.out.println("RANDY ORTON Won The Fight!!");
        }
        else if(RandyOrtonCurrHp <= 0){
            System.out.println("JOHN CENA Won The Fight!!");
        }
    }
    // method to see what the player used to attack
    public static String getAttack(int attack) {
        switch(attack) {
            case 1:
                return "Jab";
            case 2:
                return "Cross";
            case 3:
                return "Uppercut";
            case 4:
                return "Pinfall";
        }

        return "000";
    }
    // method to print the different kinds of attack player can use
    public static void printAttack() {
        System.out.println("1.Jab\t\t3.Cross");
        System.out.println("2.Uppercut\t4.Pinfall");
    }
    // method to choose the attack and return's the damage of each attack
    public static int useAttack(String name, int attack, int p2currHp) {
        int dmg = 0;
        switch (attack) {
            case 1:
                dmg = calculateDmg(JohnCenaMinDmg, JohnCenaMaxDmg);
                break;
            case 2:
            case 3:
                dmg = calculateDmg(JohnCenaMinDmg+attack, JohnCenaMaxDmg+attack);
                break;
            case 4:
                if(p2currHp > 40) {
                    System.out.println("your opponent's health is over 40% can't apply Pinfall!");
                    dmg = 0;
                }
                else {
                    System.out.println("1..2..3 You won by Pinfall");
                    dmg = 100;
                }
                break;
            default:
                System.out.println("Your wrestler doesn't understand your attack!.");
                return 0;
        }

        System.out.printf("%s used %s for %d damage!\n",name.toUpperCase(), getAttack(attack).toUpperCase(), dmg);
        return dmg;
    }
    // method to calculate the damage for each player's min damage and max damage
    public static int calculateDmg(int min, int max) {
        return (int) (Math.random()*(max-min+1))+min; // return a random number between min and max inclusive
    }
    // method to subtract the damage taken from the players current health percentage
    public static int takeDmg(int currHp, int dmg) {
        return currHp-dmg;
    }
    // method to print the current health percentage of each player
    public static void printHealthBar(int currHp, String name) {
        System.out.print(name.toUpperCase());
        System.out.println("'s Health percentage: " + currHp + "%");
    }

}
