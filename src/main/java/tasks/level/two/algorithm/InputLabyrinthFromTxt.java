package tasks.level.two.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputLabyrinthFromTxt {

    private BufferedReader in = null;

    public void input(String fileName, ArrayList<Integer> labyrinthSize, StringBuilder labyrinthString) {

        String s;

        try {
            in = new BufferedReader(new FileReader(fileName));
            s = in.readLine();
            Pattern pat = Pattern.compile("\\d+");
            Matcher matcher = pat.matcher(s);
            while (matcher.find()) {
                labyrinthSize.add(Integer.parseInt(matcher.group()));
            }

            while ((s = in.readLine()) != null) {

                labyrinthString.append(s + "\n");
            }

        } catch (Exception e) {
            System.err.println("BufferedReader error!");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    System.err.println("Input stream is not closed");
                }
            }
        }
    }
}
