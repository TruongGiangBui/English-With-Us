package toeicApp;

public class UserWords {
    private String word;
    private String pronounce;
    private String description;
    private String example;
    private String note;

    public UserWords(String word, String pronounce, String description, String example, String note) {
        this.word = word;
        this.pronounce = pronounce;
        this.description = description;
        this.example = example;
        this.note = note;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
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

    public String getNote() {
        return note;
    }

    @Override
    public String toString() {
        return word;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
