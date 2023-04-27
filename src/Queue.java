import java.nio.file.ClosedWatchServiceException;
public class Queue <Q> implements Cloneable {
    private Vector<Object> queue;
    private int first = 0;

    public Queue() {
        this.queue = new Vector<Object>();
    }

    public Queue(int limit) throws Exception {
        if (limit < 1) 
            throw new Exception("");

        this.queue = new Vector<Object>(limit);
    }

    private Queue(Vector<Object> queue) throws Exception{
        if (queue == null)
            throw new Exception("Queue cannot be null");

        this.queue = queue;
    }

    @SuppressWarnings("unchecked")
    public void push(Q value) throws Exception {
        this.queue.push((Q) ShallowOrDeepCopy.verifyAndCopy(value));
    }

    @SuppressWarnings("unchecked")    
    public Q peek() throws Exception {
        return (Q) ShallowOrDeepCopy.verifyAndCopy(this.queue.peek(first)) ;
    }

    public void pop() throws Exception {
        int size = this.queue.getFirstFreeIndex(); 
        if(size == -1)
            size = this.queue.getSize(); 
        for (int i = 0; i < size; i++)
            if (i == this.queue.getSize() -1)
                this.queue.set(i, null);
            else 
                this.queue.set(i, this.queue.peek(i+1)); 
    
        if (size < this.queue.getSize() * 0.75)
            this.queue.resizeDown();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    
    @Override
    public String toString() {
        String message = "[ ";
        try {
            for (int i = 0; i < this.queue.getSize(); i++) {
                message += this.queue.peek(i) + " ";
            }
            message += "]";
        } catch (Exception e) { e.getMessage(); }
        return message;
    }
    
    @Override
    public int hashCode() {
        int hash = 2;
        
        hash = 3 * hash + Integer.valueOf(this.first).hashCode();
        hash = 5 * hash + (this.queue).hashCode();
        if (hash < 0) hash = -hash;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        
        Queue<?> q = (Queue<?>)obj;
        if (!this.queue.equals(q.queue)) return false;
        if (this.first != q.first) return false;
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            return new Queue<Q>(this.queue);
        }
        catch(Exception e) {
            throw  new ClosedWatchServiceException();
        }
    }
}
