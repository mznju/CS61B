public class LinkedListDeque<T> {
    private class Node{
        T item;
        Node next;
        Node prev;
        public Node(T item){
            this.item = item;
        }
    }
    Node first;
    Node last;
    int size;
    public LinkedListDeque(){


    }
    public void addFirst(T item){
        Node temp = new Node(item);
        if(last==null){
            last = temp;
        }else{
            temp.next = first;
            first.prev = temp;
        }
        first = temp;
        size++;
    }
    public void addLast(T item){
        Node temp = new Node(item);
        if(first==null){
            first = temp;
        }else{
            last.next = temp;
            temp.prev = last;
        }
        last = temp;
        size++;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node pre = first;
        while(pre!=null){
            System.out.println(pre.item+"->");
            pre = pre.next;
        }
    }
    public T removeFirst(){
        size--;
        if(first ==null){
            return null;
        }
        T temp = first.item;
        first = first.next;
        if(first==null){
            last =null;
            return temp;
        }
        first.prev = null;

        return temp;
    }
    public T removeLast(){
        size--;
        if(last ==null){
            return null;
        }
        T temp = last.item;
        last = last.prev;
        if(last ==null){
            first=null;
            return temp;
        }
        last.next=null;
        return temp;

    }
    public T get(int index){
        Node temp = first;
        for(int i = 0;i<index;i++){
            temp = temp.next;
        }
        return temp.item;
    }

    public T getRecursive(int index){
      return getRecursiveHelper(index,first);
    }
    private T getRecursiveHelper(int index,Node f){
        if(index == 0){
            return f.item;
        }else{
            return getRecursiveHelper(index-1,f.next);
        }
    }

}
