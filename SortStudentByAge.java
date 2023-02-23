package costomSorting;

import java.util.Comparator;

import sdbms.Student;

public class SortStudentByAge implements Comparator<Student> {
	public int compare(Student x, Student y) {
		return x.getAge() - y.getAge();
	}
}
