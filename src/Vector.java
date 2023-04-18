import java.util.Arrays;

public class Vector<V> implements Cloneable {
    private Object[] array;
    public final boolean ISLIMITED;

    public Vector() {
        this.array = new Object[10];
        this.ISLIMITED = false;
    }

    public Vector(int limit) throws Exception {
        if (limit < 1)
            throw new Exception("Limit cannot be lower than one");

        this.array = new Object[limit];
        this.ISLIMITED = true; 
    }

    private Vector(Object[] array, boolean isLimited) {
        this.array = array;
        this.ISLIMITED = isLimited;
    }

    protected int getFirstFreeIndex() {
        for (int i = 0; i < this.array.length; i++)
            if (this.array[i] == null)
                return i;
        return -1;
    }

    protected int getLastFreeIndex() {
        for (int i = this.array.length - 1; i < -1; i--)
            if (this.array == null)
                return i; 
        return -1;            
    }

    public boolean isFull() {
        for (Object i : this.array)
            if (i == null) 
                return false;

        return true;
    }

    public void push(V value) throws Exception {
        int index = this.getFirstFreeIndex();
        if(this.isFull()) {
            if (this.ISLIMITED)
                throw new Exception("Vector is already full");

            try { 
                this.resize();
                index = this.getFirstFreeIndex(); 
            }
            catch (Exception e) { } 
        }
        
        this.set(index, value);
    }

    public void set(int index, V value) throws Exception {
        if (index >= this.getSize())
            throw new Exception("The index cannot be higher than the vector's length");
        if (index < 0)
            throw new Exception("The index must be at least equal or higher than zero");

        this.array[index] = value;
    }

    public Object peek(int position) {
        return this.array[position];
    }

    public void pop(int position) {
        this.array[position] = null;
    }

    public boolean getLimit() {
        return ISLIMITED;
    }

    public int getSize() {
        return this.array.length;
    }

    public boolean isEmpty() {
        for (Object i : this.array)
            if (i != null)
                return false;
        return true;
    }
    public void resize() throws Exception{
        resize((float)1.5);
    }
    
    public void resize(float percent) throws Exception {

        if (percent < 0) 
        throw new Exception("Growth percentage must not be lower than 0");
        if (this.ISLIMITED)
        throw new Exception("Vector length is limited");
        
        Object[] backup = this.array;
        
        this.array = new Object[Math.round(backup.length * percent)];
        
        for (int i = 0; i < backup.length; i++) {
            this.array[i] = backup[i]; 
        }
    }

    public void resizeDown() throws Exception {
        resizeDown((float)0.75);
    }
    
    public void resizeDown(float percent) throws Exception {
        if (percent < 0) 
            throw new Exception("Growth percentage must not be lower than 0");
        if (this.ISLIMITED)
            throw new Exception("Vector length is limited");
        Object[] oldArray = this.array;
        this.array = new Object[(int) Math.ceil(oldArray.length * percent)];


        for (int i = 0; i < this.getSize(); i++) 
            this.array[i] = oldArray[i];
    }
    @Override
    public int hashCode() {
        int hash = 2;
        hash = 3 * hash + Arrays.hashCode(this.array);
        hash = 5 * hash + (this.ISLIMITED ? 1 : 0);

        if (hash < 0) hash = hash * -1;
        return super.hashCode();
    }
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < this.array.length; i++) {
            message.append(this.array[i] + " ");
        }
        return message.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;

        if (this.getClass() != obj.getClass()) return false;

        Vector<?> v = (Vector<?>) obj;
        if (this.ISLIMITED != v.ISLIMITED) return false;
        if (this.getSize() != v.getSize()) return false;

        for (int i = 0; i < this.getSize(); i++) {
            if (this.array[i] != v.array[i]) return false;
        }
        return true;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        return new Vector<V>(array, ISLIMITED);
    }
}
