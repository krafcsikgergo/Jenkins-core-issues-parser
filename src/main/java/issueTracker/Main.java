package issueTracker;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws Exception {
        JiraAPI api = new JiraAPI();

        //TODO: Make loop to get all issues
        // {
        //      JSONArray responseIssues = api.getResponseInJSONArray(0);
        //      if (responseIssues == null) {
        //          System.out.println("No issues found");
        //      }
        // }





        /*
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseIssues.toString());
        //System.out.println(root.toString());
        JsonNode fieldsNode = root.path("fields");
        System.out.println(root.toPrettyString());
        Iterator<String> fieldNames = fieldsNode.fieldNames();

        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            //System.out.println(fieldName);
        }



        Issue[] issues = new Issue[50];
        api.getIssueList(responseIssues);

        */
    }
}
