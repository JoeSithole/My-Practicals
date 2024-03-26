/**
 * 
 */

import java.util.Random;

/**
 * 
 */
public class DList {

	/**
	 * @param args
	 */
	
	
	Node head;
    Node tail;

    public DList() {
        head = null;
        tail = null;
    }
    
    
    

    // Method to add a node at the end of the list
    public void addNode(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    
    
    // Method to convert an array to a doubly-linked list
    public static DList fromArray(int[] arr) {
        DList list = new DList();
        for (int num : arr) {
            list.addNode(num);
        }
        return list;
    }

    
    
    
    // Method to convert a doubly-linked list to an array
    public int[] toArray() {
        int[] arr = new int[size()];
        Node current = head;
        int index = 0;
        while (current != null) {
            arr[index++] = current.data;
            current = current.next;
        }
        return arr;
    }

    
    
    
    // Method to clone a doubly-linked list
    public DList clone() {
        DList newList = new DList();
        Node current = head;
        while (current != null) {
            newList.addNode(current.data);
            current = current.next;
        }
        return newList;
    }

    
    
    // Method to add a node after a given node
    public void addAfter(Node prevNode, int data) {
        if (prevNode == null) {
            System.out.println("Previous node cannot be null");
            return;
        }
        Node newNode = new Node(data);
        newNode.next = prevNode.next;
        newNode.prev = prevNode;
        if (prevNode.next != null) {
            prevNode.next.prev = newNode;
        } else {
            tail = newNode;
        }
        prevNode.next = newNode;
    }

    
    
    // Method to add a node before a given node
    public void addBefore(Node nextNode, int data) {
        if (nextNode == null) {
            System.out.println("Next node cannot be null");
            return;
        }
        Node newNode = new Node(data);
        newNode.prev = nextNode.prev;
        newNode.next = nextNode;
        if (nextNode.prev != null) {
            nextNode.prev.next = newNode;
        } else {
            head = newNode;
        }
        nextNode.prev = newNode;
    }

    
    
    // Method to remove a node from the list
    public boolean remove(Node node) {
        if (node == null) {
            return false;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        return true;
    }
    
    

    // Method to search for a node with a given value
    public Node search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    
    

    // Method to get the tail node
    public Node tail() {
        return tail;
    }
    
    
    

    // Method to get the size of the list
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    
    

    // Method to convert the list to a string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<> <-> ");
        Node current = head;
        while (current != null) {
            sb.append("<").append(current.data).append("> <-> ");
            current = current.next;
        }
        return sb.toString();
    }
    
    

    // Method to split the list into three smaller lists
    public DList splitEqual(int element) {
        DList equalList = new DList();
        Node current = head;
        while (current != null) {
            if (current.data == element) {
                equalList.addNode(current.data);
            }
            current = current.next;
        }
        return equalList;
    }

    
    public DList splitLess(int element) {
        DList lessList = new DList();
        Node current = head;
        while (current != null) {
            if (current.data < element) {
                lessList.addNode(current.data);
            }
            current = current.next;
        }
        return lessList;
    }
    
    

    public DList splitGreater(int element) {
        DList greaterList = new DList();
        Node current = head;
        while (current != null) {
            if (current.data > element) {
                greaterList.addNode(current.data);
            }
            current = current.next;
        }
        return greaterList;
    }
    
    

    // Method to merge two sorted lists
    public DList merge(DList list1, DList list2) {
        DList mergedList = new DList();
        Node current1 = list1.head;
        Node current2 = list2.head;
        while (current1 != null && current2 != null) {
            if (current1.data < current2.data) {
                mergedList.addNode(current1.data);
                current1 = current1.next;
            } else {
                mergedList.addNode(current2.data);
                current2 = current2.next;
            }
        }
        while (current1 != null) {
            mergedList.addNode(current1.data);
            current1 = current1.next;
        }
        while (current2 != null) {
            mergedList.addNode(current2.data);
            current2 = current2.next;
        }
        return mergedList;
    }
    
    
    

    // Method to perform quicksort
    public DList quicksort(DList list) {
        if (list == null || list.head == null) {
            return list;
        }
        int pivot = list.head.data;
        DList lessList = list.splitLess(pivot);
        DList equalList = list.splitEqual(pivot);
        DList greaterList = list.splitGreater(pivot);
        lessList = quicksort(lessList);
        greaterList = quicksort(greaterList);
        return merge(merge(lessList, equalList), greaterList);
    }
    
    
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Test the implementation
        DList list1 = new DList();
        list1.addNode(6);
        list1.addNode(9);
        list1.addNode(2);

        DList list2 = new DList();
        list2.addNode(8);
        list2.addNode(1);

        DList mergedList = list1.merge(list1, list2);
        System.out.println("List 1"+list1);
        System.out.println("List 2"+list2);
        System.out.println("Merged List: " + mergedList.toString());

        DList sortedList = list1.quicksort(mergedList);
        System.out.println("Sorted List: " + sortedList.toString());
        
        System.out.println("..........................................................");
        
        
        // Test Random Numbers and Inserts
        System.out.println("Testing Random numbers and inserts");
        DList Rlist1 = new DList();
        DList Rlist2 = new DList();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int num = rand.nextInt(100);
            Rlist1.addNode(num);
            list2.addNode(num);
        }
        System.out.println("List1: " + Rlist1.toString());
        System.out.println("List2: " + list2.toString());

        // Test 4th item in list 1
        Node fourthNode = Rlist1.head;
        for (int i = 0; i < 3; i++) {
            fourthNode = fourthNode.next;
        }
        System.out.println("4th item in list 1: " + fourthNode.data);
        System.out.println("Node: <" + fourthNode.data + ">");

        // Test List1 without item
        Rlist1.remove(fourthNode);
        System.out.println("List1 without item: " + Rlist1.toString());
        System.out.println("Removed item as expected: " + (Rlist1.search(fourthNode.data) == null));
        
        
        
        System.out.println("..........................................................");
        // Test AddBefore and AddAfter
        System.out.println("Testing Add Before and Add After");
        DList list3 = new DList();
        list3.addNode(54);
        list3.addNode(1);
        list3.addNode(6);
        list3.addNode(30);
        System.out.println("List: " + list3.toString());
        list3.addBefore(list3.head.next, 9);
        list3.addAfter(list3.head.next.next, 8);
        System.out.println("Merge and Sorted sort test:");
        System.out.println("List1: " + Rlist1.toString());
        System.out.println("List2: " + list2.toString());
        DList mergedList2 = Rlist1.merge(Rlist1, list2);
        System.out.println("List3 (merged): " + mergedList.toString());
        DList sortedList2 = Rlist1.quicksort(mergedList);
        System.out.println("SortedList: " + sortedList.toString());

        
        System.out.println("..........................................................");
        // Test Sorting Random List
        System.out.println("Testing Sorting Random list");
        DList unsortedList = new DList();
        for (int i = 0; i < 10; i++) {
            unsortedList.addNode(rand.nextInt(100));
        }
        System.out.println("Unsorted List: " + unsortedList.toString());
        DList sortedRandomList = Rlist1.quicksort(unsortedList);
        System.out.println("Sorted List: " + sortedRandomList.toString());
	}

}
