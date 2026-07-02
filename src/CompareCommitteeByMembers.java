import java.util.Comparator;

public class CompareCommitteeByMembers implements Comparator<Committee> {

    public int compare(Committee c1, Committee c2) {
        return c1.getMembersCount() - c2.getMembersCount();
    }
}