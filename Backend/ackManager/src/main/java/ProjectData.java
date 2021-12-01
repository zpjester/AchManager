public class ProjectData {
    private String projectID;
    private Teammate[] teammateList;
    private Task[] taskList;
    private boolean archived;
}

public ProjectData(String projectID, Teammate[] teammateList, Task[] taskList, boolean archived) {
    this.projectID = projectID;
    this.teammateList = teammateList;
    this.taskList = taskList;
    this.archived = archived;
}

public String getProjectID() {
    return this.projectID;
}

public void setProjectID(String newID) {
    this.projectID = newID;
}

public Teammate[] getTeammateList() {
    return this.teammateList;
}

public void setTeammateList(Teammate[] newTeammates) {
    this.teammateList = newTeammates;
}

public Task[] getTaskList() {
    return this.taskList;
}

public void setTaskList(Task[] newTasks) {
    this.taskList = newTasks;
}

public boolean getArchived() {
    return this.archived;
}

public void setArchived(boolean isArchived) {
    this.archived = isArchived;
}