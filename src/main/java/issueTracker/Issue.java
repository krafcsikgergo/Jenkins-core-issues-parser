package issueTracker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Issue {
    private String project;
    private String tracker;
    private String status;
    private String priority;
    private String subject;
    private String description;
    private String author;
    private String assignee;
    private String environment;
    private Date createdDate;
    private ArrayList<String> relatedIssues = new ArrayList<>();

    private JSONObject fields;

    public Issue(JSONObject obj){
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

    private void setTracker(){
        this.tracker = fields.getJSONObject(IssueFields.ISSUE_TYPE).
                getString("name");
    }

    private void setStatus(){
        this.status = fields.getJSONObject(IssueFields.STATUS).
                getString("name");
    }

    private void setPriority(){
        this.priority = fields.getJSONObject(IssueFields.PRIORITY).
                getString("name");
    }

    private void setSubject(){
        this.subject = fields.getString(IssueFields.SUMMARY);
    }

    private void setDescription(){
        this.description = fields.getString(IssueFields.DESCRIPTION);
    }

    private void setAuthor(){
        this.author = fields.getJSONObject(IssueFields.CREATOR).
                getString("name");
    }

    private void setAssignee(){
        this.assignee = fields.getJSONObject(IssueFields.ASSIGNEE).
                getString("name");
    }

    private void setEnvironment(){
        this.environment = fields.getString(IssueFields.ENVIRONMENT);
    }

    private void setCreatedDate(){
        String createdDateStr = fields.getString(IssueFields.CREATED);
        try {
            this.createdDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .parse(createdDateStr);
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + createdDateStr);
            this.createdDate = null;
        }
    }

    private void setRelatedIssues(){
        JSONArray related = fields.optJSONArray(IssueFields.ISSUE_LINKS) != null ?
                fields.getJSONArray(IssueFields.ISSUE_LINKS) : null;
        if (related != null) {
            for (Object object : related) {
                JSONObject casted = (JSONObject) object;
                relatedIssues.add(casted.getJSONObject(IssueFields.OUTWARD_ISSUE)
                        .getString(IssueFields.KEY));
            }
        }
    }
}
