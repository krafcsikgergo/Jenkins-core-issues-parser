package issueTracker;

import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        JiraAPI api = new JiraAPI();

        JSONArray responseIssues = api.getResponseInJSONArray(0);
        ArrayList<Issue> issues = new ArrayList<>();
        int totalIssues = api.getTotalIssueNumber();
        System.out.println(totalIssues);
        int i = 0;
        //responseIssues != null
        while (i != 5) {
            issues.addAll(api.getIssueList(responseIssues));
            i += api.getMaxResults();
            printStatusBar(i, totalIssues);
            responseIssues = api.getResponseInJSONArray(i);
        }
        writeIssuesToCSV(issues, "issues.csv");
    }
    public static void printStatusBar(int current, int total) {
        final int BAR_WIDTH = 20;
        int percentComplete = (int) Math.round((double) current / total * 100);
        int numChars = (int) Math.round((double) current / total * BAR_WIDTH);
        // Clear the console
        System.out.print("\033[H\033[2J");
        String progressBar = "[" + "=".repeat(numChars) + " ".repeat(BAR_WIDTH - numChars) + "]";
        System.out.printf("Progress: %d%% %s%n", percentComplete, progressBar);
    }

    public static void writeIssuesToCSV(List<Issue> issues, String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        String fieldNames = "Project,Key,Tracker,Status,Priority,Subject," +
                "Description,Author,Assignee,Environment,Created date" +
                ",Inward issues,Outward issues\n";
        try {
            writer.write(fieldNames);
        } catch (IOException e) {
            System.out.println("error writing field names");
        }
        for (Issue i : issues) {
            String line = i.getFieldValues();
            try {
                writer.write(line);
            } catch (IOException e) {
                System.out.println("error writing issue");
            }
        }
        writer.close();
    }
}
