class LL {
    Node head; // ✅ head belongs to the LinkedList, not Node

    class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;   // ❌ you wrote: this.data = "new";
                                // ✅ should use the parameter
            this.next = null;
        }
    }

    public void addfirst(String data) {
        Node newnode = new Node(data);

        if (head == null) {
            head = newnode;
            return;             // ❌ you missed return, so extra lines executed
        }

        newnode.next = head;    // ✅ link new node to current head
        head = newnode;         // ✅ move head to new node
    }

    public void addlast(String data) {
        Node newnode = new Node(data);

        if (head == null) {
            head = newnode;
            return;             // ❌ same issue, without return logic breaks
        }

        Node nextnode = head;
        while (nextnode.next != null) {
            nextnode = nextnode.next;
        }

        nextnode.next = newnode; // ❌ you wrote: newnode = nextnode;
                                 // ✅ should link at the end
    }

    public void print() {
        if (head == null) {
            System.out.print("Empty list");
            return;
        }

        Node nextnode = head;
        while (nextnode != null) {
            System.out.print(nextnode.data + " -> "); // ❌ you wrote: nextnode
                                                      // ✅ should print data
            nextnode = nextnode.next;                 // ❌ you moved before printing
        }
        System.out.println("null");
    }

    public static void main(String args[]) {
        LL list = new LL();
        list.addfirst("hi");       // ❌ you wrote hi without quotes
        list.addfirst("hello");    // ✅ should be in quotes
        list.addfirst("namaste");
        list.addlast("no");
        list.addlast("no");
        list.print();
    }
}
