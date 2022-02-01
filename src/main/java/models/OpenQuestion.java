package models;


public class OpenQuestion extends Question {
    String answer;
    String instructions;
    String questionText;

    private static final int MAX_OPEN_QUESTION_LENGTH = 256;

    public OpenQuestion(String questionText) {
        this.questionText = questionText;
        answer = null;
        type = Type.OPEN_QUESTION;
    }


    public String getQuestionText() {
        return questionText;
    }

    @Override
    public boolean vaildateAndSetAnswer(String answer) {
        if (answer.length() > MAX_OPEN_QUESTION_LENGTH) {
            System.out.println("Answer Is Too Long, Try Again?");
            return false;
        }
        this.answer = answer;
        return true;
    }

    @Override
    public String getAnswerText() {
        return answer;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswerInstruction() {
        return instructions;
    }
}
