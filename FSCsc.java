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
// import everything
// This file is the comment file for Program 5 : FSC Sharing is Caring.

// import everything :
import java.util.*;

public class FSCsc {

    // main method :
    public static void main(String[] args) {

        // Create Scanner : variable name = in
        Scanner in = new Scanner(System.in);

        // Create BST
        FSCscBST bst = new FSCscBST();

        // First Line of the program will be seed range, need to get from terminal.
        Random rng = new Random(in.nextInt());

        // Now we have the range of the seed, make a variable that will
        // get a pseudo random number from rng.

        // The next line of code contains two integers :
        // x coordinate and y coordinate respectively representing
        // where the node in the BST is on a xy plane.
        // First we need to make a reference var that points to the nodes in BST.
        // ⬇️
        int dX = in.nextInt();
        int dY = in.nextInt();


        FSCstudent currentNode = new FSCstudent();

        // Finally the last input will be the number of commands that need to run
        int numCmd = in.nextInt();

        // Make sure to eat the new line created by nextInt to avoid error
        in.nextLine();

        // Need to loop through commands given by user
        // Because we know the amount of iteration make a for loop
        for (int i = 0; i < numCmd; i++) {

            // Make a String var that reads the command from user
            String cmd = in.nextLine();

            // Make a String array to split all information in the line,
            // split by spaces : " ".
            String[] cmdSplit = cmd.split(" ");

            // Make if else statements to read in and execute the right command
            if (cmdSplit[0].equals("JOIN")) {
                /*
                    This condition statement will add a new node into the BST.
                    Each node will have : MAC address, student's full name,
                    broadcast range (maximum range of their device), and the device's
                    initial X and Y coordinates.

                    IF (they are not in the BST) {
                        Add them to the BST
                    }

                    ELSE {
                        Print error msg.
                    }
                */

                if (bst.search(Integer.parseInt(cmdSplit[1]))) {
                    System.out.println("Cannot Perform JOIN Command:");
                    System.out.printf("\tMAC %d, %s %s - already a participant in the FSC Sharing is Caring system.\n",
                            Integer.parseInt(cmdSplit[1]),
                            cmdSplit[2],
                            cmdSplit[3]
                    );
                    continue;
                }

                FSCstudent newNode = new FSCstudent(
                        cmdSplit[2],
                        cmdSplit[3],
                        Integer.parseInt(cmdSplit[1]),
                        Integer.parseInt(cmdSplit[4]),
                        Integer.parseInt(cmdSplit[5]),
                        Integer.parseInt(cmdSplit[6])
                );

                bst.insert(newNode);

                System.out.printf("%s %s, MAC %d, joined the FSC Sharing is Caring system.\n",
                        newNode.getFirstName(),
                        newNode.getLastName(),
                        newNode.getMACnumber()
                );

            }
            else if (cmdSplit[0].equals("FINDMAC")) {
                /*
                    Input : One MAC address #

                    This condition statement will find a device's MAC address

                    IF (MAC address found) {
                        print : MAC addy, student's full name, coordinates,
                                and number of devices it is connected too.
                    }

                    ELSE {
                        Print error msg.
                    }
                */

                String stringLink;

                if (bst.findNode(Integer.parseInt(cmdSplit[1])) != null ) {
                    FSCstudent found =  bst.findNode(Integer.parseInt(cmdSplit[1]));

                    if (found.getNumLinks() > 1 || found.getNumLinks() == 0) {
                        stringLink = "Links";
                    }
                    else {
                        stringLink = "Link";
                    }

                    System.out.printf("Found:  MAC %d, %s %s, currently at position (%d, %d), %d %s\n",
                            found.getMACnumber(),
                            found.getFirstName(),
                            found.getLastName(),
                            found.getX(),
                            found.getY(),
                            found.getNumLinks(),
                            stringLink
                    );
                }

                else {
                    System.out.printf("MAC %d not found in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[1]));
                }

            }
            else if (cmdSplit[0].equals("LINK")) {
                /*
                    Input : Two MAC address #s

                    This condition statement will link two devices together

                    IF (MAC addresses are in system AND devices not linked to each other) {
                        Add the MAC addresses of each MAC address to each others
                        deviceLinks linked list (SHOULD BE SORTED BY MAC #).
                    }

                    ELSE {
                        Print error msg.
                    }
                */

                FSCstudent mac1Exist = bst.findNode(Integer.parseInt(cmdSplit[1]));
                FSCstudent mac2Exist = bst.findNode(Integer.parseInt(cmdSplit[2]));

                if (mac1Exist == null && mac2Exist == null) {

                    System.out.println("Cannot Perform LINK Command:");
                    System.out.printf("\tMAC %d - This MAC Address is not in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[1]));
                    System.out.printf("\tMAC %d - This MAC Address is not in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[2]));
                }

                else if (mac1Exist == null) {

                    System.out.println("Cannot Perform LINK Command:");
                    System.out.printf("\tMAC %d - This MAC Address is not in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[1]));

                }
                else if (mac2Exist == null) {

                    System.out.println("Cannot Perform LINK Command:");
                    System.out.printf("\tMAC %d - This MAC Address is not in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[2]));

                }
                else if (Integer.parseInt(cmdSplit[1]) == Integer.parseInt(cmdSplit[2])) {

                    System.out.println("Cannot Perform LINK Command:");
                    System.out.printf("\tMAC %d cannot link to itself.\n", Integer.parseInt(cmdSplit[1]));

                }


                else if (mac1Exist != null && mac2Exist != null) {

                    boolean isMAC2InMAC1LINK = mac1Exist.getAllowedMACs().search(Integer.parseInt(cmdSplit[2]));
                    boolean isMAC1InMAC2LINK = mac2Exist.getAllowedMACs().search(Integer.parseInt(cmdSplit[1]));

                    if (!isMAC2InMAC1LINK && !isMAC1InMAC2LINK) {

                        mac1Exist.getAllowedMACs().insert(Integer.parseInt(cmdSplit[2]));
                        mac2Exist.getAllowedMACs().insert(Integer.parseInt(cmdSplit[1]));

                        mac1Exist.setNumLinks(mac1Exist.getAllowedMACs().size());
                        mac2Exist.setNumLinks(mac2Exist.getAllowedMACs().size());

                        System.out.printf("MAC %d and MAC %d are now linked.\n", Integer.parseInt(cmdSplit[1]), Integer.parseInt(cmdSplit[2]));
                    }

                    else {

                        System.out.println("Cannot Perform LINK Command:");
                        System.out.printf("\tMAC %d and MAC %d are already linked.\n",  Integer.parseInt(cmdSplit[1]), Integer.parseInt(cmdSplit[2]));

                    }

                }


            }

            else if (cmdSplit[0].equals("UNLINK")) {
                /*
                    Inputs : Two MAC address #s

                    This condition statement is the opposite of LINK

                    IF (exist with in the system AND devices are linked to each other) {
                        Remove each MAC address for each other's deviceLinks linked list.
                    }

                    ELSE {
                        Print error msg.
                    }
                */
                FSCstudent mac1ExistUNLINK = bst.findNode(Integer.parseInt(cmdSplit[1]));
                FSCstudent mac2ExistUNLINK = bst.findNode(Integer.parseInt(cmdSplit[2]));

                if (mac1ExistUNLINK == null && mac2ExistUNLINK == null) {

                    System.out.println("Cannot Perform UNLINK Command:");
                    System.out.printf("\tMAC %d - This MAC Address is not in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[1]));
                    System.out.printf("\tMAC %d - This MAC Address is not in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[2]));

                }

                else if (mac1ExistUNLINK == null) {

                    System.out.println("Cannot Perform UNLINK Command:");
                    System.out.printf("\tMAC %d - This MAC Address is not in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[1]));

                }

                else if (mac2ExistUNLINK == null) {

                    System.out.println("Cannot Perform UNLINK Command:");
                    System.out.printf("\tMAC %d - This MAC Address is not in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[2]));

                }

                /*
                else if (Integer.parseInt(cmdSplit[1]) == Integer.parseInt(cmdSplit[2])) {

                    System.out.println("Cannot Perform UNLINK Command:");
                    System.out.printf("\tMAC %d cannot link to itself.\n", Integer.parseInt(cmdSplit[1]));

                }
                */
                if (mac1ExistUNLINK != null || mac2ExistUNLINK != null) {

                    boolean isMAC2InMAC1LINK = mac1ExistUNLINK.getAllowedMACs().search(Integer.parseInt(cmdSplit[2]));
                    boolean isMAC1InMAC2LINK = mac2ExistUNLINK.getAllowedMACs().search(Integer.parseInt(cmdSplit[1]));

                    if (isMAC2InMAC1LINK && isMAC1InMAC2LINK) {

                        mac1ExistUNLINK.getAllowedMACs().delete(Integer.parseInt(cmdSplit[2]));
                        mac2ExistUNLINK.getAllowedMACs().delete(Integer.parseInt(cmdSplit[1]));

                        mac1ExistUNLINK.setNumLinks(mac1ExistUNLINK.getAllowedMACs().size());
                        mac2ExistUNLINK.setNumLinks(mac2ExistUNLINK.getAllowedMACs().size());

                        System.out.printf("MAC %d and MAC %d are now unlinked.\n", Integer.parseInt(cmdSplit[1]), Integer.parseInt(cmdSplit[2]));

                    }

                    else {

                        System.out.println("Cannot Perform UNLINK Command:");
                        System.out.printf("\tMAC %d and MAC %d are not currently linked.\n",  Integer.parseInt(cmdSplit[1]), Integer.parseInt(cmdSplit[2]));

                    }

                }

            }
            else if (cmdSplit[0].equals("QUIT")) {
                /*
                    Input : One MAC address #

                    This conditional statement means user is quitting FSCsc Program
                    (MUST DELETE device from BST).

                    BEFORE REMOVING FROM BST, must remove the MAC of quitting devices
                    from other devices in the BST that are linked to it.

                    IF (device exists in BST) {
                        Run through every node in the BST and remove the MAC address
                        of the quitting devices in other devices.
                    }

                    ELSE {
                        Print error msg.
                    }
                */

                if (bst.findNode(Integer.parseInt(cmdSplit[1])) != null) {

                    bst.findNode(Integer.parseInt(cmdSplit[1])).getAllowedMACs().removeAllLinks(Integer.parseInt(cmdSplit[1]), bst);

                    System.out.printf("MAC %d has been removed from the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[1]));

                }

                else {
                    System.out.println("Cannot Perform QUIT Command:");
                    System.out.printf("\tMAC %d not found in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[1]));
                }

            }
            else if (cmdSplit[0].equals("SHOWCONNECTIONS")) {
                /*
                    Input = One MAC address

                    This conditional statement will print out the MAC address,
                    student's FULL name, number of links, number of ACTIVE links
                    (active means that the devices are linked an within range), and
                    print out, NUMERICALLY, active MAC addresses and other info for those
                    MAC addresses.

                    IF (MAC address is found) {
                        print out the MAC address,
                        student's FULL name, number of links, number of ACTIVE links
                        (active means that the devices are linked an within range), and
                        print out, NUMERICALLY, active MAC addresses and other info for those
                        MAC addresses.
                    }

                    ELSE {
                        MAC address not found,
                        Print error msg.
                    }
                */

                if (bst.findNode(Integer.parseInt(cmdSplit[1])) != null) {
                    bst.findNode(Integer.parseInt(cmdSplit[1])).getAllowedMACs().showConnections(Integer.parseInt(cmdSplit[1]), bst);
                }

                else {
                    System.out.println("Cannot Perform SHOWCONNECTIONS Command:");
                    System.out.printf("\tMAC %d - This MAC Address is not in the FSC Sharing is Caring system.\n", Integer.parseInt(cmdSplit[1]));

                }
            }
            else if (cmdSplit[0].equals("PRINTMEMBERS")) {
                /*
                    Inputs : none (other than the name)

                    This conditional statement will print out all devices
                    in ascending numerical order as well as info about the devices
                */

                if (!bst.isEmpty()) {
                    System.out.println("Members of FSC Sharing is Caring System:");
                    bst.printInorder();
                }
                else {
                    System.out.println("Cannot Perform PRINTMEMBERS Command:");
                    System.out.println("\tThere are currently no participants in the FSC Sharing is Caring system.");
                }

            }

            else if (cmdSplit[0].equals("MOVEDEVICES")) {
                /*
                    Inputs : none (other than the name)

                    This conditional statement will move the devices by changing their
                    X and Y coordinates. It will assign RANDOM X and Y coordinates to
                    EACH node in the BTS tree. Will travel in ascending order (INORDER)
                    and assign first the new X and then new Y coordinate.
                */

                if (!bst.isEmpty()) {
                    bst.moveDevices(rng, dX, dY);
                    System.out.println("All devices successfully moved.");
                }
                else {
                    System.out.println("Cannot Perform MOVEDEVICES Command:");
                    System.out.println("\tThere are currently no participants in the FSC Sharing is Caring system.");

                }
            }
        // end of for loop
        }
    // end of main method
    }
// end of class
}