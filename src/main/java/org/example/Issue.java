package org.example;

import java.util.Date;
import java.util.List;

public class Issue {
    // default values given for fields that may not be present in the JSON response
    private String project;
    private String tracker;
    private String status;
    private String priority;
    private String subject;
    private String description;
    private String author;
    private String assignee;
    private String category;
    private String targetVersion;
    private Date startDate;
    private Date dueDate = null;
    private float estimatedTime = 0;
    private List<String> relatedIssues = null;
    private boolean isPrivate = false;

    public void setProject(String project) {
        this.project = project;
    }

    public void setTracker(String tracker) {
        this.tracker = tracker;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTargetVersion(String targetVersion) {
        this.targetVersion = targetVersion;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setEstimatedTime(float estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void setRelatedIssues(List<String> relatedIssues) {
        this.relatedIssues = relatedIssues;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
}
