package com.dkit.gd2.johnloane;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An application to manage players
 *
 */
public class PlayerDBApp
{
    private static Scanner keyboard = new Scanner(System.in);
    public static void main( String[] args )
    {
        PlayerDBApp playerManager = new PlayerDBApp();
        playerManager.start();
    }

    private void start()
    {
        System.out.println(Colours.BLUE + "Welcome to the player manager app");
        PlayerDB players = new PlayerDB();
        players.loadPlayersFromFile();
        doMainMenuLoop(players);
        players.savePlayersToFile();
    }

    private void doMainMenuLoop(PlayerDB players)
    {
        boolean loop = true;
        MainMenu menuOption;
        int option;
        while(loop)
        {
            printMainMenu();
            try
            {
                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = MainMenu.values()[option];
                switch(menuOption)
                {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case DISPLAY_PLAYER_MENU:
                        doPlayerMainMenuLoop(players);
                        break;
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
    }

    private void printMainMenu()
    {
        System.out.println("\n Options to select:");
        for(int i=0; i < MainMenu.values().length;i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + MainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select option (enter 0 to cancel):>");
    }

    private void doPlayerMainMenuLoop(PlayerDB players)
    {
        boolean loop = true;
        PlayerMainMenu menuOption;
        int option;
        while(loop)
        {
            PlayerDB.printPlayerMainMenu();
            try
            {
                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = PlayerMainMenu.values()[option];
                switch(menuOption)
                {
                    case QUIT_PLAYER_MENU:
                        loop = false;
                        break;
                    case ADD_PLAYER:
                        players.addPlayer();
                        break;
//                    case EDIT_PLAYER:
//                        players.editPlayer();
//                        break;
                    case DELETE_PLAYER:
                        players.deletePlayer();
                        break;
                    case PRINT_PLAYER:
                        players.printPlayer();
                        break;
                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println(Colours.RED + "Please enter a valid option" + Colours.RESET);
            }
        }
    }
}
