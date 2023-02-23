package pl.javastart.task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteData {
    public static void saveList(ArrayList<Person> participantsList, File participantsFile) {
        try {
            FileWriter fileWriter = new FileWriter(participantsFile);
            for (Person participant : participantsList) {
                fileWriter.write(participant.toString());
            }
            fileWriter.close();
            System.out.println("Dane posortowano i zapisano do pliku stats.csv.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
