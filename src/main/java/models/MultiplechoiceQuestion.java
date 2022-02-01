package models;

import java.util.List;

public class MultiplechoiceQuestion extends Question {
    List<String> answersList;
    Integer answerIndex;
    String instructions;
    String questionText;




    public MultiplechoiceQuestion(String question, List<String> answersList) {
        this.questionText = question;
        this.answersList = answersList;
        answerIndex = null;
        type = Type.MULTIPLECHOICE_QUESTION;
    }

    public void setAnswerIndex(Integer answerIndex) {
        this.answerIndex = answerIndex;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public String getAnswerInstruction() {
        return instructions;
    }


    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public boolean vaildateAndSetAnswer(String answer) {
        try {
            int index = Integer.parseInt(answer);
            if (index >= 0 && index < answersList.size()) {
                answerIndex = index;
                return true;
            }

        } catch (Exception ignored){}
        System.out.println("Answer Index Invalid, Try Again?");
        return false;
    }

    @Override
    public String getAnswerText() {
        if (answerIndex == null)
            return null;
        return answersList.get(answerIndex);
    }


}
