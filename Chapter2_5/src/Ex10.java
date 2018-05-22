/**
 * @author Evan
 * @version 1.0 21/05/18 10:14
 */
public class Ex10 {
    public static void main(String[] args) {
        Version v1 = new Version(115, 10, 10),
                v2 = new Version(115,1,1);
        System.out.println(v1 + "\n" + v2);
        System.out.println(v1.compareTo(v2));
    }
}

class Version implements Comparable<Version> {
    private int majorVersion;
    private int minorVersion;
    private int phaseVersion;

    public Version(int major, int minor, int phase) {
        this.majorVersion = major;
        this.minorVersion = minor;
        this.phaseVersion = phase;
    }

    @Override
    public int compareTo(Version that) {
        int majorCompare = Integer.compare(this.majorVersion,
                that.majorVersion);
        if (majorCompare != 0) {
            return majorCompare;
        }

        int minorCompare = Integer.compare(this.minorVersion,
                that.minorVersion);
        if (minorCompare != 0) {
            return minorCompare;
        }

        return Integer.compare(this.phaseVersion,
                that.phaseVersion);
    }

    @Override
    public String toString() {
        return String.valueOf(majorVersion) + "." + String.valueOf(minorVersion) + "."
                + String.valueOf(phaseVersion);
    }
}
