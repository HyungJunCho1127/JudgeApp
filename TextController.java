package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TextController {
    File file;
    FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    BufferedReader bufferedReader;
    File battleTextFile;
    Scanner scanner;
    String number;
    String word;
    String filePath;
    String[] scoreList;
    int line;
    int score;
    int counter;
    String scoreLine;
    String newScoreLine;

    public TextController() {

    }

    public void createBattleFile(String battleName, int judges, int competitors) {
        //handles creating new text file
        file = new File(battleName + ".txt");
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(battleName);
            bufferedWriter.newLine();
            bufferedWriter.write("Judges");
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(judges));
            bufferedWriter.newLine();
            bufferedWriter.write("Competitors");
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(competitors));
            for (int i = 0; i < judges; i++) {
                bufferedWriter.newLine();
                bufferedWriter.write("Line " + (i + 1));
                bufferedWriter.newLine();
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getJudgeNo(String fileName) {
        //gets judge no from txt
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            while (bufferedReader.readLine() != "Judges") {
                word = bufferedReader.readLine();
                if (word.equals("Judges")) {
                    number = bufferedReader.readLine();
                    return number;
                }
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return number;

    }

    public String getCompetitorNo(String fileName) {
        //gets competitor no from txt
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            while (bufferedReader.readLine() != "Competitors") {
                word = bufferedReader.readLine();
                if (word.equals("Competitors")) {
                    number = bufferedReader.readLine();
                    bufferedReader.close();
                    return number;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return number;

    }

    public void setScores(int judgeNo, int competitorNo, String battleName) {
        //sets scores into text
        counter = 0;
        filePath = "C:\\Users\\davch\\IdeaProjects\\JudgeApp\\" + battleName;
        scoreList = new String[competitorNo];
        scanner = new Scanner(System.in);
        line = ((judgeNo * 2) + 4);
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
            // maybe change this to while. and then change this method into a recursive method?
            while (counter < competitorNo) {
                score = scoreInput(counter);
                scoreList[counter] = Integer.toString(score);
                counter++;
            }

            System.out.println(Arrays.toString(scoreList));
            fileContent.set(line, Arrays.toString(scoreList));
            Files.write(Path.of(filePath), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Invalid input Please try again");
            e.printStackTrace();

        }
    }

    public int scoreInput(int counter) {
        scanner = new Scanner(System.in);
        try {
            System.out.println("Competitor :" + (counter + 1));
            System.out.print("Score :");
            score = scanner.nextInt();
            if (score <= 10 && score > 0) {
                return score;
            } else  {
                System.out.println("Score must be from 1 to 10");
                scoreInput(counter);
            }
            } catch(InputMismatchException e){
                System.out.println("Invalid input Please try again");
            scoreInput(counter);
            }
        return score;
        }

        public String[] getScoresByLine(String battleName, int judgeNo){
            filePath = "C:\\Users\\davch\\IdeaProjects\\JudgeApp\\" + battleName;
            line = ((judgeNo * 2) + 6);
            try {
                List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
                scoreLine = Files.readAllLines(Paths.get(filePath)).get(line);
            } catch (IOException e) {

            }
            newScoreLine = scoreLine.replace("[", "").replace("]", "").replace(" ", "");
            scoreList = newScoreLine.split(",");
            return scoreList;
        }
    }



