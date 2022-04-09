package com.company;
import java.io.File;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceController {
    Scanner scanner;
    int option;
    Battle battle;
    File directoryPath;
    int counter;
    String[] list;
    TextController textController;
    String judgeNo;
    String competitorNo;
    Judge judge;
    int[] allScoreAddedUp;
    int[] stringAllAddedUp;
    ArrayList<Score> arrayList;
    ArrayList<Score> sortedArrayList;
    Score temp;

    public InterfaceController() {

    }

    public int battleConfirm(String selection, int number) {
        // confirms battle selection
        scanner = new Scanner(System.in);
        textController = new TextController();
        judge = new Judge();
        System.out.println("Join battle :" + selection + "?");
        System.out.println("1 for yes");
        System.out.println("2 for no");
        System.out.print("Selection :");
        try {
            option = scanner.nextInt();
            if (option == 1) {
                // selected the battle
                judgeNo = textController.getJudgeNo(selection);
                competitorNo = textController.getCompetitorNo(selection);
                judge.selectBattleInfo(Integer.parseInt(judgeNo), Integer.parseInt(competitorNo), selection);
                System.out.println("Battle finished!");
                startOptions();
            } else if (option == 2) {
                joinExistingBattle(number);
            } else if (option <= 0) {
                System.out.println("Invalid input Please try again");
                battleConfirm(selection, number);
            } else {
                System.out.println("Invalid input Please try again");
                battleConfirm(selection, number);
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input Please try again");
            battleConfirm(selection, number);
        }
        return 0;
    }

    public void selectBattle(String[] list, int number) {
        // select battle
        System.out.print("Selection :");
        try {
            scanner = new Scanner(System.in);
            option = scanner.nextInt();
            // if option is x then select list[x - 1]
            listConfirm(option, list, number);
        } catch (
                InputMismatchException e) {
            if (number == 1) {
                System.out.println("Invalid input Please try again");
                joinExistingBattle(number);
            }
        }
    }

    public void listConfirm(int option, String[] list, int number) {
        // selection for option to list
        if (option < list.length) {
            if (number == 1) {
                battleConfirm(list[option - 1], number);
            } else if (number == 2) {
                getJudgeScoreList(list[option - 1]);
            }

        } else if (option <= 0) {
            System.out.println("Invalid input Please try again");
            joinExistingBattle(number);
        } else {
            System.out.println("Invalid input Please try again");
            joinExistingBattle(number);
        }
    }


    public void startOptions() {
        // start of program
        battle = new Battle();
        textController = new TextController();
        System.out.println("Select 1 to create new battle");
        System.out.println("Select 2 to join existing battle");
        System.out.println("Select 3 to see Battle Scores");
        System.out.print("Selection :");
        try {
            scanner = new Scanner(System.in);

            option = scanner.nextInt();

            if (option == 1) {
                battle.createBattle();
            } else if (option == 2) {
                joinExistingBattle(1);
            } else if (option == 3) {
                joinExistingBattle(2);
            } else if (option <= 0) {
                System.out.println("Invalid input Please try again");
                startOptions();
            } else {
                System.out.println("Invalid input Please try again");
                startOptions();
            }
        } catch (
                InputMismatchException e) {
            System.out.println("Invalid input Please try again");
            startOptions();
        }
    }

    public void joinExistingBattle(int number) {
        // loads txt from folder and lists them.
        directoryPath = new File("C:\\Users\\davch\\IdeaProjects\\JudgeApp");
        counter = 1;
        for (File file : directoryPath.listFiles()) {
            if (file.getName().endsWith(".txt")) {
                counter++;
            }
        }
        list = new String[counter];
        int i = 0;
        for (File file : directoryPath.listFiles()) {
            if (file.getName().endsWith(".txt")) {
                list[i] = file.getName();
                System.out.println("Select " + (i + 1) + " for " + list[i]);
                i++;
            }
        }

        selectBattle(list, number);
    }

    public void getJudgeScoreList(String selection) {
        textController = new TextController();
        competitorNo = textController.getCompetitorNo(selection);
        allScoreAddedUp = new int[Integer.parseInt(competitorNo)];
        judgeNo = textController.getJudgeNo(selection);
        arrayList = new ArrayList<Score>();
        sortedArrayList = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(judgeNo); i++) {
            list = textController.getScoresByLine(selection, i);

            for (int j = 0; j < list.length; j++) {
                allScoreAddedUp[j] += Integer.parseInt(list[j]);
            }
        }
        // need to rank it using this method here using the score m
        for (int i = 0; i < allScoreAddedUp.length; i++) {
            arrayList.add(new Score(0, i + 1, allScoreAddedUp[i]));
        }
        temp = new Score();
        counter = 0;
        for (int i = 0; i < arrayList.size(); i++) {
                for (int x = i+1; x < arrayList.size(); x++) {
                    if (arrayList.get(i).getCompetitorScore() < arrayList.get(x).getCompetitorScore()){
                        temp = arrayList.get(i);
                        arrayList.set(i, arrayList.get(x));
                        arrayList.set(x, temp);
                    }


            }
        }

        for (int x = 0; x < arrayList.size(); x++) {
            arrayList.get(x).setCompetitorRank(x + 1);
        }

        rankList(arrayList);
    }

    public void rankList(ArrayList arrayList) {
        for (int x = 0; x < arrayList.size(); x++) {
            System.out.println(arrayList.get(x).toString());
        }

    }
}
