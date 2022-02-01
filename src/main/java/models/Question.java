package models;

public abstract class Question {

    Type type;

    public abstract String getAnswerInstruction();

    public abstract String getQuestionText();

    public abstract boolean vaildateAndSetAnswer(String answer);

    public abstract String getAnswerText();
}

enum Type {
    MULTIPLECHOICE_QUESTION,
    OPEN_QUESTION
}