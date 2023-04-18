package issueTracker;

import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        JiraAPI api = new JiraAPI();
        ArrayList<Issue> issues = new ArrayList<>();
        int totalIssues = api.getTotalIssueNumber();
        System.out.println("There are a total of " + totalIssues + " currently available Jenkins core issues." +
                "Would you like to read them now? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (!input.contains("yes")) return;
        System.out.println("How many issues would you like to return per request (performance may vary depending on your internet speed)?" +
                "\nEnter an integer between 0-1000: ");
        int input2 = scanner.nextInt();
        if (input2 > 0 && input2 <= 1000){
            api.setMaxResults(input2);
        }
        int i = 0;
        printStatusBar(i, totalIssues);
        JSONArray responseIssues = api.getResponseInJSONArray(0);
        while (responseIssues != null) {
            issues.addAll(api.getIssueList(responseIssues));
            i += api.getMaxResults();
            System.out.println(i);
            printStatusBar(i, totalIssues);
            responseIssues = api.getResponseInJSONArray(i);
        }
        printStatusBar(i, totalIssues);
        System.out.println(issues.size());
        System.out.println("Reading of issues is done. Now writing data to file");
        writeIssuesToCSV(issues, "issues.csv");
        System.out.println("Date writing is done and is now in the root folder of the project");
    }
    public static void printStatusBar(int current, int total) {
        final int BAR_WIDTH = 20;
        int percentComplete = (int) Math.round((double) current / total * 100);
        int numChars = (int) Math.round((double) current / total * BAR_WIDTH);
        if (numChars > 20) numChars = 20;
        // Clear the console
        System.out.print("\033[H\033[2J");
        String progressBar = "[" + "=".repeat(numChars) + " ".repeat(BAR_WIDTH - numChars) + "]";
        System.out.printf("Progress: %d%% %s%n", percentComplete, progressBar);
    }

    public static void writeIssuesToCSV(List<Issue> issues, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String fieldNames = "Project,Key,Tracker,Status,Priority,Subject," +
                "Description,Author,Assignee,Environment,Created date" +
                ",Inward issues,Outward issues\n";
        try {
            writer.write(fieldNames);
        } catch (IOException e) {
            System.out.println("error writing field names");
        }
        writer.flush();
        for (Issue i : issues) {
            String line = i.getFieldValues();
            try {
                writer.write(line);
            } catch (IOException e) {
                System.out.println("error writing issue");
            }
            writer.flush();
        }
        writer.close();
    }
}
