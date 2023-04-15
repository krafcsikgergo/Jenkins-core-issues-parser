package org.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {

        JiraAPI api = new JiraAPI();
        JSONArray responseIssues = api.getResponseInJSONArray(0);
        if (responseIssues == null) {
            System.out.println("No issues found");
        }
        Issue[] issues = new Issue[50];
        api.getIssueList(responseIssues);

        /*
        JSONObject obj = new JSONObject(json);

        int total = obj.getInt("total");
        JSONArray issues = obj.getJSONArray("issues");

        String[] issuesArray = new String[issues.length()];
        for (int i = 0; i < issues.length(); i++) {
            JSONObject issue = issues.getJSONObject(i);
            String id = Integer.toString(issue.getInt("id"));
            JSONObject fields = issue.getJSONObject("fields");
            String tracker = fields.getJSONObject("issuetype").getString("name");
            String status = fields.getJSONObject("status").getString("name");
            String priority = fields.getJSONObject("priority").getString("name");
            String subject = fields.getString("summary");
            String author = fields.getJSONObject("creator").getString("name");
            String assignee = fields.optJSONObject("assignee") != null ? fields.getJSONObject("assignee").getString("name") : "";
            String category = fields.optJSONObject("category") != null ? fields.getJSONObject("category").getString("name") : "";
            String targetVersion = fields.optJSONObject("fixVersions") != null ? fields.getJSONArray("fixVersions").getJSONObject(0).getString("name") : "";
            String createdDateStr = fields.getString("created");
            Date createdDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(createdDateStr);
            String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(createdDate);
            String dueDate = fields.optString("duedate");
            String estimatedTime = fields.optString("timeestimate");
            JSONArray relatedIssues = fields.optJSONArray("links");
            String privateIssue = Boolean.toString(fields.optBoolean("is_private"));

            String issueStr = String.format("ID: %s\nTracker: %s\nStatus: %s\nPriority: %s\nSubject: %s\nAuthor: %s\nAssignee: %s\nCategory: %s\nTarget Version: %s\nStart Date: %s\nDue Date: %s\nEstimated Time: %s\nRelated Issues: %s\nPrivate: %s\n", id, tracker, status, priority, subject, author, assignee, category, targetVersion, startDateStr, dueDate, estimatedTime, relatedIssues, privateIssue);
            issuesArray[i] = issueStr;
            System.out.println(issueStr);
        }
        */
    }
}
