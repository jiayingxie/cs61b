public class OffByOne implements CharacterComparator{

    /** Returns true if characters are equal by the rules of the implementing class. */
    /** returns true for characters that are different by exactly one,
     * eg: equalChars(a,b) is true, while equalChars(a,c) is false */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == 1 || diff == -1) {
            return true;
        } else {
            return false;
        }
    }
//    public static void main(String[] args){
//        OffByOne obj = new OffByOne();
//        System.out.println(obj.equalChars('%','&'));
//        System.out.println(obj.equalChars('a','A'));
//    }
}
