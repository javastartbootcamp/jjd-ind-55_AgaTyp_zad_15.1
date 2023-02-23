package pl.javastart.task;

import java.io.File;
import java.util.*;

public class TournamentStats {

    private ArrayList<Person> participantsList = new ArrayList<>();
    private File resultFile = new File("stats.csv");

    void run(Scanner scanner) {
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości
        String input = "";
        while (!Objects.equals(input.toUpperCase(), "STOP")) {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            input = scanner.nextLine();
            addPerson(input);

        }
        if (participantsList.size() > 0) {
            chooseSortingParameter(scanner);
            chooseOrderAndWriteStats(scanner);
        } else {
            System.out.println("Na liście nie ma uczestników.");
        }

    }

    private void chooseOrderAndWriteStats(Scanner scanner) {
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        switch (scanner.nextInt()) {
            case 1:
                WriteData.saveList(participantsList, resultFile);
                break;
            case 2:
                Collections.reverse(participantsList);
                WriteData.saveList(participantsList, resultFile);
                break;
        }
    }

    private void chooseSortingParameter(Scanner scanner) {
        System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");
        switch (scanner.nextInt()) {
            case 1:
                participantsList.sort(new Person.FirstNameComparator());
                break;
            case 2:
                participantsList.sort(new Person.LastNameComparator());
                break;
            case 3:
                participantsList.sort(new Person.ScoreNameComparator());
                break;
        }
    }

    private void addPerson(String input) {
        String[] personLine = input.split(" ");
        if (personLine.length == 3) {
            String firstName = personLine[0];
            String lastName = personLine[1];
            int score = Integer.parseInt(personLine[2]);

            participantsList.add(new Person(firstName, lastName, score));
        } else if (!input.equalsIgnoreCase("STOP")) {
            System.out.println("Podano niepoprawne dane.");
        }

    }
}
