import java.util.Scanner;
// Semyon Galitsky - 213863319
// Dor Mendelovich - 214289613

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice, count, wage, id, title;
        String lecturerName, degreeName, committeeName, departmentName;

        System.out.println("Please provide the college name: ");
        College college = new College(scanner.nextLine());
        System.out.println("Starting system for: " + college.getName());

        do {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("                                      MENU                                       ");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("[0] - Exit");
            System.out.println("[1] - Add lecturer / Dr / Professor");
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
            System.out.println("[12]- Add article to Dr / Professor");
            System.out.println("[13]- Compare Dr / Professors by articles count"); // דרישה 1
            System.out.println("[14]- Compare committees (by members or total articles)"); // דרישה 2
            System.out.println("[15]- Duplicate a committee"); // דרישה 3
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("\nPlease select an action: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // buffer

            try {
                switch (choice) {
                    case 0:
                        System.out.println("Exit selected, shutting system down.");
                        break;

                    case 1:
                        System.out.print("Please provide a name: ");
                        lecturerName = scanner.nextLine();

                        System.out.print("Please provide an id (9 digits): ");
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

                        Lecturer newLecturer;
                        if (title == 1 || title == 2) {
                            newLecturer = new Lecturer(lecturerName, id, title, degreeName, wage);
                        } else if (title == 3) {
                            newLecturer = new Dr(lecturerName, id, degreeName, wage);
                        } else if (title == 4) {
                            System.out.print("Please provide awarding institution for the professor: ");
                            String institution = scanner.nextLine();
                            newLecturer = new Professor(lecturerName, id, degreeName, wage, institution);
                        } else {
                            throw new ManagementException("Invalid title level. Must be between 1 and 4.");
                        }

                        college.addLecturer(newLecturer);
                        System.out.println("[Success] Lecturer " + lecturerName + " was successfully added.");
                        break;

                    case 2:
                        System.out.print("Please provide the name of the committee you wish to add: ");
                        committeeName = scanner.nextLine();
                        System.out.print("Please provide the name of the head of the committee: ");
                        lecturerName = scanner.nextLine();

                        college.addCommittee(committeeName, lecturerName);
                        System.out.println("[Success] Committee " + committeeName + " was successfully added.");
                        break;

                    case 3:
                        System.out.print("Please provide the name of the lecturer: ");
                        lecturerName = scanner.nextLine();
                        System.out.print("Please provide the name of the committee: ");
                        committeeName = scanner.nextLine();

                        college.assignLecturerToCommittee(lecturerName, committeeName);
                        System.out.println("[Success] Assigned successfully.");
                        break;

                    case 4:
                        System.out.print("Please provide the name of the lecturer: ");
                        lecturerName = scanner.nextLine();
                        System.out.print("Please provide the name of the committee: ");
                        committeeName = scanner.nextLine();

                        college.updateHeadOfCommittee(lecturerName, committeeName);
                        System.out.println("[Success] Head of committee updated successfully.");
                        break;

                    case 5:
                        System.out.print("Please provide the name of the lecturer: ");
                        lecturerName = scanner.nextLine();
                        System.out.print("Please provide the name of the committee: ");
                        committeeName = scanner.nextLine();

                        college.removeLecturerFromCommittee(lecturerName, committeeName);
                        System.out.println("[Success] Lecturer removed from committee.");
                        break;

                    case 6:
                        System.out.print("Please provide the name of the department you wish to add: ");
                        departmentName = scanner.nextLine();
                        System.out.print("Please provide the student count: ");
                        count = scanner.nextInt();
                        scanner.nextLine(); // buffer

                        college.addDepartment(departmentName, count);
                        System.out.println("[Success] Department " + departmentName + " was successfully added.");
                        break;

                    case 7:
                        System.out.print("Please provide the name of the lecturer: ");
                        lecturerName = scanner.nextLine();
                        System.out.print("Please provide the name of the department: ");
                        departmentName = scanner.nextLine();

                        college.assignLecturerToDepartment(lecturerName, departmentName);
                        System.out.println("[Success] Lecturer assigned to department.");
                        break;

                    case 8:
                        System.out.println("The average wage of all lecturers across the college is: " + college.getAverageWage());
                        break;

                    case 9:
                        System.out.print("Please provide department name: ");
                        departmentName = scanner.nextLine();
                        double avg = college.getDepartmentAverageWage(departmentName);
                        System.out.println("The average wage of lecturers in " + departmentName + " is: " + avg);
                        break;

                    case 10:
                        String lecturersDetails = college.getAllLecturersDetails();
                        if (lecturersDetails.isEmpty()) {
                            System.out.println("[Error] No lecturers found.");
                        } else {
                            System.out.println(lecturersDetails);
                        }
                        break;

                    case 11:
                        String committeesDetails = college.getAllCommitteesDetails();
                        if (committeesDetails.isEmpty()) {
                            System.out.println("[Error] No committees found.");
                        } else {
                            System.out.println(committeesDetails);
                        }
                        break;

                    case 12:
                        //System.out.println("Provide the name of the lecturer: ");
                        //lecturerName = scanner.nextLine();
                        //System.out.println("Provide the name of article: ");
                        //String articleName = scanner.nextLine();

                        //if (college.getLecturerByName(lecturerName) == null){
                            //System.out.println("[Error] Lecturer not found.");
                            //break;
                        //}
                        //Lecturer lecturer = college.getLecturerByName(lecturerName);
                        //if (lecturer instanceof Dr){
                            //((Dr) lecturer).addArticle(articleName);
                        //}
                        // TODO: וידוא בעזרת instanceof שהמרצה הוא מופע של Dr, ביצוע Casting, והפעלת addArticle.
                        break;

                    case 13:
                        // TODO: קליטת שמותיהם של 2 דוקטורים/פרופסורים שתרצו להשוות ביניהם.
                        // TODO: שליפת שני האובייקטים ו-Casting מתאים ל-Dr.
                        // TODO: קריאה למתודת compareTo (ההשוואה הטבעית שממשנו ב-Dr) והדפסת התוצאה המתאימה.
                        break;

                    case 14:
                        // TODO: קליטת שמותיהן של 2 ועדות שתרצו להשוות ביניהן ושליפתן מתוך college.
                        // TODO: הצגת תת-תפריט לקליטת סוג הקריטריון (1 - לפי כמות חברים, 2 - לפי סך מאמרים).
                        // TODO: יצירת מופע של ה-Comparator המתאים (CompareCommitteeByMembers או CompareCommitteeByArticles).
                        // TODO: הפעלת מתודת compare של אותו Comparator והדפסת התוצאה למשתמש.
                        break;

                    case 15:
                        System.out.println("Provide the name of the committee you want to clone: ");
                        committeeName = scanner.nextLine();
                        college.cloneCommittee(committeeName);
                        System.out.println("Committee " + committeeName + " was duplicated successfully.");
                        break;

                    default:
                        System.out.println("--- Action Failed ---\nInput out of range. Must be between 0 and 15.");
                }
            } catch (MemberAlreadyInCommitteeException e) {
                System.out.println("--- Action Failed (Committee Membership Error) ---\n" + e.getMessage());
            } catch (ManagementException e) {
                System.out.println("--- Action Failed (System Error) ---\n" + e.getMessage());
            //} catch (CloneNotSupportedException e) {
                //System.out.println("--- Action Failed (Cloning Error) ---\n" + e.getMessage());
            } catch (Exception e) {
                System.out.println("--- Unexpected Error ---\n" + e.getMessage());
            }

            if (choice != 0) {
                System.out.println("\nPress enter to continue");
                scanner.nextLine();
            }

        } while (choice != 0);

        scanner.close();
    }
}