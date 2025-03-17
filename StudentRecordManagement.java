// Node
class StudentNode{
    int rollNumber;
    String name;
    int age;
    String grade;
    StudentNode next;

    StudentNode(int rollNumber, String name, int age, String grade){
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList{
    private StudentNode head;

    // Add at beginning
    public void addAtBeginning(int rollNumber, String name, int age, String grade){
        StudentNode student = new StudentNode(rollNumber, name, age, grade);
        student.next = head;
        head = student;
    }

    // Add at end
    public void add(int rollNumber, String name, int age, String grade){
        StudentNode student = new StudentNode(rollNumber, name, age, grade);
        if(head==null){
            head = student;
            System.out.println("Head is at student");
            return;
        }
        // Head is already present
        StudentNode temp = head;

        while(temp.next!=null){
            temp=temp.next;
        }

        temp.next = student;
    }

    // Add at specific position
    public void  addStudentAtPosition(int rollNumber, String name, int age, String grade, int position) {
        StudentNode student = new StudentNode(rollNumber, name, age, grade);

        if(position == 1){
            addAtBeginning(rollNumber, name, age, grade);
            return;
        }

        StudentNode temp = head;
        for(int i=0; temp!=null && i<position-1;i++){
            temp=temp.next;
        }

        if(temp==null){
            System.err.println("Position Out of range");
            return;
        }
        student.next = temp.next;
        temp.next = student;
    }

    public void deleteByRollNumber(int rollNumber){
        if(head == null){
            System.out.println("List is empty");
            return;
        }
        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }
        StudentNode temp = head;
        while(temp.next!=null && temp.next.rollNumber != rollNumber){
            temp=temp.next;
        }
        if(temp.next==null){
            System.out.println("Student not found");
            return;
        }
        temp.next = temp.next.next;
    }

    public StudentNode searchStudent(int rollNumber) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    public void updateStudentGrade(int rollNumber, String newGrade) {
        StudentNode student = searchStudent(rollNumber);
        if (student != null) {
            student.grade = newGrade;
        } else {
            System.out.println("Student not found");
        }
    }

    public void displayStudents() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }
        StudentNode temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentRecordManagement {
    public static void main(String[] args) {
        StudentList studentList = new StudentList();
        studentList.addAtBeginning(1, "Naman Malhotra", 20, "0");
        studentList.add(2, "Manvi", 21, "A+");
        studentList.displayStudents();

        System.out.println("Updating grade...");
        studentList.updateStudentGrade(2, "0");
        studentList.displayStudents();

        System.out.println("Deleting student...");
        studentList.deleteByRollNumber(1);
        studentList.displayStudents();
    }
}
