# Data Structure - Linked List

## Types of Linked Lists
- **Singly Linked List**
- **Doubly Linked List**
- **Circular Linked List**

## Advantages of Linked Lists
-  **Dynamic Size** – Can grow or shrink dynamically.
-  **Efficient Insertions/Deletions** – No need for shifting like arrays.
-  **No Memory Waste** – Allocates memory as needed.
-  **Used in Various Data Structures** – Stacks, Queues, Graphs, etc.

## Disadvantages of Linked Lists
-  **Higher Memory Usage** – Requires extra memory for pointers.
-  **Slower Searching** – No direct indexing like arrays.
-  **More Overhead** – Extra memory for storing links.

## Operations on Linked Lists
- **Insertion**
- **Deletion**
- **Updation**
- **Traversal**
- **Searching**
- **Sorting**  
- 
## Operations on Linked Lists and Time Complexity
| Operation  | Singly Linked List | Doubly Linked List | Circular Linked List |
|------------|--------------------|--------------------|----------------------|
| **Insertion (Beginning)** | O(1) | O(1) | O(1) |
| **Insertion (End)** | O(n) | O(1) (if tail is maintained) | O(n) |
| **Insertion (Middle)** | O(n) | O(n) | O(n) |
| **Deletion (Beginning)** | O(1) | O(1) | O(1) |
| **Deletion (End)** | O(n) | O(1) (if tail is maintained) | O(n) |
| **Deletion (Middle)** | O(n) | O(n) | O(n) |
| **Searching** | O(n) | O(n) | O(n) |
| **Traversal** | O(n) | O(n) | O(n) |
| **Sorting** | O(n log n) | O(n log n) | O(n log n) |
