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
import java.util.ArrayList;

public class FSCscLinkedDevices {

    private FSCscDevice head;

    public FSCscDevice getHead() {
        return head;
    }
    public void setHead(FSCscDevice head) {}

    public FSCscLinkedDevices() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
    }

    public int size() {
        return size(head);
    }

    private int size(FSCscDevice head) {
        FSCscDevice current = head;

        int count = 0;

        while (current != null) {
            current = current.getNext();
            count++;
        }

        return count;

    }

    public boolean search (int MACnumber) {
        return search(head, MACnumber);
    }

    private boolean search(FSCscDevice head, int MACnumber) {
        FSCscDevice helpPtr = head;
        while (helpPtr != null) {
            if (helpPtr.getMACnumber() == MACnumber) {
                return true;
            }
            helpPtr = helpPtr.getNext();
        }
        return false;
    }

    public FSCscDevice findNode(int MACnumber) {
        return findNode(head, MACnumber);
    }

    private FSCscDevice findNode(FSCscDevice head, int MACnumber) {
        FSCscDevice helpPtr = head;
        while (helpPtr != null) {
            if (helpPtr.getMACnumber() == MACnumber) {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        return null;
    }

    public void PrintList() {
        PrintList(head);
    }

    private void PrintList(FSCscDevice head) {
        FSCscDevice helpPtr = head;
        while (helpPtr != null) {
            System.out.print(helpPtr.toString());
            helpPtr = helpPtr.getNext();
        }
        System.out.println();
    }

    public void insert(int MACnumber) {
        head = insert(head, MACnumber);
    }

    private FSCscDevice insert(FSCscDevice head, int MACnumber) {
        if (head == null || head.getMACnumber() > MACnumber) {
            head = new FSCscDevice(MACnumber, head);
            return head;
        }
        else {
            FSCscDevice helpPtr = head;

            while (helpPtr.getNext() != null) {
                if (helpPtr.getNext().getMACnumber() > MACnumber) {
                    break;
                }
                helpPtr = helpPtr.getNext();
            }
            FSCscDevice newNode = new FSCscDevice(MACnumber, helpPtr.getNext());
            helpPtr.setNext(newNode);
        }
        return head;
    }

    public void delete (int MACnumber) {
        head = delete(head, MACnumber);
    }

    private FSCscDevice delete(FSCscDevice head, int MACnumber) {

        if (!isEmpty()) {

            if (head.getMACnumber() == MACnumber) {
                head = head.getNext();
            }

            else {
                FSCscDevice helpPtr = head;

                while (helpPtr.getNext() != null) {
                    if (helpPtr.getNext().getMACnumber() == MACnumber) {
                        helpPtr.setNext(helpPtr.getNext().getNext());
                        break;
                    }
                    helpPtr = helpPtr.getNext();
                }
            }
            return head;
        }
        return head;
    }

    public void showConnections(int MACnumber, FSCscBST ref) {
        showConnections(head, MACnumber, ref);
    }

    private void showConnections(FSCscDevice head, int MACnumber, FSCscBST ref) {

        FSCstudent sourceNode = ref.findNode(MACnumber);

        if (sourceNode == null) {
            return;
        }

        int pX = sourceNode.getX();
        int pY = sourceNode.getY();
        int range = sourceNode.getBroadcastRange();

        int count = sourceNode.getAllowedMACs().size();
        FSCscDevice helpPtr = head;

        if (count == 0) {

            System.out.printf("MAC %d has no links.\n", MACnumber);

            return;

        }

        System.out.printf("Connections for MAC %d, %s %s, currently at position (%d, %d):\n",
                sourceNode.getMACnumber(),
                sourceNode.getFirstName(),
                sourceNode.getLastName(),
                pX,
                pY
        );
        System.out.printf("\tThere are a total of %d link(s).\n", count);

        int activeLinkCount = 0;

        while (helpPtr != null) {
            FSCstudent temp = ref.findNode(helpPtr.getMACnumber());

            if (withinRange(range, pX, temp.getX(), pY, temp.getY())) {
                activeLinkCount++;
            }

            helpPtr = helpPtr.getNext();

        }

        if (activeLinkCount == 0) {
            System.out.printf("\tThere are NO active links within the broadcast range of %d.\n", range);
        }
        else {
            System.out.printf("\tThere are %d active link(s) within the broadcast range of %d:\n", activeLinkCount, range);
        }

        helpPtr = head;

        while (helpPtr != null) {

            if (helpPtr.getMACnumber() != MACnumber) {

                FSCstudent temp = ref.findNode(helpPtr.getMACnumber());

                if (temp != null) {

                    if (withinRange(range, pX, temp.getX(), pY, temp.getY())) {

                        System.out.printf("\t\tMAC %d, %s %s, currently at position (%d, %d)\n",
                            temp.getMACnumber(),
                            temp.getFirstName(),
                            temp.getLastName(),
                            temp.getX(),
                            temp.getY()
                        );

                    }
                }
            }

            helpPtr = helpPtr.getNext();

        }

    }

    private boolean withinRange(int broadcastRange, int pX, int qX, int pY, int qY) {

        int x = (pX - qX) * (pX - qX);
        int y = (pY - qY) * (pY - qY);
        int euclidean = (int) Math.sqrt(x + y);

        return euclidean <= broadcastRange;

    }

    public void removeAllLinks(int MACnumber, FSCscBST ref) {

        removeAllLinks(MACnumber, ref, ref.findNode(MACnumber));;

    }

    private void removeAllLinks(int MACnumber, FSCscBST ref, FSCstudent ref2) {

        if (ref2 == null) {
            return;
        }

        ArrayList<Integer> listOfMACs = new ArrayList<>();
        FSCscDevice helpPtr = ref2.getAllowedMACs().getHead();

        while (helpPtr != null) {
            listOfMACs.add(helpPtr.getMACnumber());
            helpPtr = helpPtr.getNext();
        }

        for (int i  = 0; i < listOfMACs.size(); i++) {
            ref.findNode(listOfMACs.get(i)).getAllowedMACs().delete(MACnumber);
            ref.findNode(listOfMACs.get(i)).setNumLinks(ref.findNode(listOfMACs.get(i)).getNumLinks() - 1);
        }

        ref2.getAllowedMACs().clear();
        ref2.setNumLinks(0);

        ref.delete(MACnumber);

    }

}
