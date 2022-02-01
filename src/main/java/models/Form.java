package models;

import java.util.List;

public class Form {
    String id;
    String title;
    List<Question> questionList;


    public Form(String id, String title, List<Question> questionList) {
        this.id = id;
        this.title = title;
        this.questionList = questionList;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
