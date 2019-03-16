public class Guitar37 implements Guitar {
   private GuitarString[] keyStrings; // Array of all strings in a guitar
   private int ticsCount = 0;
   // Possible keyboard inputs
   public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
   
   // The constructor creates an array of GuitarSting objects
   // with notes in between 100Hz to 880Hz
   public Guitar37() {
      keyStrings = new GuitarString[KEYBOARD.length()];
      for (int i = 0; i < KEYBOARD.length(); i++) {
         keyStrings[i] = new GuitarString(440.0 * Math.pow(2, ((i - 24.0) / 12.0)));
      }
   }
   
   // Plays the given note by plucking a string
   // Concert-A has a pitch of 12
   public void playNote(int pitch) {
      int note = pitch + 12;
      keyStrings[note].pluck();
   }
   
   // finds if the input provided is in the keyboard
   // key is the input character
   // true : Valid
   // false : Invalid
   public boolean hasString(char key) {
      return KEYBOARD.indexOf(key) != -1;
   }
   
   // plucks for an appropriate input
   // otherwise : throws IllegalArgumentException
   // if the key is not one of the 37 keys it is designed to play
   public void pluck(char key) {
      if (hasString(key)) {
         keyStrings[KEYBOARD.indexOf(key)].pluck();
      }
      throw new IllegalArgumentException();
   }
   
   // returns the sound
   // sum of all the strings
   public double sample() {
      double sumOfSamples = 0.0;
      for (int i = 0; i < KEYBOARD.length(); i++) {
         sumOfSamples += keyStrings[i].sample();
      }
      return sumOfSamples;
   }
   
   // advances the time forward one "tic"
   public void tic() {
      for (int i = 0; i < KEYBOARD.length(); i++) {
         keyStrings[i].tic();
      }
      ticsCount++;
   }
   
   // returns the number of times the input called
   public int time() {
      return ticsCount;
   }
}