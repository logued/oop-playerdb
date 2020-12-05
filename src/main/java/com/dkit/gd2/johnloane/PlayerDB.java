package com.dkit.gd2.johnloane;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerDB
{
    private ArrayList<Player> players;
    private static Scanner keyboard = new Scanner(System.in);

    public PlayerDB()
    {
        this.players = new ArrayList<>();
    }

    public void loadPlayersFromFile()
    {
        try(Scanner playersFile = new Scanner(new BufferedReader(new FileReader("players.txt"))))
        {
            String input;
            while(playersFile.hasNextLine())
            {
                input = playersFile.nextLine();
                String[] data = input.split(",");
                String name = data[0];
                int hitPoints = Integer.parseInt(data[1]);
                int strength = Integer.parseInt(data[1]);
                String weapon = data[2];

                Player readInPlayer = new Player(name, hitPoints, strength, weapon);
                this.players.add(readInPlayer);
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println(Colours.PURPLE + "Could not load players." + "If this is the first time you are running the program this is fine." +Colours.RESET);
        }
    }

    public void savePlayersToFile()
    {
        try(BufferedWriter playersFile = new BufferedWriter(new FileWriter("players.txt") ))
        {
            for(Player player : players)
            {
                playersFile.write(player.getName() +","+player.getHitPoints()+","+player.getStrength()+","+player.getWeapon());
                playersFile.write("\n");
            }
        }
        catch(IOException ioe)
        {
            System.out.println(Colours.PURPLE + "Could not save students." +Colours.RESET);
        }
    }

    public static void printPlayerMainMenu()
    {
        System.out.println("\n Options to select:");
        for(int i=0; i < PlayerMainMenu.values().length;i++)
        {
            System.out.println("\t" + Colours.BLUE + i + ". " + PlayerMainMenu.values()[i].toString() + Colours.RESET);
        }
        System.out.println("Enter a number to select option (enter 0 to cancel):>");
    }

    public void addPlayer()
    {
        String name = enterField("name");
        int hitPoints = Integer.parseInt(enterField("hitpoints"));
        int strength = Integer.parseInt(enterField("strength"));
        String weapon = enterField("weapon");
        Player addition = new Player(name, hitPoints, strength, weapon);
        players.add(addition);
    }

    private String enterField(String field)
    {
        String input;
        System.out.println("Enter player " + field + ":> ");
        input = keyboard.nextLine();
        return input;
    }

    public void deletePlayer()
    {
        System.out.println("Enter name to delete:> ");
        String name = keyboard.nextLine();
        Player playerToRemove = findPlayer(name);
        players.remove(playerToRemove);
    }

    public void printPlayer()
    {
        System.out.println("Enter name to print:> ");
        String playerID = keyboard.nextLine();
        Player player = findPlayer(playerID);
        System.out.println(player);
    }

    private Player findPlayer(String name)
    {
        for(Player player : players)
        {
            if(player.getName().equals(name))
            {
                return player;
            }
        }
        return null;
    }


}
