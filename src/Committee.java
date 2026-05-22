public class Committee {
    private String name;
    private Lecturer headOfCommittee;
    private Lecturer[] lecturers = new Lecturer[2];
    private int lecturerCount = 0;

    public Committee(String name,Lecturer headOfCommittee){
        setName(name);
        setHeadOfCommittee(headOfCommittee);
        }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    public String getName() {
        return name;
    }

    private boolean hasLecturer(Lecturer lecturer) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i] == lecturer) {
                return true;
            }
        }
        return false;
    }

    private boolean setHeadOfCommittee(Lecturer lecturer) {
        if (lecturer.getTitle() == Title.DR || lecturer.getTitle() == Title.PROF) {
            headOfCommittee = lecturer;
            headOfCommittee.addCommittee(this);
            return true;
        }
        return false;
    }
    private boolean updateHeadOfCommittee(Lecturer newHead) {
        if (newHead == headOfCommittee) {
            return false;
        }
        if (newHead.getTitle() != Title.DR && newHead.getTitle() != Title.PROF) {
            return false;
        }
        addLecturer(headOfCommittee);
        headOfCommittee = newHead;
        headOfCommittee.addCommittee(this);
        if (hasLecturer(newHead)) {
            removeLecturer(newHead);
        }
        return true;
    }
    public Lecturer getHeadOfCommittee() {
        return headOfCommittee;
    }

    public boolean addLecturer(Lecturer lecturer) {
        if (hasLecturer(lecturer)) {
            return false;
        }
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
        return true;
    }
    public boolean removeLecturer(Lecturer lecturer) {
        if (!hasLecturer(lecturer)) {
            return false;
        }
        int counter = 0;
        for (int i = 0; i < lecturerCount; i++ ) {
            if (lecturers[i] == lecturer) {
                counter = i;
                break;
            }
        }
        for (int i = counter; i < lecturerCount - 1; i++) {
            lecturers[i] = lecturers[i + 1];
        }
        lecturerCount--;
        lecturer.removeCommittee(this);
        return true;
    }
    public Lecturer[] getLecturers() {
        return lecturers;
    }
    public int getLecturerCount() {
        return lecturerCount;
    }
}
