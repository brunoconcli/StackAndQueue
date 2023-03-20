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

    // 50% = 50/100 = 0.5
    public void resize() throws Exception{
        resize((float)1.5); // É necessário ser lançada a exceção por conta de resize()
    }
    
    // 1 exceções 
    // 2 backup (uma cópia da array antiga/inicial)
    // 3 aumenta o tamanho da array original
    // 4 repassa os dados do array backup para o com tamanho aumentado 
    public void resize(float percent) throws Exception {
        // 1
        if (percent < 0) 
        throw new Exception("Growth percentage must not be lower than 0");
        if (this.ISLIMITED)
        throw new Exception("Vector length is limited");
        
        // 2
        Object[] backup = this.array;
        
        // 3
        this.array = new Object[Math.round(backup.length * percent)]; // this.array é reinstanciado para um novo tamanho
        
        // 4
        for (int i = 0; i < backup.length; i++) {
            this.array[i] = backup[i]; // depois recebe de volta o que foi guardado em oldArray
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

        // System.arraycopy(this.array, 0, oldArray, this.getSize(), 0);

        for (int i = 0; i < this.getSize(); i++) 
            this.array[i] = oldArray[i];
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new Vector<V>(array, ISLIMITED);
    }
}
