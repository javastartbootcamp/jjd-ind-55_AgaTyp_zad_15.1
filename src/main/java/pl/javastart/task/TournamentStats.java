package pl.javastart.task;

import java.io.File;
import java.util.*;

public class TournamentStats {

    private static final int SORT_BY_NAME = 1;
    private static final int SORT_BY_LASTNAME = 2;
    private static final int SORT_BY_SCORE = 3;
    private static final int SORT_ASC = 1;
    private static final int SORT_DESC = 2;
    private ArrayList<Person> participantsList = new ArrayList<>();
    private File resultFile = new File("stats.csv");

    void run(Scanner scanner) {
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości
        loadParticipants(scanner);
        if (participantsList.size() > 0) {
            Comparator<Person> comparator = chooseSortingParameter(scanner);
            chooseOrderAndSort(scanner, comparator);
            writeStats();
        } else {
            System.out.println("Na liście nie ma uczestników.");
        }

    }

    private void writeStats() {
        WriteData.saveList(participantsList, resultFile);
    }

    private void loadParticipants(Scanner scanner) {
        String input = "";
        while (!Objects.equals(input.toUpperCase(), "STOP")) {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            input = scanner.nextLine();
            addPerson(input);

        }
    }

    private void chooseOrderAndSort(Scanner scanner, Comparator<Person> comparator) {
        System.out.printf("Sortować rosnąco czy malejąco? (%d - rosnąco, %d - malejąco)\n", SORT_ASC, SORT_DESC);
        switch (scanner.nextInt()) {
            case SORT_ASC:
                participantsList.sort(comparator);
                break;
            case SORT_DESC:
                participantsList.sort(comparator.reversed());
                break;
        }
    }

    private Comparator<Person> chooseSortingParameter(Scanner scanner) {
        System.out.printf("Po jakim parametrze posortować? (%d - imię, %d - nazwisko, %d - wynik)\n", SORT_BY_NAME, SORT_BY_LASTNAME, SORT_BY_SCORE);
        switch (scanner.nextInt()) {
            case SORT_BY_NAME:
                return new Person.FirstNameComparator();
            case SORT_BY_LASTNAME:
                return new Person.LastNameComparator();
            case SORT_BY_SCORE:
                return new Person.ScoreNameComparator();
        }
        return null;
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
