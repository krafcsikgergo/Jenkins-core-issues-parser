package issueTracker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Issue {
    private String project, tracker, status, priority, subject, description, author, assignee, environment;
    private Date createdDate;
    private ArrayList<String> inwardIssues = new ArrayList<>();
    private ArrayList<String> outwardIssues = new ArrayList<>();
    private JSONObject fields;

    public Issue(JSONObject obj) {
        fields = obj.getJSONObject("fields");
        setProject();
        setTracker();
        setStatus();
        setPriority();
        setSubject();
        setDescription();
        setAuthor();
        setAssignee();
        setEnvironment();
        setCreatedDate();
        setRelatedIssues();
    }

    private void setProject() {
        this.project = fields.getJSONObject(IssueFields.PROJECT).
                getString("name");
    }

    private void setTracker() {
        this.tracker = fields.getJSONObject(IssueFields.ISSUE_TYPE).
                getString("name");
    }

    private void setStatus() {
        this.status = fields.getJSONObject(IssueFields.STATUS).
                getString("name");
    }

    private void setPriority() {
        this.priority = fields.getJSONObject(IssueFields.PRIORITY).
                getString("name");
    }

    private void setSubject() {
        this.subject = fields.getString(IssueFields.SUMMARY);
    }

    private void setDescription() {
        this.description = fields.optString(IssueFields.DESCRIPTION);
    }

    private void setAuthor() {
        this.author = fields.getJSONObject(IssueFields.CREATOR).
                getString("name");
    }

    private void setAssignee() {
        this.assignee = this.assignee = fields.optJSONObject(IssueFields.ASSIGNEE) != null ?
                fields.getJSONObject(IssueFields.ASSIGNEE).getString("name") : "";
    }

    private void setEnvironment() {
        this.environment = fields.optString(IssueFields.ENVIRONMENT);
    }

    private void setCreatedDate() {
        String createdDateStr = fields.getString(IssueFields.CREATED);
        try {
            this.createdDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .parse(createdDateStr);
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + createdDateStr);
            this.createdDate = null;
        }
    }

    private void setRelatedIssues() {
        JSONArray related = fields.optJSONArray(IssueFields.ISSUE_LINKS) != null ?
                fields.getJSONArray(IssueFields.ISSUE_LINKS) : null;

        // If there are related issues
        if (related != null) {
            for (Object object : related) {
                JSONObject casted = (JSONObject) object;
                // Is there an inward issue?
                String inward = casted.optJSONObject(IssueFields.INWARD_ISSUE) != null ?
                        casted.getJSONObject(IssueFields.INWARD_ISSUE).getString("key") : null;
                // Is there an outward issue?
                String outward = casted.optJSONObject(IssueFields.OUTWARD_ISSUE) != null ?
                        casted.getJSONObject(IssueFields.OUTWARD_ISSUE).getString("key") : null;
                if (inward != null) inwardIssues.add(inward);
                if (outward != null) outwardIssues.add(outward);
            }
        }
    }
}
