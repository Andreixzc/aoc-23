package Day01;
public class A {
    public static void main(String[] args) {
        
    }
    public static int misterio(int n ){
        if (n<=1) {
            return 1;
        } else return misterio(n-1);
    }
}

