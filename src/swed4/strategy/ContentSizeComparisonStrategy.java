package swed4.strategy;

public class ContentSizeComparisonStrategy implements ComparisonStrategy{
    @Override
    public boolean Compare(String string1, String string2) {
        if (string1 == null || string2 == null) return false;
        return string1.length() == string2.length();
    }
}
