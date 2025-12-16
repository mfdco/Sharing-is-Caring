/*HONOR CODE POLICY: “I will practice academic and personal integrity and excellence of character
and expect the same from others.”
As an academic community, Florida Southern College is firmly committed to honor and integrity in the
pursuit of knowledge. Therefore, as members of this academic community, all students acknowledge
responsibility for their actions and commit themselves to the highest standards of integrity, thereby making
a covenant with the College and all members of the academic community not to engage in any form of
academic dishonesty as defined immediately below. This covenant—Florida Southern College’s Honor
Code—lies at the heart of learning, inquiry, and the critical exploration and dissemination of ideas. Through
it, students affirm the authorship of their own work, and when work is not their own, appropriately attribute
ideas, concepts, data, words, and artistic and creative expressions. Formal subscription to the Honor Code
by all students assures the academic community that breaches of academic integrity will not be tolerated
and fosters learning at its best. */
// Marcos Felipe de Castro Ornelas
// ID : 1308576
// Email : mornelas@mocs.flsouthern.edu
public class FSCstudent {

    private String firstName;
    private String lastName;
    private int MACnumber;
    private int broadcastRange;
    private int x;
    private int y;
    private int numLinks;
    private FSCscLinkedDevices allowedMACs;
    private FSCstudent right;
    private FSCstudent left;

    public FSCstudent() {
        left = right = null;
        allowedMACs = new FSCscLinkedDevices();
    }

    public FSCstudent(String firstName, String lastName, int MACnumber, int broadcastRange, int  x, int y) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.MACnumber = MACnumber;
        this.broadcastRange = broadcastRange;
        this.x = x;
        this.y = y;

        allowedMACs = new FSCscLinkedDevices();
        left = right = null;
        numLinks = 0;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getMACnumber() {
        return MACnumber;
    }
    public void setMACnumber(int MACnumber) {
        this.MACnumber = MACnumber;
    }
    public int getBroadcastRange() {
        return broadcastRange;
    }
    public void setBroadcastRange(int broadcastRange) {
        this.broadcastRange = broadcastRange;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getNumLinks() {
        return numLinks;
    }
    public void setNumLinks(int numLinks) {
        this.numLinks = numLinks;
    }
    public FSCscLinkedDevices getAllowedMACs() {
        return allowedMACs;
    }
    public void setAllowedMACs(FSCscLinkedDevices allowedMACs) {
        this.allowedMACs = allowedMACs;
    }
    public FSCstudent getRight() {
        return right;
    }
    public void setRight(FSCstudent right) {
        this.right = right;
    }
    public FSCstudent getLeft() {
        return left;
    }
    public void setLeft(FSCstudent left) {
        this.left = left;
    }
}
