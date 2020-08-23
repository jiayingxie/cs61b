public class OffByOne implements CharacterComparator{

    /** Returns true if characters are equal by the rules of the implementing class. */
    /** returns true for characters that are different by exactly one,
     * eg: equalChars(a,b) is true, while equalChars(a,c) is false */
    @Override
    public boolean equalChars(char x, char y) {
        if ((x - y) == 1 || (x - y) == -1) {
            return true;
        } else {
            return false;
        }
    }
//    public static void main(String[] args){
//        System.out.println('&' - '%');
//        OffByOne obj = new OffByOne();
//        System.out.println(obj.equalChars('%','&'));
//        System.out.println(11/2);
//    }
}
