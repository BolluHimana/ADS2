public class Outcast {
    private WordNet wn;
    public Outcast(WordNet wn) {
      this.wn = wn;
    }
    public String outcast(String[] nouns) {
      int max = -1;
      String noun = "";
      for (int i = 0; i < nouns.length; i++) {
        int curr = 0;
        for (int j = 0; j < nouns.length; j++) {
          curr += wn.distance(nouns[i], nouns[j]);
        }
        if (curr > max) {
          max = curr;
          noun = nouns[i];
        }
      }
      return noun;
    }
    public static void main(String[] args) {
        /*
        liiu
        */
    }
  }