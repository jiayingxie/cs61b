// A class for palindrome operations.
public class Palindrome {
    /* Given a String, wordToDeque should return a Deque where the characters appear in
        the same order as in the String.
     * */
    public Deque<Character> wordToDeque(String word){
        char[] temp = word.toCharArray();
        int length = temp.length;
        LinkedListDeque<Character> characterList = new LinkedListDeque<>();
        for (int i = 0; i < length; ++i) {
            characterList.addLast(temp[i]);
        }
        return characterList;
    }

    public boolean isPalindrome(String word) {
        // Any word of length 1 or 0 is a palindrome.
        // ‘A’ and ‘a’ should not be considered equal.
        boolean isPalindrome = true;
        Deque<Character> readForwards = wordToDeque(word);
        char[] temp = word.toCharArray();
        int length = temp.length;
        for (int i = 0; i < length; ++i) {
            if (temp[i] == readForwards.removeLast()) {
                continue;
            } else {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
//        LinkedListDeque<Character> readBackwards = new LinkedListDeque<>();
//        for (int i = 0; i < length; ++i) {
//            readBackwards.addFirst(temp[i]);
//        }

    }

    /** The method will return true if the word is a palindrome according to the
     * character comparison test provided by the CharacterComparator passed
     * in as argument cc.
     * */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        boolean isPalindrome = true;
        Deque<Character> readForwards = wordToDeque(word);
        char[] temp = word.toCharArray();
        int length = temp.length;
        int halfLength = length / 2;
//        OffByOne obj = new OffByOne();
        if (length == 1) {
            isPalindrome = true;
        } else {
            for (int i = 0; i < halfLength; ++i) {
                if (cc.equalChars(temp[i],readForwards.removeLast())) {
                    continue;
                } else {
                    isPalindrome = false;
                    break;
                }
            }
        }
        return isPalindrome;
    }
}
