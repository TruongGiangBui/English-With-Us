package toeicApp;

import java.util.ArrayList;

public class Survey {
    private String question;
    private ArrayList<String> answer=new ArrayList<>();
    private int correctanswer;

    public Survey(String question,String a1,String a2,String a3,String a4, int correctanswer) {
        this.question = question;
        this.correctanswer = correctanswer;
        answer.add(a1);
        answer.add(a2);
        answer.add(a3);
        answer.add(a4);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public int getCorrectanswer() {
        return correctanswer;
    }

    public void setCorrectanswer(int correctanswer) {
        this.correctanswer = correctanswer;
    }
}
