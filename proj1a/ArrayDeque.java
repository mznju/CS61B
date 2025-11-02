public class ArrayDeque<T> {
    int cap = 8;
    private T[] a = (T[]) new Object[cap];
    int nextLast = 5;
    int nextFirst = 4;
    int size = 0;
    private int minusOne(int index){
        int head = nextFirst + 1;
        if(head == cap){
            head = 0;
        }
        int actualIndex = (head + index) % cap;
        return actualIndex;
    }
    public ArrayDeque(){

    }
    private void adjustCap(){
        if(size == cap){
            cap*=2;
        }
        T[] b = (T[]) new Object[cap];
        System.arraycopy(a,0,b,0,size);
        a = b;

        if(size<cap /4){
            cap = cap>>1;
        }
        T[] c = (T[]) new Object[cap];
        System.arraycopy(a,0,c,0,size);
        a = c;

    }
    public void addFirst(T item){
        adjustCap();
        a[nextFirst--] = item;
        size++;
        if(nextFirst<0){
            nextFirst = cap -1;
        }

    }
    public void addLast(T item){
        adjustCap();
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
       return a[minusOne(index)];
    }
    public void printDeque(){
        for(int i =0;i<size;i++){
            System.out.print(get(i)+" ");
        }


    }
    public T removeFirst(){
        adjustCap();
        T temp = a[nextFirst++];
        size--;
        return temp;

    }
    public T removeLast(){
        adjustCap();
        T temp = a[nextLast--];
        size--;
        return temp;

    }

}
