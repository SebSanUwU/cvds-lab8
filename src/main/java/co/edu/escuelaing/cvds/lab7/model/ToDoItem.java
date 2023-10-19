package co.edu.escuelaing.cvds.lab7.model;

public class ToDoItem {
    private Integer id;
    private Integer userId;
    private String title;
    private Boolean completed;

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String  getTitle(){
        return title;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    public boolean getCompleted(){
        return completed;
    }
}
