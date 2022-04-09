package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Judge {
    Scanner scanner;
    int userNo;
    int option;
    int confirm;
    TextController textController;

    public Judge(){

    }

    public void selectBattleInfo(int judgeNo, int competitorNo, String battleName){
        // select which no judge you are
        System.out.println("There are " + judgeNo + " judges for this Battle");
        System.out.println("Please select your judge #");
        System.out.print("Selection :");
        scanner = new Scanner(System.in);
        try {
        option = scanner.nextInt();
        if (option <= judgeNo && option > 0){
            userNo = confirm(judgeNo, option, competitorNo, battleName);
            judgeScore(userNo, competitorNo, battleName);
        } else if (option == 0) {
            System.out.println("Invalid input Please try again");
            selectBattleInfo(judgeNo, competitorNo, battleName);
        }
        else if ( option > judgeNo || option < 0) {
            System.out.println("Invalid input Please try again");
            selectBattleInfo(judgeNo, competitorNo, battleName);
        }
        } catch (
                InputMismatchException e) {
            System.out.println("Invalid input Please try again");
            selectBattleInfo(judgeNo, competitorNo, battleName);
        }

    }

    public int confirm(int judgeNo, int option, int competitorNo, String battleName){
        // confirm which no judge you are
        scanner = new Scanner(System.in);
        System.out.println("Confirm you are the #" + option + " Judge?");
        System.out.println("1 for yes");
        System.out.println("2 for no");
        System.out.print("Selection :");
        try {
            confirm = scanner.nextInt();
            if (confirm == 1) {
                return option;
            } else if (confirm == 2) {
                selectBattleInfo(judgeNo,competitorNo, battleName);
            } else if (option <= 0) {
                System.out.println("Invalid input Please try again");
                confirm(judgeNo, option,competitorNo, battleName);
            } else {
                System.out.println("Invalid input Please try again");
                confirm(judgeNo,option,competitorNo, battleName);
            }

        } catch (
                InputMismatchException e) {
            System.out.println("Invalid input Please try again");
            confirm(judgeNo,option,competitorNo, battleName);
        }
        return judgeNo;
    }

    public void judgeScore(int judgeNo, int competitorNo, String battleName){
        textController = new TextController();
        System.out.println("Battle Start!");
        textController.setScores(judgeNo,competitorNo,battleName);


    }
}
