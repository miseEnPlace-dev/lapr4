package isep.ui;

/**
 * Development Team View
 *
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable {
    public DevTeamUI() {}

    public void run() {
        System.out.println("\n");
        System.out.printf("Development Team:\n");
        System.out.printf("\t André Barros - 1211299@isep.ipp.pt \n");
        System.out.printf("\t Carlos Lopes - 1211277@isep.ipp.pt \n");
        System.out.printf("\t Ricardo Moreira - 1211285@isep.ipp.pt \n");
        System.out.printf("\t Tomás Lopes - 1211289@isep.ipp.pt \n");
        System.out.printf("\t Tomás Russo - 1211288@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
