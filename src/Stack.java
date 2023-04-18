public class Stack<S> {
    private Vector<Object> stack;
    private int last;

    public Stack() {
        stack = new Vector<Object>();
    }

    public Stack(int limit) throws Exception{
        if (limit < 1)
            throw new Exception("Limit cannot be lower than 1");
        this.stack = new Vector<Object>(limit);
    }

    private Stack(Vector<Object> stack) throws Exception {
        if (stack == null)
            throw new Exception("stack parameter cannot be null");
        this.stack = stack;
    }

    public void push(Object element) throws Exception {
        this.stack.push(element);
        this.last ++;
    }
    public Object peek() {
        return this.stack.peek(last);
    }
    public void pop() throws Exception {
        this.stack.pop(last);
         
        if (this.stack.getFirstFreeIndex() < this.stack.getSize() * 0.75)
            this.stack.resizeDown();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return new Stack<S>(this.stack);
        } catch(Exception e) {
            throw new CloneNotSupportedException();
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        
        Stack<?> s = (Stack<?>)obj;
        if (!this.stack.equals(s.stack)) return false;
        if (this.last != s.last) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 2;
        hash = 3 * hash + Integer.valueOf(this.last).hashCode();
        hash = 5 * hash + (this.stack).hashCode();
        if (hash < 0) hash = -hash;
        return hash;
    }
    @Override
    public String toString() {
        String message = "";
        for (int i = 0; i < this.stack.getSize(); i++)
            message += this.stack.peek(i) + " ";

        return message;
    }
}
