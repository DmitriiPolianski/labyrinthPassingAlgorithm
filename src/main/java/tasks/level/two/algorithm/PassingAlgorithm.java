package tasks.level.two.algorithm;

import java.util.ArrayList;

public class PassingAlgorithm {

    public void passingAlgorithm(String fileName) {
        int h, m, n;
        ArrayList<Integer> labyrinthSize = new ArrayList<Integer>();
        StringBuilder labyrinthString = new StringBuilder("");

        InputLabyrinthFromTxt labyrinthInput = new InputLabyrinthFromTxt();

        labyrinthInput.input(fileName, labyrinthSize, labyrinthString);

        h = labyrinthSize.get(0); // count of levels
        m = labyrinthSize.get(1); // count of strings
        n = labyrinthSize.get(2); // count of columns

        int[][][] labyrinthInt = new int[h][m][n];

        labyrinthFromStringToInt(labyrinthString, labyrinthInt, h, m, n);
        printIntLabyrinth(labyrinthInt, h, m, n);
        getPath(labyrinthInt, h, m, n);
        getPathTime(labyrinthInt, h, m, n);

    }

    private void printIntLabyrinth(int[][][] labyrinthInt, int h, int m, int n) {
        System.out.println("labyrinth have " + h + " levels. Each level size " + m + "x" + n);
        System.out.println("1-Prince position, 2-Princes position, 3-wall,0-free space");
        System.out.println("Labyrinth:");
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.print(" " + labyrinthInt[i][j][k]);
                }
                System.out.print("\n");
            }
            System.out.print("\n");
        }
    }

    private void labyrinthFromStringToInt(StringBuilder labyrinthString, int[][][] labyrinthInt, int h, int m, int n) {
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    if (labyrinthString.charAt(0) == '1') {      //1-Prince position
                        labyrinthInt[i][j][k] = 1;
                        labyrinthString.deleteCharAt(0);
                    } else if (labyrinthString.charAt(0) == '2') { //2-Princes position
                        labyrinthInt[i][j][k] = 2;
                        labyrinthString.deleteCharAt(0);
                    } else if (labyrinthString.charAt(0) == '.') { //0-free space
                        labyrinthInt[i][j][k] = 0;
                        labyrinthString.deleteCharAt(0);
                    } else if (labyrinthString.charAt(0) == 'o') { //3-column
                        labyrinthInt[i][j][k] = 3;
                        labyrinthString.deleteCharAt(0);
                    } else {
                        System.out.println("invalid input");
                        System.exit(-1);
                    }
                }
                labyrinthString.deleteCharAt(0);
            }
        }
    }

    private void getPath(int[][][] labyrinthInt, int h, int m, int n) {
        int iStop = 0, jStop = 0, kStop = 0, movementCount = -1;
        boolean moveIsMade = false;
        if (labyrinthInt[0][0][1] == 0) {
            labyrinthInt[0][0][1] = -1;
        }
        if (labyrinthInt[0][1][0] == 0) {
            labyrinthInt[0][1][0] = -1;
        }
        if (labyrinthInt[1][0][0] == 0) {
            labyrinthInt[1][0][0] = -1;
        }

        while (true) {

            for (int i = 0; i < h; ++i) {
                for (int j = 0; j < m; ++j) {
                    for (int k = 0; k < n; ++k) {
                        if (labyrinthInt[i][j][k] == movementCount & (iStop != i | jStop != j | kStop != k)) {
                            iStop = i;
                            jStop = j;
                            kStop = k;

                            if (iStop + 1 < h)
                                if (labyrinthInt[iStop + 1][jStop][kStop] <= 0 | labyrinthInt[iStop + 1][jStop][kStop] < movementCount - 1) {
                                    if (labyrinthInt[iStop + 1][jStop][kStop] == 0) {
                                        labyrinthInt[iStop + 1][jStop][kStop] = movementCount - 1;
                                        moveIsMade = true;
                                    }
                                }

                            if (jStop + 1 < m)
                                if (labyrinthInt[iStop][jStop + 1][kStop] <= 0 | labyrinthInt[iStop][jStop + 1][kStop] < movementCount - 1) {
                                    if (labyrinthInt[iStop][jStop + 1][kStop] == 0) {
                                        labyrinthInt[iStop][jStop + 1][kStop] = movementCount - 1;
                                        moveIsMade = true;
                                    }
                                }

                            if (jStop - 1 >= 0)
                                if (labyrinthInt[iStop][jStop - 1][kStop] <= 0 | labyrinthInt[iStop][jStop - 1][kStop] < movementCount - 1) {
                                    if (labyrinthInt[iStop][jStop - 1][kStop] == 0) {
                                        labyrinthInt[iStop][jStop - 1][kStop] = movementCount - 1;
                                        moveIsMade = true;
                                    }
                                }

                            if (kStop + 1 < n)
                                if (labyrinthInt[iStop][jStop][kStop + 1] <= 0 | labyrinthInt[iStop][jStop][kStop + 1] < movementCount - 1) {
                                    if (labyrinthInt[iStop][jStop][kStop + 1] == 0) {
                                        labyrinthInt[iStop][jStop][kStop + 1] = movementCount - 1;
                                        moveIsMade = true;
                                    }
                                }

                            if (kStop - 1 >= 0)
                                if (labyrinthInt[iStop][jStop][kStop - 1] <= 0 | labyrinthInt[iStop][jStop][kStop - 1] < movementCount - 1) {
                                    if (labyrinthInt[iStop][jStop][kStop - 1] == 0) {
                                        labyrinthInt[iStop][jStop][kStop - 1] = movementCount - 1;
                                        moveIsMade = true;
                                    }
                                }
                        }

                    }
                }
            }

            --movementCount;
            if (labyrinthInt[h - 1][m - 1][n - 2] < 0 | labyrinthInt[h - 1][m - 2][n - 1] < 0 | labyrinthInt[h - 2][m - 1][n - 1] < 0) {
                break;
            } else if (moveIsMade) {
                moveIsMade = false;
            } else {
                System.out.println("the labyrinth has no exit");
                System.exit(-1);
            }
        }

    }

    private void getPathTime(int[][][] labyrinthInt, int h, int m, int n) {
        int minPath, pathTime = 0;
        if (labyrinthInt[h - 1][m - 1][n - 2] < 0) {
            minPath = Math.abs(labyrinthInt[h - 1][m - 1][n - 2]) + 1;
        } else if (labyrinthInt[h - 1][m - 2][n - 1] < 0) {
            minPath = Math.abs(labyrinthInt[h - 1][m - 2][n - 1]) + 1;
        } else {
            minPath = Math.abs(labyrinthInt[h - 2][m - 1][n - 1]) + 1;
        }
        pathTime = minPath * 5;
        System.out.println("Prince found the Princess for " + pathTime + " seconds");
    }

}