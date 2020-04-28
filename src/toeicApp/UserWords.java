package toeicApp;

public class UserWords {
    private String word;
    private String description;
    private String example;

    public UserWords(String word, String description, String example) {
        this.word = word;
        this.description = description;
        this.example = example;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
