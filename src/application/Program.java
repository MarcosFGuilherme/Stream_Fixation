package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;
import general.Utility;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: "); // c:\temp\in.txt
		String pathIn = sc.nextLine();

		System.out.print("Enter salary: "); // 2000.00
		Double salary = sc.nextDouble();

		System.out.print("Enter starts letter of names: "); // M
		String letter = sc.next();

		try (BufferedReader br = new BufferedReader(new FileReader(pathIn))) {

			List<Employee> list = new ArrayList<>();

			String line = br.readLine();

			while (line != null) {
				String[] fields = line.split(",");
				Employee emp = new Employee(fields[0], fields[1], Double.parseDouble(fields[2]));
				list.add(emp);
				line = br.readLine();
			}
			showEmployee(list,"Loading employees from file...");
			System.out.println();
			
			List<Employee> filterList = list.stream()
					.filter(e -> e.getSalary() > salary)
					.collect(Collectors.toList());
			
			showEmployee(filterList,"Data of people whose salary is more than [" + String.format("%.2f", salary) + "]:");
			
			List<String> filterEmail = filterList.stream()
					.map(e -> e.getEmail())
					.sorted((s1,s2) -> s1.compareTo(s2))
					.collect(Collectors.toList());
					
					
			showEmail(filterEmail,"Email of people whose salary is more than [" + String.format("%.2f", salary) + "]:");		
			System.out.print("Sum of salary of people whose name starts with [" + letter.toUpperCase() + "]: ");

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		sc.close();

	}

	public static void showEmployee(List<Employee> list, String title) {

		System.out.println(Utility.stringFix("", 65, "="));
		System.out.println(Utility.stringFix(title, 65, " "));
		System.out.println(Utility.stringFix("", 65, "="));
		System.out.println(Utility.stringFix("Employee", 20, " ") 
				+ Utility.stringFix("Email", 30, " ")
				+ Utility.stringFix("Salary", 15, " "));
		System.out.println(Utility.stringFix("", 65, "-"));
		for (Employee e : list) {
			System.out.print(Utility.stringFix(e.getName(), 20, " "));
			System.out.print(Utility.stringFix(e.getEmail(), 30, " "));
			System.out.println(Utility.stringFix(String.format("%.2f", e.getSalary()), 15, " "));
		}
		System.out.println(Utility.stringFix("", 65, "-"));
		System.out.println(Utility.stringFix("", 65, "="));
	}
	
	public static void showEmail(List<String> list, String title) {
		System.out.println(Utility.stringFix("", 65, "="));
		System.out.println(Utility.stringFix(title, 65, " "));
		System.out.println(Utility.stringFix("", 65, "="));
		System.out.println(Utility.stringFix("Email", 30, " "));
		System.out.println(Utility.stringFix("", 30, "-"));
		for (String e : list) {
			System.out.println(Utility.stringFix(e, 30, " "));
		}
		System.out.println(Utility.stringFix("", 30, "-"));
		System.out.println(Utility.stringFix("", 65, "="));
	}
}
