package hwr.oop.todo;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProjectMenu extends Menu{


	public ArrayList<String> createTestTaskList() {
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("Erste Task-Name");
		myList.add("Zweite Task-Name");
		myList.add("Dritte Task-Name");
		myList.add("Vierte Task-Name");
		return myList;
	}

	public void openCreate() throws IOException {
		clearConsole();
		System.out.println("What's the name of the project?");

		String newProjectName = input.next();

		System.out.println("What's the deadline of the project? Format: YYYY-MM-DD (optional, input 'no')");

		String deadlineStr = input.next();

		LocalDate deadline = null;

		System.out.println("input: " + deadlineStr);

		if(deadlineStr != null) {
			deadline = LocalDate.parse(deadlineStr);
		} else {
			System.out.println("input was null!");
		}


		Project testproject = new Project(newProjectName, deadline); // TODO: Save project in Application class
		System.out.println("U WANNA SAVE THAT SHIT?");
		System.out.println("[1] Yes!");
		System.out.println("[0] No!");

		int answer = input.nextInt();

        /* Den Datei-Namen könnte man manuell festlegen,
		   oder man nimmt einfach einen Standardpfad,
		   welcher den Namen des "Projects" hat
		 */

		String TestFileName = "C:/Users/DerNikls/Desktop/test.csv";
		// String TestFileName = testproject.Title()

		if(answer == 1) {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			ArrayList<String> taskList = createTestTaskList();

			CSVCreate.writeFile(testproject.title(), taskList, formatter.format(testproject.getDeadline()), TestFileName);
		}
		System.out.println("U like to read data?");
		System.out.println("[1] Yes!");
		System.out.println("[0] No!");

		answer = input.nextInt();

		if(answer == 1) {
			List<String[]> projectDetails = CSVReader.readCSV(TestFileName);
			for (String[] project : projectDetails) {
				System.out.println("Project Name: " + project[0]);
				System.out.println("Tasks:");
				for (int i = 1; i < project.length - 1; i++) {
					System.out.println(project[i]);
				}
				System.out.println("Date: " + project[project.length - 1]);
			}
		}
	}

}
