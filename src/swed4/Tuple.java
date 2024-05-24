package swed4;

public class Tuple<T, K> {
    private T t;
    private K k;


    public Tuple(T t, K k) {
        this.t = t;
        this.k = k;
    }
    
    public T getT() {
        return t;
    }

    public K getK() {
        return k;
    }
}
