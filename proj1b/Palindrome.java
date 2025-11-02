public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i <word.length() ; i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }
    public boolean isPalindrome(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        deque = wordToDeque(word);
        if(deque.isEmpty() ||deque.size()==1){
            return true;
        }
       while(!deque.isEmpty()){
           if(deque.size()==1){
               break;
           }
           if(deque.removeFirst()!=deque.removeLast()){
               return false;
           }
       }
       return true;
    }
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = new LinkedListDeque<>();
        deque = wordToDeque(word);
        if(deque.isEmpty() ||deque.size()==1){
            return true;
        }
        while(!deque.isEmpty()){
            if(deque.size()==1){
                break;
            }
            if(!cc.equalChars(deque.removeFirst(), deque.removeLast())){
                return false;
            }

        }
        return true;
    }
}
