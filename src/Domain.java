import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Domain {
    ArrayList<Character> domain;
   private char [] nums = {'1','2','3','4','5','6','7','8','9','0'};
   private char [] smallLet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
   private char [] capitalLet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
   private char [] chars = {'!','@','#','$','%','^','&','*','?'};

    /**
     * A constructor
     */
   public Domain() {
       domain = new ArrayList<Character>();
   }

    /**
     * Adding all the characters from the passed array to the domain
     * @param arr
     */
   public void addCharacters (char [] arr) {
       for (int i = 0; i < arr.length; i++) {
           domain.add(arr[i]);
       }
   }

    /**
     * Removes all the elements in the current domain
     */
   public void clearDomain () {
       domain.clear();
   }

    /**
     *
     * @return The array of the capital letters
     */
    public char[] getCapitalLet() {
        return capitalLet;
    }

    /**
     *
     * @return the array of the digits
     */
    public char[] getNums() {
        return nums;
    }

    /**
     *
     * @return the array of the small letters
     */
    public char[] getSmallLet() {
        return smallLet;
    }

    /**
     *
     * @return the array of the symbols
     */
    public char[] getChars() {
        return chars;
    }

    /**
     *
     * @return the size of the domain
     */
    public int getLength () {
       return domain.size();
    }

    /**
     * Constructing a password with a given length including characters from the current domain
     * @param password
     * @param len
     */
    public void constructPassword (ArrayList<Character> password,int len) {
       Random r =  new Random();
       for (int i = 0; i < len; i ++) {
          int randomNumber = r.nextInt(domain.size());
           password.add(domain.get(randomNumber));
       }
    }

    /**
     * converting the domain to String for testing purposes
     * @return
     */
    public String toString () {
       String result = "";
        for(int i = 0;i < 10; i ++) {
            result += domain.get(i);
        }
       return result;
    }
}
