import java.util.Comparator;

public class CompareCommitteeByArticles implements Comparator<Committee> {

    public int compare(Committee c1, Committee c2) {
        return c1.getTotalArticles() - c2.getTotalArticles();
    }
}