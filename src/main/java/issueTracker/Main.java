package issueTracker;

import org.json.JSONArray;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        JiraAPI api = new JiraAPI();

        JSONArray responseIssues = api.getResponseInJSONArray(0);
        ArrayList<Issue> issues = new ArrayList<>();
        int totalIssues = api.getTotalIssueNumber();
        System.out.println(totalIssues);
        int i = 0;
        while (responseIssues != null) {
            issues.addAll(api.getIssueList(responseIssues));
            i += api.getMaxResults();
            printStatusBar(i, totalIssues);
            responseIssues = api.getResponseInJSONArray(i);
        }
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
}
