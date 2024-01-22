
package testBanque;

import service.Compte;
import service.IServiceBanque;
import service.ServiceBanqueImpl;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        new Application().start();
    }

    private void start() {
        System.out.println("start application");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the account code");
        Long code = scanner.nextLong();
        System.out.println("Enter the account balance");
        double solde = scanner.nextDouble();
        Compte compte = new Compte(code, solde);
        IServiceBanque serviceBanque = new ServiceBanqueImpl();

        // if we use call

        // before will be here
        serviceBanque.addCompte(compte);
        // after will be here

        while (true) {
            System.out.println("Enter the operation to perform");
            System.out.println("1- Deposit");
            System.out.println("2- Withdraw");
            System.out.println("3- Consult");
            System.out.println("4- Exit");
            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Enter the amount to deposit");
                    double montant = scanner.nextDouble();
                    serviceBanque.verser(code, montant);
                    break;
                case 2:
                    System.out.println("Enter the amount to withdraw");
                    montant = scanner.nextDouble();
                    serviceBanque.retirer(code, montant);
                    break;
                case 3:
                    System.out.println("Your balance is " +
                            serviceBanque.consulter(code));
                    break;
                case 4: {
                    System.out.println("end application");
                    System.exit(0);
                }
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }

}
