package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import costomSorting.SortStudentByAge;
import costomSorting.SortStudentById;
import costomSorting.SortStudentByMarks;
import costomSorting.SortStudentByName;
import customexception.InvalidChoiceException;
import customexception.StudentNotFoundException;

public class StudentManaagementSystemImpl implements StudentManaagementSystem {
	Scanner sc = new Scanner(System.in);
	Map<String, Student> db = new LinkedHashMap<String, Student>();

	@Override
	public void addStudent() {
		System.out.println("Enter Name of the Student");
		String name = sc.next();
		System.out.println("Enter Age of the Student");
		int age = sc.nextInt();
		System.out.println("Enter Marks of the Student");
		int marks = sc.nextInt();
		Student student = new Student(name, age, marks);
		db.put(student.getId(), student);
		System.out.println("Student Record Inserted Successfully");
		System.out.println("Student Id is " + student.getId());
	}

	@Override
	public void displayStudent() {
		System.out.println("Enter Student Id ");
		String id = sc.next().toUpperCase();
		if (db.containsKey(id)) {
			System.out.println(db.get(id));
//			Student student=db.get(id);
//			System.out.println(student.getAge());

		} else {
			try {
				String message = "Student with the Id " + id + " is not found!";
				throw new StudentNotFoundException(message);
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void displayAllStudents() {
		if (db.size() != 0) {

			System.out.println("Student Records are as follows");
			System.out.println("===============================");
			Set<String> keys = db.keySet();
			for (String key : keys) {
				Student student = db.get(key);
				System.out.println(student);// System.out.println(db.get(key));
			}
		} else {
			try {
				String message = "Student Database is Empty Nothing to Display";
				throw new StudentNotFoundException(message);
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeStudent() {
		System.out.println("Enter Student Id");
		String id = sc.next().toUpperCase();
		if (db.containsKey(id)) {
			System.out.println(db.get(id));
			db.remove(id);
			System.out.println("Student Record Removed Successfully!!");
		} else {
			try {
				String message = "Student with the Id " + id + " is not found!";
				throw new StudentNotFoundException(message);
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudent() {
		if (db.size() != 0) {
			System.out.println("Avalable Student Records " + db.size());
			db.clear();
			System.out.println("All the Students records deleted successifully");
		} else {
			try {
				String message = "Student Database is Empty, Nothing to Delete";
				throw new StudentNotFoundException(message);
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void updateStudent() {
		System.out.println("Enter Id");
		String id = sc.next().toUpperCase();

		if (db.containsKey(id)) {
			Student student = db.get(id);
			System.out.println(db.get(id));
			System.out.println("Update by \n1:Update Age\n2:Update Name\n3:Update Marks");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter new Age");
				int age = sc.nextInt();
				student.setAge(age);
				System.out.println(db.get(id));
				System.out.println("Updated Successfully!");
				break;
			case 2:
				System.out.println("Enter new Name");
				student.setName(sc.next());
				System.out.println(db.get(id));
				System.out.println("Updated Successfully!");
				break;
			case 3:
				System.out.println("Enter new Marks");
				student.setMarks(sc.nextInt());
				System.out.println(db.get(id));
				System.out.println("Updated Successfully!");
				break;

			default:
				try {
					String message = "Invalid Choice, Enter Valid Choice";
					throw new InvalidChoiceException(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("----------------------");
				}
			}
		} else {
			try {
				String message = "Student with the Id " + id + " is not found!";
				throw new StudentNotFoundException(message);
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void countStudent() {
		System.out.println("Avalible Student Records: " + db.size());
	}

	@Override
	public void sortStudent() {
		if (db.size() >= 2) {
			Set<String> keys = db.keySet();
			List<Student> list = new ArrayList<Student>();
			for (String key : keys) {
				list.add(db.get(key));
			}
			System.out.println(
					"Sort by\n1:Sort Student BY ID \n2:Sort Student By Age\n3:Sort Student By Name\n4:Sort Student By Marks");
			System.out.println("Enter choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				Collections.sort(list, new SortStudentById());
				display(list);
				break;
			case 2:
				Collections.sort(list, new SortStudentByAge());
				display(list);
				break;
			case 3:
				Collections.sort(list, new SortStudentByName());
				display(list);
				break;
			case 4:
				Collections.sort(list, new SortStudentByMarks());
				display(list);
				break;
			default:
				try {
					String message = "Invalid Choice, Enter Valid Choice";
					throw new InvalidChoiceException(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("----------------------");
				}
			}
		} else {
			try {
				String message = "No Sufficient Records to Sort!";
				throw new StudentNotFoundException(message);
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void display(List<Student> list) {
		for (Student s : list) {
			System.out.println(s);
		}
	}

	@Override
	public void getStudentwithHighestMarks() {
		if (db.size() >= 2) {
			Set<String> keys = db.keySet();
			List<Student> list = new ArrayList<Student>();
			for (String key : keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(db.size() - 1));
		} else {
			try {
				String message = "No Sufficient Student Records to Compare!";
				throw new StudentNotFoundException(message);
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void getStudentwithLowestMarks() {
		if (db.size() >= 2) {
			Set<String> keys = db.keySet();
			List<Student> list = new ArrayList<Student>();
			for (String key : keys) {
				list.add(db.get(key));
			}
			Collections.sort(list, new SortStudentByMarks());
			System.out.println(list.get(0));
		} else {
			try {
				String message = "No Sufficient Student Records to Compare!";
				throw new StudentNotFoundException(message);
			} catch (StudentNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
