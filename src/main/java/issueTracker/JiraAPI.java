package issueTracker;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JiraAPI {
    private final String BASE_URL = "https://issues.jenkins.io/rest/api/latest/";
    private int startAt = 0;

    // maxResults is limited to 50 for optimal performance
    private final int maxResults = 50;
    private String JENKINS_CORE_ISSUES_ENDPOINT = "search?jql=component%3Dcore%20AND%20project%3DJENKINS&startAt=" + startAt + "&maxResults=" + maxResults;

    public JSONArray getResponseInJSONArray(int startAt) throws IOException {
        this.startAt = startAt;
        JENKINS_CORE_ISSUES_ENDPOINT = "search?jql=component%3Dcore%20AND%20project%3DJENKINS&startAt=" + startAt + "&maxResults=" + maxResults;

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(BASE_URL + JENKINS_CORE_ISSUES_ENDPOINT);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        JSONObject obj = new JSONObject(rd.readLine());
        if (obj.getJSONArray("issues").isEmpty()){
            return null;
        }
        return obj.getJSONArray("issues");
    }

    public ArrayList<Issue> getIssueList(JSONArray responseIssues) {
        ArrayList<Issue> issues = new ArrayList<>();
        for (int i = 0; i < responseIssues.length(); i++) {
            JSONObject issueObj = responseIssues.getJSONObject(i);
            issues.add(new Issue(issueObj));
        }
        return issues;
    }


}
