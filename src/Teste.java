public class Teste<T> {
    private Object[] array;
    private final int FIRST = 0;
    private int last = 0;

    public Teste() {
        array = new Object[8];
    }

    public void guardeUmItem(Object element) {
        this.array[last] = element;
        last ++;
    }

    public Object recupereUmItem() {
        return this.array[last];
    }

    public void removaUmItem() {
        this.array[last] = null;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int hash = 2;
        hash = 3 * hash + this.array.hashCode();

        if (hash<0) hash = -hash;
        return hash;
    }

    @Override
    public String toString() {
        return "" + this.array[last];
    }

    
}
