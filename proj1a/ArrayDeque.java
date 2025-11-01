public class ArrayDeque<T> {
    int cap = 8;
    private T[] a = (T[]) new Object[cap];
    int nextLast = 5;
    int nextFirst = 4;
    int size = 0;
    public ArrayDeque(){

    }
    public void addFirst(T item){
        a[nextFirst--] = item;
        size++;
        if(nextFirst<0){
            nextFirst = cap -1;
        }

    }
    public void addLast(T item){
        a[nextLast++] = item;
        size++;
        if(nextLast>cap-1){
            nextLast = 0;
        }
    }
    public boolean isEmpty(){
        return size==0;

    }
    public int size(){
        return size;
    }
    public T get(int index){
        int temp = nextFirst + index  + 1;
        if(temp>=cap){
            temp = temp -cap;
        }
        return a[temp];
    }
    public void printDeque(){
        for(int i =0;i<size;i++){
            System.out.println(get(i));
        }


    }
    public T removeFirst(){
        T temp = a[nextFirst++];
        size--;
        return temp;

    }
    public T removeLast(){
        T temp = a[nextLast--];
        size--;
        return temp;

    }

}
