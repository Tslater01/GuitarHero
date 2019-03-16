import java.util.*;

public class GuitarString {
   private Queue<Double> ringBuffer; // Stores sound data(Ring Buffer Samples)
   
   public static final double DECAY_FACTOR = 0.996; // Energy decay factor
   
   // Constructs a Guitar String corresponding
   // to a given frequency. It creates a ring buffer of the
   // desired capacity N (sampling rate divided by frequency,
   // rounded to the nearest integer), and initializes it to
   // represent a guitar string at rest by enqueueing N zeros.
   // The sampling rate is specified by the constant "StdAudio.SAMPLE_RATE".
   // If the frequency is less than or equal to 0 or
   // if the resulting size of the ring buffer is less than 2,
   // the method throws an IllegalArgumentException.
   public GuitarString(double frequency) {
      int desiredCapacityN = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
      if (frequency <= 0 || desiredCapacityN < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();;
      for (int i = 0; i < desiredCapacityN; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   // Constructs a guitar string and initializes the contents
   // of the ring buffer to the values in the array.
   // If the array has fewer than two elements, the
   // constructor throws an IllegalArgumentException.
   // This constructor is used only for testing purposes.
   public GuitarString(double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();;
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // This method should replace the N elements in the ring
   // buffer with N random values between -0.5 inclusive and
   // +0.5 exclusive (i.e. -0.5 <= value < 0.5).
   public void pluck() {
      for (int i = 0; i < ringBuffer.size(); i++) {
         double randomValues = Math.random() - 0.5;
         ringBuffer.add(randomValues);
         ringBuffer.remove();
      }
   }
   
   // This method applies the Karplus-Strong update once
   // (performing one step).  It deletes the sample at
   // the front of the ring buffer and add to the end of the ring
   // buffer the average of the first two samples, multiplied by the
   // energy decay factor (0.996).
   public void tic() {
      double front = ringBuffer.remove();
      double next = ringBuffer.peek();
      double update = ((front + next) / 2) * DECAY_FACTOR;
      ringBuffer.add(update);
   }
   
   // This method should return the current sample
   // (the value at the front of the ring buffer).
   public double sample() {
      return ringBuffer.peek();
   }
}