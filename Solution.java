package sdbms;

import java.util.Scanner;

import customexception.InvalidChoiceException;

public class Solution {

	public static void main(String[] args) {
		System.out.println("Welcome to Student Database Project");
		Scanner sc = new Scanner(System.in);
		StudentManaagementSystem std = new StudentManaagementSystemImpl();

		while (true) {
			System.out.println("Enter you Choice");
			System.out.println(
					"1:Add Students\n2:Display Students\n3:Display All Students\n4:Remove Student"
					+ "\n5:Remove All Student\n6:Update Student\n7:Count Student\n8:Sort Student"
					+ "\n9:Student with Highest Marks\n10:Student with Lowest Marks\n11:Exit");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				std.addStudent();
				break;

			case 2:
				std.displayStudent();
				break;

			case 3:
				std.displayAllStudents();
				break;
			case 4:
				std.removeStudent();
				break;

			case 5:
				std.removeAllStudent();
				break;

			case 6:
				std.updateStudent();
				break;

			case 7:
				std.countStudent();
				break;

			case 8:
				std.sortStudent();
				break;

			case 9:
				std.getStudentwithHighestMarks();
				break;

			case 10:
				std.getStudentwithLowestMarks();
				break;

			case 11:
				System.out.println("THANK YOU!!!");
				System.exit(0);
				break;

			default:
                 try {
                	 String message="Invalid Choice, Enter Valid Choice";
                     throw new InvalidChoiceException(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("----------------------");
				}
			}

		}
	}

}
