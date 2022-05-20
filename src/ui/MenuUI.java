package ui;

import core.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MenuUI {

    public static void main(String[] args) {

        //System.out.println("Hello world!");
        System.out.println("Please enter the number of players");
        Scanner userInput = new Scanner(System.in);
        int playerNumber = userInput.nextInt();
        System.out.println("User entered: " + playerNumber);
        userInput.nextLine();
        Player[] players = new Player[playerNumber];
        int playerIndex = 0;
        int turnNumber = 0;
        boolean quit = false;
        while(!quit) {
            displayMenuOptions();
            System.out.println("Please enter option");
            int enteredNumber = userInput.nextInt();
            userInput.nextLine();
            switch(enteredNumber) {
                case 1 : {
                    System.out.println("1. List all players");
                    for(int i = 0; i < playerIndex; i++) {
                        System.out.println(players[i].toString());
                    }
                    break;
                }
                case 2 : {
                    System.out.println("Enter player name");
                    String temp = userInput.nextLine();
                    players[playerIndex] = new Player(temp, 20);
                    playerIndex++;
                    break;
                }
                case 3 : {
                    System.out.println("3. Load player list");
                    break;

                }
                case 4 : {
                    System.out.println("Enter player name");
                    String tempName = userInput.nextLine();
                    boolean found = false;
                    for(int i = 0; i < playerIndex; i++) {
                        if(tempName.compareToIgnoreCase(players[i].getPlayerName()) == 0) {
                            found = true;
                            players[i].performAttack(turnNumber);
                            turnNumber++;
                        }
                    }
                    if(!found) {
                        System.out.println("No user with that name");
                    }
                    break;
                }
                case 5 : {
                    System.out.println("Enter player name");
                    String tempName = userInput.nextLine();
                    boolean found = false;
                    for(int i = 0; i < playerIndex; i++) {
                        if(tempName.compareToIgnoreCase(players[i].getPlayerName()) == 0) {
                            found = true;
                            System.out.println("Enter item name");
                            String tempItemName = userInput.nextLine();
                            players[i].useItem(turnNumber, tempItemName);
                            turnNumber++;
                        }
                    }
                    if(!found) {
                        System.out.println("No user with that name");
                    }
                    break;
                }
                case 6 : {
                    System.out.println("6. Find highest roll");
                    int pIndex = -1;
                    int highestIndex = -1;
                    int highestRoll = -1;
                    for(int j = 0; j < playerIndex; j++) {
                        for(int i = 0; i < players[j].getPlayerActions().length; i++) {
                            if(players[j].getRoll(i) != -1) {
                                if(players[j].getRoll(i) > highestRoll) {
                                    pIndex = j;
                                    highestIndex = i;
                                    highestRoll = players[j].getRoll(i);
                                }
                            }
                        }
                    }
                    System.out.println("Highest roll performed by: " + players[pIndex].getPlayerName());
                    System.out.println(players[pIndex].getPlayerAction(highestIndex).toString());
                    break;

                }
                case 7 : {
                    System.out.println("7. Quit");
                    quit = true;
                    break;
                }

            }

        } // end of while loop
        System.out.println("Menu successfully quit");
        try(FileWriter fileWriter = new FileWriter(new File("player_list.txt"));
            BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write("Player List");
            writer.write('\n');
            for(int i = 0; i < playerIndex; i++) {
                writer.write(players[i].toString());
                writer.write('\n');
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void displayMenuOptions() {
        System.out.println("===Menu===");
        System.out.println("1. List all players");
        System.out.println("2. Add a player");
        System.out.println("3. Load player list");
        System.out.println("4. Make player attack");
        System.out.println("5. Player use item");
        System.out.println("6. Find highest roll");
        System.out.println("7. Quit");
    }

}
