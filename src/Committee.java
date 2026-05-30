public class Committee {
    private String name;
    private Lecturer headOfCommittee;
    private Lecturer[] lecturers = new Lecturer[2];
    private int lecturerCount = 0;

    public Committee(String name, Lecturer headOfCommittee) {
        setName(name);
        setHeadOfCommittee(headOfCommittee);
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    public String getName() { return name; }

    private boolean setHeadOfCommittee(Lecturer lecturer) {
        if (lecturer.getTitle() == Title.DR || lecturer.getTitle() == Title.PROFESSOR) {
            headOfCommittee = lecturer;
            headOfCommittee.addCommittee(this);
            return true;
        }
        return false;
    }
    public Lecturer getHeadOfCommittee() { return headOfCommittee; }

    public String toString() {
        String str = "Name: " + name + "\n" +
                "   Head of committee: " + headOfCommittee.getName() + "\n";

        if (lecturerCount > 0) {
            str += "    Lecturers: \n";
            for (int i = 0; i < lecturerCount; i++) {
                str += "        " + lecturers[i].getName() + "\n";
            }
        }
        return str;
    }

    private boolean hasLecturer(Lecturer lecturer) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i] == lecturer) return true;
        }
        return false;
    }

    public String updateHeadOfCommittee(Lecturer newHead) {
        String errors = "";
        if (newHead == headOfCommittee) {
            errors += "- Lecturer is already head of the committee.\n";
        }
        if (newHead.getTitle() != Title.DR && newHead.getTitle() != Title.PROFESSOR) {
            errors += "- Lecturer does not meet requirements (DR or PROFESSOR).\n";
        }

        if (!errors.isEmpty()) return errors.trim();

        Lecturer oldHead = headOfCommittee;
        if (hasLecturer(newHead)) {
            removeLecturer(newHead);
        }
        setHeadOfCommittee(newHead);
        oldHead.removeCommittee(this);
        addLecturer(oldHead);
        return "SUCCESS";
    }

    public String addLecturer(Lecturer lecturer) {
        String errors = "";
        if (lecturer == headOfCommittee) {
            errors += "- Lecturer is the head of committee and cannot be in members list.\n";
        }
        if (hasLecturer(lecturer)) {
            errors += "- Lecturer already part of committee.\n";
        }

        if (!errors.isEmpty()) return errors.trim();

        if (lecturerCount == lecturers.length) {
            Lecturer[] newLecturers = new Lecturer[lecturerCount * 2];
            for (int i = 0; i < lecturerCount; i++) {
                newLecturers[i] = lecturers[i];
            }
            lecturers = newLecturers;
        }
        lecturers[lecturerCount] = lecturer;
        lecturerCount++;
        lecturer.addCommittee(this);
        return "SUCCESS";
    }

    public String removeLecturer(Lecturer lecturer) {
        String errors = "";
        if (lecturer == headOfCommittee) {
            errors += "- Cannot remove the Head of Committee. Assign a new Head first.\n";
        }
        if (!hasLecturer(lecturer)) {
            errors += "- Lecturer is not part of committee.\n";
        }

        if (!errors.isEmpty()) return errors.trim();

        int indexToRemove = -1;
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i] == lecturer) {
                indexToRemove = i;
                break;
            }
        }

        for (int i = indexToRemove; i < lecturerCount - 1; i++) {
            lecturers[i] = lecturers[i + 1];
        }
        lecturers[lecturerCount - 1] = null;
        lecturerCount--;

        lecturer.removeCommittee(this);
        return "SUCCESS";
    }
}