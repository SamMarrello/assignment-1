package src;

//WordFrequency acts like a class version of a hashmap, storing the hashmaps string key and Integer value
public class WordFrequency implements Comparable<WordFrequency> {

    public String word;
    public Integer frequency;

    //constructor, passes in a word and a frequency
    public WordFrequency(String _word, Integer _frequency) {
        this.word = _word;
        this.frequency = _frequency;
    }

    //getters and setters
    public String getWord() {
        return word;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setWord(String _word) {
        this.word = _word;
    }

    public void setFrequency(Integer _frequency) {
        this.frequency = _frequency;
    }

    @Override
    public String toString() {
        return "Word: " + word + " Frequency: " + frequency;
    }

    //overrides compare to to create an ascending list when .sort() is called on an arraylist of WordFrequency
    @Override
    public int compareTo(WordFrequency otherWord) {
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
