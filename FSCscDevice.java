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
public class FSCscDevice {

    private int MACnumber;
    private FSCscDevice next;

    public FSCscDevice() {
        MACnumber = 0;
        next = null;
    }

    public FSCscDevice(int MACnumber) {
        this.MACnumber = MACnumber;
        next = null;
    }

    public FSCscDevice(int MACnumber, FSCscDevice next) {
        this.MACnumber = MACnumber;
        this.next = next;
    }

    public int getMACnumber() {
        return MACnumber;
    }

    public FSCscDevice getNext() {
        return next;
    }

    public void setNext(FSCscDevice next) {
        this.next = next;
    }

    public void setMACnumber(int MACnumber) {
        this.MACnumber = MACnumber;
    }

}
