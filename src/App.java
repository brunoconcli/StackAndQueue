import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Queue<Integer> queue = new Queue<Integer>();
            Queue<String> limitedQueue = new Queue<String>(10);
            Stack<Integer> stack = new Stack<Integer>();
            ArrayList<String> array = new ArrayList<String>();
             
            for (int i = 0; i < 10; i++) 
                stack.push(i);

            System.out.println(stack.toString());
            
            for (int i = 10; i < 16; i++) 
            stack.push(i);
            
            System.out.println(stack.toString());

            for (int i = 0; i < 15; i++) {
                queue.push(i);
            }
            System.out.println("First addition: " + queue.toString());

            queue.push(55);
            System.out.println("Second addition and resize: " + queue.toString());
            
            queue.pop();
            System.out.println("First remove and resize: " + queue.toString());

            queue.pop();
            System.out.println("Second remove and resize: " + queue.toString());

            queue.pop();
            queue.pop();
            queue.pop();
            queue.pop();
            queue.pop();
            System.out.println("Third remove and resize: " + queue.toString());

            Teste<Integer> ste = new Teste<Integer>();
            ste.guardeUmItem(2);
            ste.recupereUmItem(); // mostra o número 2

            ste.guardeUmItem(3);
            ste.recupereUmItem(); // mostra o número 3

            ste.removaUmItem();
            ste.recupereUmItem(); // mostra 2

        } catch(Exception e) {
            e.printStackTrace();
        }
        

    }
}
