package toeicApp;

public class ToeicWords {
    private String word;
    private String pronounce;
    private String engdescirption;
    private String viedescriptions;
    private String type;
    private String engexample;
    private String vieexample;
    private int indexofTopic;
    private String topic;


    public ToeicWords(String word, String pronounce, String type, String engdescirption, String viedescriptions, String engexample, String vieexample, int indexofTopic, String topic) {
        this.word = word;
        this.pronounce = pronounce;
        this.engdescirption = engdescirption;
        this.viedescriptions = viedescriptions;
        this.type = type;
        this.engexample = engexample;
        this.vieexample = vieexample;
        this.indexofTopic = indexofTopic;
        this.topic = topic;
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEngdescirption() {
        return engdescirption;
    }

    public void setEngdescirption(String engdescirption) {
        this.engdescirption = engdescirption;
    }

    public String getViedescriptions() {
        return viedescriptions;
    }

    public void setViedescriptions(String viedescriptions) {
        this.viedescriptions = viedescriptions;
    }

    public String getEngexample() {
        return engexample;
    }

    public void setEngexample(String engexample) {
        this.engexample = engexample;
    }

    public String getVieexample() {
        return vieexample;
    }

    public void setVieexample(String vieexample) {
        this.vieexample = vieexample;
    }

    public int getIndexofTopic() {
        return indexofTopic;
    }

    public void setIndexofTopic(int indexofTopic) {
        this.indexofTopic = indexofTopic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "ToeicWords{" +
                "word='" + word + '\'' +
                ", pronounce='" + pronounce + '\'' +
                ", engdescirption='" + engdescirption + '\'' +
                ", viedescriptions='" + viedescriptions + '\'' +
                ", type='" + type + '\'' +
                ", engexample='" + engexample + '\'' +
                ", vieexample='" + vieexample + '\'' +
                ", indexofTopic=" + indexofTopic +
                ", topic='" + topic + '\'' +
                '}';
    }
}
