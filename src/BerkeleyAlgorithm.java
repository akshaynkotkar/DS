import java.util.*;

class BerkeleyAlgorithm {

    static class Node {
        int id;
        long clockTime;
        Node(int id, long clockTime) {
            this.id = id;
            this.clockTime = clockTime;
        }
        public String toString() {
            return "Node " + id + " -> Clock: " + clockTime;
        }
    }

    public static void main(String[] args) {
        List<Node> nodes = Arrays.asList(new Node(1, 1000), new Node(2, 1200), new Node(3, 1300), new Node(4, 1100));
        
        long avgTime = nodes.stream().mapToLong(n -> n.clockTime).sum() / nodes.size();
        System.out.println("Average Time: " + avgTime);
        
        nodes.forEach(n -> {
            long offset = avgTime - n.clockTime;
            System.out.println("Node " + n.id + " - Offset: " + offset);
            n.clockTime += offset;
        });
        
        nodes.forEach(System.out::println);
    }
}

/*
   javac BerkeleyAlgorithm.java
   java BerkeleyAlgorithm

output

   Average Time: 1150
   Node 1 - Offset: 150
   Node 2 - Offset: -50
   Node 3 - Offset: -150
   Node 4 - Offset: 50
   Node 1 -> Clock: 1150
   Node 2 -> Clock: 1150
   Node 3 -> Clock: 1150
   Node 4 -> Clock: 1150
*/
