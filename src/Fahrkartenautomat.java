import java.util.Scanner;
import java.util.stream.DoubleStream;

class Fahrkartenautomat {
    public static void main(String[] args) {

        Scanner tastatur = new Scanner(System.in);

        double zuZahlenderBetrag;
        double eingezahlterGesamtbetrag;
        double eingeworfeneMuenze;
        double rueckgabebetrag;
        double nochZuZahlen;

        // Geldbetrag eingeben
        System.out.print("Zu zahlender Betrag (Euro): ");
        zuZahlenderBetrag = tastatur.nextDouble();

        // check if number is positive, if not set to one
        if (zuZahlenderBetrag < 0) {
            System.out.println("Fehlerhafte Eingabe - Ticketpreis wird auf 1 Euro gesetzt");
            zuZahlenderBetrag = 1;
        }

        // Anzahl der Tickets eingeben
        System.out.print("Anzahl der Tickets: ");
        int anzahlTickets = tastatur.nextInt();

        //check if number is 1-10, if not set to 1
        while(anzahlTickets < 1 || anzahlTickets > 10) {
            System.out.println(">> Wählen Sie bitte eine Anzahl von 1 bis 10 Tickets aus. <<");
            System.out.print("Anzahl der Tickets: ");
            anzahlTickets = tastatur.nextInt();
        }

        // Gesamtbetrag berechnen
        zuZahlenderBetrag = zuZahlenderBetrag * anzahlTickets;

        // Geldeinwurf
        eingezahlterGesamtbetrag = 0.0;
        nochZuZahlen = 0.0;
        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
            nochZuZahlen = zuZahlenderBetrag - eingezahlterGesamtbetrag;
            System.out.println("Noch zu zahlen: " + String.format("%.2f", nochZuZahlen));
            System.out.print("Eingabe (mind. 5 Cent, höchstens 20 Euro): ");
            eingeworfeneMuenze = tastatur.nextDouble();

            //validate input to be 5c, 10c, 20c, 50c, 1€, 2€, 5€, 10€, 20€
            double[] validCoins = {0.05, 0.10, 0.20, 0.50, 1, 2, 5, 10, 20};
            double finalEingeworfeneMuenze = eingeworfeneMuenze;
            if(DoubleStream.of(validCoins).anyMatch(x -> x == finalEingeworfeneMuenze)) {
                eingezahlterGesamtbetrag = eingezahlterGesamtbetrag + eingeworfeneMuenze;
            } else {
                System.out.println(">> Kein gültiges Zahlungsmittel");
            }
        }

        // Fahrscheinausgabe
        System.out.println("\nFahrschein wird ausgegeben");
        for (int i = 0; i < 8; i++) {
            System.out.print("=");
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n");

        // Rückgeldberechnung und -ausgabe
        rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
        if (rueckgabebetrag > 0.0) {
            System.out.println("Der Rückgabebetrag in Höhe von " + String.format("%.2f", rueckgabebetrag) + " Euro");
            System.out.println("rueckgabebetrag = " + rueckgabebetrag);
            System.out.println("wird in folgenden Münzen ausgezahlt:");

            while (rueckgabebetrag >= 2.0) { // 2-Euro-Münzen
                rueckgabebetrag = rueckgabebetrag - 2;
            }
            while (rueckgabebetrag >= 1.0) { // 1-Euro-Münzen
                rueckgabebetrag = rueckgabebetrag - 1;
            }
            while (rueckgabebetrag >= 0.5) { // 50-Cent-Münzen
                rueckgabebetrag = rueckgabebetrag - 0.5;
            }
            while (rueckgabebetrag >= 0.2) { // 20-Cent-Münzen
                rueckgabebetrag = rueckgabebetrag - 0.2;
            }
            while (rueckgabebetrag >= 0.1) { // 10-Cent-Münzen
                rueckgabebetrag = rueckgabebetrag - 0.1;
            }
            while (rueckgabebetrag >= 0.05) { // 5-Cent-Münzen
                rueckgabebetrag = rueckgabebetrag - 0.05;
            }
        }

        System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
                + "Wir wünschen Ihnen eine gute Fahrt.");

        tastatur.close();

    }
}