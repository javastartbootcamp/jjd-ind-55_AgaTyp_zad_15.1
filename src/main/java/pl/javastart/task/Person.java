package pl.javastart.task;

import java.util.Comparator;

public class Person implements Comparable<Person> {
    private String firsName;
    private String lastName;
    private int score;

    public Person(String firsName, String lastName, int score) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.score = score;
    }

    public static class FirstNameComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getFirsName().compareTo(p2.getFirsName());
        }
    }

    public static class LastNameComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getLastName().compareTo(p2.getLastName());
        }
    }

    public static class ScoreNameComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return Integer.compare(p1.getScore(), p2.getScore());
        }
    }

    @Override
    public String toString() {
        return firsName + ' ' +
                lastName + ';' +
                score + '\n';
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Person o) {
        if (score > o.score) {
            return 1;
        } else if (score < o.score) {
            return -1;
        }
        int lastNameCompare = lastName.compareTo(o.getLastName());
        if (lastNameCompare != 0) {
            return lastNameCompare;
        }
        return firsName.compareTo(o.getFirsName());
    }
}
