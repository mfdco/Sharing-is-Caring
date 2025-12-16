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
import java.util.*;
public class FSCscBST {

    private FSCstudent root;

    public FSCscBST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public FSCstudent getRoot() {
        return root;
    }

    public void setRoot(FSCstudent root) {
        this.root = root;
    }

    public boolean search(int MACnumber) {
        return search(root, MACnumber);
    }

    private boolean search(FSCstudent root, int MACnumber) {
        if (root == null) {
            return false;
        } else {
            if (MACnumber == root.getMACnumber()) {
                return true;
            } else if (MACnumber < root.getMACnumber()) {
                return search(root.getLeft(), MACnumber);
            } else {
                return search(root.getRight(), MACnumber);
            }
        }
    }

    public void insert(FSCstudent newNode) {
        root = insert(root, newNode);
    }

    private FSCstudent insert(FSCstudent root, FSCstudent newNode) {
        if (root == null) {
            return newNode;
        }
        else {
            if (newNode.getMACnumber() > root.getMACnumber()) {
                FSCstudent temp = insert(root.getRight(), newNode);
                root.setRight(temp);
            }
            else {
                FSCstudent temp = insert(root.getLeft(), newNode);
                root.setLeft(temp);
            }
        }
        return root;
    }

    public void printInorder() {
        printInorder(root);
    }

    private void printInorder(FSCstudent root) {

        String stringLink;

        if (root != null) {

            printInorder(root.getLeft());
            if (root.getNumLinks() > 1 || root.getNumLinks() == 0) {
                stringLink = "Links";
            }
            else {
                stringLink = "Link";
            }
            System.out.printf("\tMAC %d, %s %s, currently at position (%d, %d), %d %s\n",
                    root.getMACnumber(),
                    root.getFirstName(),
                    root.getLastName(),
                    root.getX(),
                    root.getY(),
                    root.getNumLinks(),
                    stringLink
            );
            printInorder(root.getRight());
        }
    }

    public void moveDevices(Random rng, int X, int Y) {
        moveDevices(root, rng, X, Y);
    }

    private void moveDevices(FSCstudent root, Random rng, int X, int Y) {
        if (root != null) {
            moveDevices(root.getLeft(), rng, X, Y);
            root.setX(rng.nextInt(X));
            root.setY(rng.nextInt(Y));
            moveDevices(root.getRight(), rng, X, Y);
        }
    }

    public void delete(int MACnumber) {
        root = delete(root, MACnumber);
    }

    private FSCstudent delete(FSCstudent root, int MACnumber) {
        FSCstudent node2delete, newnode2delete, node2save, parent;
        int saveValue;
        String saveFName;
        String saveLName;
        int saveX;
        int saveY;
        int broadcast;

        node2delete = findNode(root, MACnumber);

        if (node2delete == null) {
            return root;
        }

        parent = parent(root, node2delete);

        if (isLeaf(node2delete)) {
            if (parent == null) {
                return null;
            }

            if (MACnumber < parent.getMACnumber()) {
                parent.setLeft(null);
            }
            else {
                parent.setRight(null);
            }
            return root;
        }

        if (hasOnlyLeftChild(node2delete)) {
            if (parent == null) {
                return node2delete.getLeft();
            }
            if (MACnumber < parent.getMACnumber()) {
                parent.setLeft(parent.getLeft().getLeft());
            }
            else {
                parent.setRight(parent.getRight().getLeft());
            }

            return root;
        }

        if (hasOnlyRightChild(node2delete)) {
            if (parent == null) {
                return node2delete.getRight();
            }
            if (MACnumber < parent.getMACnumber()) {
                parent.setLeft(parent.getLeft().getRight());
            }
            else {
                parent.setRight(parent.getRight().getRight());
            }
            return root;
        }

        newnode2delete = minNode(node2delete.getRight());

        saveValue = newnode2delete.getMACnumber();
        saveFName = newnode2delete.getFirstName();
        saveLName = newnode2delete.getLastName();
        saveX = newnode2delete.getX();
        saveY = newnode2delete.getY();
        broadcast = newnode2delete.getNumLinks();

        root = delete(root, saveValue);

        node2delete.setMACnumber(saveValue);
        node2delete.setFirstName(saveFName);
        node2delete.setLastName(saveLName);
        node2delete.setX(saveX);
        node2delete.setY(saveY);
        node2delete.setBroadcastRange(broadcast);

        return root;

    }

    public FSCstudent findNode(int MACaddress) {
         return findNode(root, MACaddress);
    }

    private FSCstudent findNode(FSCstudent root, int MACaddress) {
        if (root != null) {
            if (MACaddress == root.getMACnumber()) {
                return root;
            }
            else if (MACaddress < root.getMACnumber()) {
                return findNode(root.getLeft(), MACaddress);
            }
            else {
                return findNode(root.getRight(), MACaddress);
            }
        }
        else {
            return null;
        }
    }

    public FSCstudent minNode(FSCstudent root) {
        if (root == null) {
            return null;
        }
        else {
            if (root.getLeft() == null) {
                return root;
            }
            else {
                return minNode(root.getLeft());
            }
        }
    }

    public FSCstudent maxNode(FSCstudent root) {
        if (root == null) {
            return null;
        }
        else {
            if (root.getRight() == null) {
                return root;
            }
            else {
                return maxNode(root.getRight());
            }
        }
    }

    public FSCstudent parent(FSCstudent child) {
        return parent(root, child);
    }

    private FSCstudent parent(FSCstudent root, FSCstudent child) {
        if (root == null || root == child) {
            return null;
        }

        if (root.getLeft() == child || root.getRight() == child) {
            return root;
        }

        if (child.getMACnumber() < root.getMACnumber()) {
            return parent(root.getLeft(), child);
        }

        else if (child.getMACnumber() > root.getMACnumber()) {
            return parent(root.getRight(), child);
        }

        else {
            return null;
        }
    }

    public boolean isLeaf(FSCstudent root) {
        return (root.getLeft() == null && root.getRight() == null);
    }

    public boolean hasOnlyLeftChild(FSCstudent root) {
        return (root.getLeft() != null && root.getRight() == null);
    }

    public boolean hasOnlyRightChild(FSCstudent root) {
        return (root.getRight() != null && root.getLeft() == null);
    }


}
