package tasks.level.two.main;

import tasks.level.two.algorithm.PassingAlgorithm;


public class Main {

    public static final String FILE_NAME = "src/main/java//tasks/level/two/resources/INPUT.txt";

    public static void main(String arg[]) {

        PassingAlgorithm passingAlgorithm = new PassingAlgorithm();
        passingAlgorithm.passingAlgorithm(FILE_NAME);
    }
}
