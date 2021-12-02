package io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.impl.jira;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.TaskTrackerService;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain.TaskData;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.domain.TaskSearchCriteria;
import io.github.jaqat.skipper.core.base.impl.storagetracker.services.tracker.impl.jira.domain.JiraTask;
import io.github.jaqat.skipper.core.utils.HttpUtils;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class JiraTaskTrackerService implements TaskTrackerService {

    private static final HttpUtils HTTP_UTILS = new HttpUtils();
    private static final Gson GSON = new GsonBuilder().create();
    private String userName;
    private String password;
    private URI jiraUrl;

    public JiraTaskTrackerService(String userName, String password, URI jiraUrl) {
        this.userName = userName;
        this.password = password;
        this.jiraUrl = jiraUrl;
    }

    @Override
    public String getTasksBrowseUrl() {
        return jiraUrl + "/browse";
    }

    @Override
    public TaskData getTaskData(TaskSearchCriteria searchCriteria) {
        HttpRequest taskRequest = getTaskRequest(searchCriteria);
        HttpResponse<String> response = HTTP_UTILS.sendRequest(taskRequest);
        int responseCode = response.statusCode();
        if (responseCode != 200) {
            return null;
        }
        JiraTask jiraTask = getJiraTaskFromResponse(response);
        return new TaskData()
                .setIssueKey(jiraTask.getKey())
                .setIssuePriority(jiraTask.getFields().getPriority().getName())
                .setIssueStatus(jiraTask.getFields().getStatus().getName())
                .setSummary(jiraTask.getFields().getSummary());

    }

    private JiraTask getJiraTaskFromResponse(HttpResponse<String> response) {
        return GSON.fromJson(response.body(), JiraTask.class);
    }


    private HttpRequest getTaskRequest(TaskSearchCriteria searchCriteria) {
        return HttpRequest.newBuilder()
                .uri(getTaskUri(searchCriteria))
                .header("Content-Type", "application/json")
                .header("Authorization", basicAuth(userName, password))
                .GET()
                .build();
    }

    private String basicAuth(String username, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    private URI getTaskUri(TaskSearchCriteria searchCriteria) {
        return URI.create(jiraUrl + "/rest/api/latest/issue/" + searchCriteria.getTaskId());
    }
}
