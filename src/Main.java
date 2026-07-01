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
                        System.out.println("Provide the name of the lecturer: ");
                        lecturerName = scanner.nextLine();
                        System.out.println("Provide the name of article: ");
                        String articleName = scanner.nextLine();

                        Lecturer lecturer = college.getLecturerByName(lecturerName);

                        if (lecturer == null) {
                            System.out.println("[Error] Lecturer not found.");
                            break;
                        }

                        if (lecturer instanceof Dr) {
                            ((Dr) lecturer).addArticle(articleName);
                            System.out.println("[Success] Article added to lecturer.");
                            break;
                        }

                        System.out.println("[Error] Lecturer has to be a Doctor.");
                        break;

                    case 13:
                        System.out.println("Provide the name of the first lecturer you want to compare: ");
                        String firstName = scanner.nextLine();
                        System.out.println("Provide the name of the second lecturer you want to compare: ");
                        String secondName = scanner.nextLine();
                        Lecturer firstLecturerName = college.getLecturerByName(firstName);
                        Lecturer secondLecturerName = college.getLecturerByName(secondName);
                        if (firstLecturerName == null || secondLecturerName == null) {
                            System.out.println("[Error] One or both lecturers not found.");
                            break;
                        }

                        if (!(firstLecturerName instanceof Dr) || !(secondLecturerName instanceof Dr)) {
                            System.out.println("[Error] Both lecturers must be at least a Doctor to compare articles.");
                            break;
                        }
                        Dr dr1 = (Dr) firstLecturerName;
                        Dr dr2 = (Dr) secondLecturerName;
                        int articlesDifference = dr1.compareTo(dr2);
                        if (articlesDifference > 0){
                            System.out.println( firstName +"has "+ articlesDifference+ "more articles than "+ secondName+ ".");
                        }
                        else if (articlesDifference < 0 ){
                            System.out.println( secondName +"has " + -(articlesDifference) + "more articles than "+ firstName+ ".");
                        }
                        else {
                            System.out.println( "Both "+firstName+ " and "+ secondName +" have "+ dr1.articlesCount + " articles.");
                        }
                        break;

                    case 14:
                        System.out.println("Provide the name of the first Committee you want to compare: ");
                        String firstCommittee = scanner.nextLine();
                        System.out.println("Provide the name of the second Committee you want to compare: ");
                        String secondCommittee = scanner.nextLine();
                        Committee firstCommitteeName = college.getCommitteeByName(firstCommittee);
                        Committee secondCommitteeName = college.getCommitteeByName(secondCommittee);

                        if (firstCommitteeName == null || secondCommitteeName == null) {
                            System.out.println("[Error] One or both Committees not found.");
                            break;
                        }
                        System.out.println("---------------------------------\n" +
                                           "1.compare by members?\n" +
                                           "2.compare by sum of articles?\n" +
                                           "---------------------------------");
                        int option = scanner.nextInt();
                        Committee com1 = (Committee) firstCommitteeName;
                        Committee com2 = (Committee) secondCommitteeName;
                        if (option == 1){

                        }

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