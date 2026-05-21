public class Committee {
    private static Committee[] allCommittees = new Committee[2];
    private static int committeeCount = 0;
    private String name;
    private Lecturer[] members = new Lecturer[2];;
    private Lecturer headOfCommittee;


    public Committee(String name,String headOfCommittee){
        this.name = name;
        //work in progress

        }

    }

public boolean setHeadOfCommittee(Lecturer headOfCommittee) {
    if (headOfCommittee.getTitle() == Title.DR || headOfCommittee.getTitle() == Title.PROF) {

        for (int i = 0; i < committeeCount; i++) {
            if (allCommittees[i].getHeadOfCommittee() == headOfCommittee) {
                allCommittees[i].setHeadOfCommitteeDirectly(null);
                System.out.println("Head of committee moved from" + allCommittees[i].getName() + "to current committee");
                break;
            }
        }
        this.headOfCommittee = headOfCommittee;
        return true;
    }
    System.out.println("error : has to be prof or Dr");
    return false;
}

// in case of changing to NULL ONLY use this
private void setHeadOfCommitteeDirectly(Lecturer headOfCommittee) {
    this.headOfCommittee = headOfCommittee;
}

    public String getName() {return name;}
    public Lecturer[] getMembers() {return members;}
    public String getHeadOfCommittee() {return headOfCommittee;}
}