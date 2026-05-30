import java.util.Scanner;
// Semyon Galitsky - 213863310
// Dor Mendelovich - your id

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice, count, wage, id, title;
        String lecturerName, degreeName, committeeName, departmentName, result;

        System.out.println("Please provide the college name: ");
        College college = new College(scanner.nextLine());
        System.out.println("Starting system for: " + college.getName());

        do {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("                                      MENU                                       ");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("[0] - Exit");
            System.out.println("[1] - Add lecturer");
            System.out.println("[2] - Add committee");
            System.out.println("[3] - Assign lecturer to committee");
            System.out.println("[4] - Update head of committee");
            System.out.println("[5] - Remove lecturer from a committee");
            System.out.println("[6] - Add study department");
            System.out.println("[7] - Assign lecturer to department");
            System.out.println("[8] - Show salary average of all college lecturers");
            System.out.println("[9] - Show salary average of all college lecturers in a department");
            System.out.println("[10]- Show all lecturers");
            System.out.println("[11]- Show all committees");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.print("\nPlease select an action: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // buffer

            switch (choice) {
                case 0:
                    System.out.println("Exit selected, shutting system down.");
                    break;

                case 1:
                    System.out.print("Please provide a name: ");
                    lecturerName = scanner.nextLine();

                    System.out.print("Please provide an id: ");
                    id = scanner.nextInt();

                    System.out.println("Please provide Title level [1-4]: ");
                    System.out.println("    [1] - Bachelors\n    [2] - Masters\n    [3] - Dr\n    [4] - Professor");
                    title = scanner.nextInt();
                    scanner.nextLine(); // buffer

                    System.out.print("Please provide name of Degree: ");
                    degreeName = scanner.nextLine();

                    System.out.print("Please provide wage: ");
                    wage = scanner.nextInt();
                    scanner.nextLine(); // buffer

                    result = college.addLecturer(lecturerName, id, title, degreeName, wage);
                    if (result.equals("SUCCESS")) {
                        System.out.println("[Success] Lecturer " + lecturerName + " was successfully added.");
                    } else {
                        System.out.println("--- Action Failed ---\n" + result);
                    }
                    break;

                case 2:
                    System.out.print("Please provide the name of the committee you wish to add: ");
                    committeeName = scanner.nextLine();
                    System.out.print("Please provide the name of the head of the committee: ");
                    lecturerName = scanner.nextLine();

                    result = college.addCommittee(committeeName, lecturerName);
                    if (result.equals("SUCCESS")) {
                        System.out.println("[Success] Committee " + committeeName + " was successfully added.");
                    } else {
                        System.out.println("--- Action Failed ---\n" + result);
                    }
                    break;

                case 3:
                    System.out.print("Please provide the name of the lecturer: ");
                    lecturerName = scanner.nextLine();
                    System.out.print("Please provide the name of the committee: ");
                    committeeName = scanner.nextLine();

                    result = college.assignLecturerToCommittee(lecturerName, committeeName);
                    if (result.equals("SUCCESS")) {
                        System.out.println("[Success] Assigned successfully.");
                    } else {
                        System.out.println("--- Action Failed ---\n" + result);
                    }
                    break;

                case 4:
                    System.out.print("Please provide the name of the lecturer: ");
                    lecturerName = scanner.nextLine();
                    System.out.print("Please provide the name of the committee: ");
                    committeeName = scanner.nextLine();

                    result = college.updateHeadOfCommittee(lecturerName, committeeName);
                    if (result.equals("SUCCESS")) {
                        System.out.println("[Success] Head of committee updated successfully.");
                    } else {
                        System.out.println("--- Action Failed ---\n" + result);
                    }
                    break;

                case 5:
                    System.out.print("Please provide the name of the lecturer: ");
                    lecturerName = scanner.nextLine();
                    System.out.print("Please provide the name of the committee: ");
                    committeeName = scanner.nextLine();

                    result = college.removeLecturerFromCommittee(lecturerName, committeeName);
                    if (result.equals("SUCCESS")) {
                        System.out.println("[Success] Lecturer removed from committee.");
                    } else {
                        System.out.println("--- Action Failed ---\n" + result);
                    }
                    break;

                case 6:
                    System.out.print("Please provide the name of the department you wish to add: ");
                    departmentName = scanner.nextLine();
                    System.out.print("Please provide the student count: ");
                    count = scanner.nextInt();
                    scanner.nextLine(); // buffer

                    result = college.addDepartment(departmentName, count);
                    if (result.equals("SUCCESS")) {
                        System.out.println("[Success] Department " + departmentName + " was successfully added.");
                    } else {
                        System.out.println("--- Action Failed ---\n" + result);
                    }
                    break;

                case 7:
                    System.out.print("Please provide the name of the lecturer: ");
                    lecturerName = scanner.nextLine();
                    System.out.print("Please provide the name of the department: ");
                    departmentName = scanner.nextLine();

                    result = college.assignLecturerToDepartment(lecturerName, departmentName);
                    if (result.equals("SUCCESS")) {
                        System.out.println("[Success] Lecturer assigned to department.");
                    } else {
                        System.out.println("--- Action Failed ---\n" + result);
                    }
                    break;

                case 8:
                    System.out.println("The average wage of all lecturers across the college is: " + college.getAverageWage());
                    break;

                case 9:
                    System.out.print("Please provide department name: ");
                    departmentName = scanner.nextLine();
                    double avg = college.getDepartmentAverageWage(departmentName);
                    if (avg == -1) {
                        System.out.println("[Error] Department not found.");
                    } else {
                        System.out.println("The average wage of lecturers in " + departmentName + " is: " + avg);
                    }
                    break;

                case 10:
                    result = college.getAllLecturersDetails();
                    if (result.isEmpty()) {
                        System.out.println("[Error] No lecturers found.");
                    } else {
                        System.out.println(result);
                    }
                    break;

                case 11:
                    result = college.getAllCommitteesDetails();
                    if (result.isEmpty()) {
                        System.out.println("[Error] No committees found.");
                    } else {
                        System.out.println(result);
                    }
                    break;

                default:
                    System.out.println("--- Action Failed ---\nInput out of range. Must be between 0 and 11.");
            }

            if (choice != 0) {
                System.out.println("\nPress enter to continue");
                scanner.nextLine();
            }

        } while (choice != 0);

        scanner.close();
    }
}