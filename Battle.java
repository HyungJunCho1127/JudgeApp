package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Battle {
    Scanner scanner;
    int option;
    int judgeNo;
    int confirm;
    int competitors;
    String battleName;
    String battleNameConfirm;
    TextController textController;
    InterfaceController interfaceController;

    public Battle() {

    }

    public String battleName(){
        // type battle name
        System.out.print("Battle name? :");
        scanner = new Scanner(System.in);
        battleName = scanner.nextLine();
        try {
            battleNameConfirm = battleConfirm(battleName);

        } catch (
                InputMismatchException e) {
            System.out.println("Invalid input Please try again");
            battleName();
        }

        return battleName;
    }

    public String battleConfirm(String selection){
        // confirm the battle name
        scanner = new Scanner(System.in);
        System.out.println("Confirm battle name :" + selection + "?");
        System.out.println("1 for yes");
        System.out.println("2 for no");
        System.out.print("Selection :");
        try {
            option = scanner.nextInt();
            if (option == 1) {
                return selection;
            } else if (option == 2) {
                battleName();
            } else if (option == 0) {
                System.out.println("Invalid input Please try again");
                battleConfirm(selection);
            } else {
                System.out.println("Invalid input Please try again");
                battleConfirm(selection);
            }

        } catch (
                InputMismatchException e) {
            System.out.println("Invalid input Please try again");
            battleConfirm(selection);
        }
        return selection;
    }

    public int confirm(int number, String selection){
        // confirm for judge and competitors int values
        scanner = new Scanner(System.in);
        System.out.println("Confirm " + number + " " + selection + "?");
        System.out.println("1 for yes");
        System.out.println("2 for no");
        System.out.print("Selection :");
        try {
            option = scanner.nextInt();
            if (option == 1) {
                return number;
            } else if (option == 2) {
                createBattle();
            } else if (option == 0) {
                System.out.println("Invalid input Please try again");
                confirm(number, selection);
            } else {
                System.out.println("Invalid input Please try again");
                confirm(number, selection);
            }

        } catch (
                InputMismatchException e) {
            System.out.println("Invalid input Please try again");
            confirm(number, selection);
        }
        return number;
    }

    public void createBattle() {
        // create battle to txt file
        System.out.println("Create battle");
        textController = new TextController();
        interfaceController = new InterfaceController();
        judgeNo = howManyJudges();
        competitors = howManyCompetitors();
        battleName = battleName();
        System.out.println(battleName + " created with " + judgeNo + " judges and " + competitors + " competitors");
        textController.createBattleFile(battleName, judgeNo, competitors);
        interfaceController.startOptions();


    }

    public int howManyCompetitors(){
        // type how many competitors
        System.out.println("How many competitors?");
        System.out.print("Selection :");
        scanner = new Scanner(System.in);


        try {
            option = scanner.nextInt();
            if (option > 0) {
                competitors = confirm(option, "competitors");
            } else {
                System.out.println("Cannot have 0 competitors");
                howManyCompetitors();
            }
        } catch (
                InputMismatchException e) {
            System.out.println("Invalid input Please try again");

        }

        return competitors;
    }

    public int howManyJudges() {
        // type how many judges
        System.out.println("How many judges?");
        System.out.print("Selection :");
        scanner = new Scanner(System.in);

        try {
            option = scanner.nextInt();
            if (option > 0) {
                judgeNo = confirm(option, "judges");
            } else {
                System.out.println("Cannot have 0 judges");
                howManyJudges();
            }


        } catch (
                InputMismatchException e) {
            System.out.println("Invalid input Please try again");
            createBattle();
        }
        return judgeNo;
    }

}
