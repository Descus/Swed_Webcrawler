package swed4.strategy;

public class TextComparisonStrategy implements ComparisonStrategy {
    private static final String regex = "<script\\b[^<]*(?:(?!</script>)<[^<]*)*</script\\s*>|(?i)<[^>]*>|<style((.|\\n|\\r)*?)</style>";
    @Override
    public boolean Compare(String string1, String string2) {
        if (string1 == null || string2 == null) return false;
        String target1 = string1.replaceAll(regex, " ").replaceAll("\\s+", " ").trim();
        String target2 = string2.replaceAll(regex, " ").replaceAll("\\s+", " ").trim();
        return target1.equals(target2);
    }
}
