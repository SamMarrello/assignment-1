package src;

public class ParagraphFrequency implements Comparable<ParagraphFrequency> {
    public String word;
    public Integer frequency;

    public ParagraphFrequency(String word, Integer frequency) {
        this.frequency = frequency;
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }

    public Integer getFrequency() {
        return this.frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int compareTo(ParagraphFrequency otherWord) {
        if (this.frequency < otherWord.frequency) {
            return -1;
        }
        else if (this.frequency > otherWord.frequency) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
