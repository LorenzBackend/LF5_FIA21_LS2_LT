import java.util.Scanner;
import java.util.stream.DoubleStream;

public class FahrkartenautomatV2 {
    public static void main(String[] args) {

        Scanner tastatur = new Scanner(System.in);

        double zuZahlenderBetrag = 0;
        double eingezahlterGesamtbetrag;
        int i = 0;

        begreussung();
        zuZahlenderBetrag = fahrkartenbestellungErfassen(tastatur);
        eingezahlterGesamtbetrag = fahrkartenBezahlen(tastatur, zuZahlenderBetrag);
        fahrkartenAusgeben();
        rueckgeldAusgeben(zuZahlenderBetrag, eingezahlterGesamtbetrag);
        verabschiedung();

        tastatur.close();

    }

    public static void begreussung() {
        System.out.println("Herzlich Willkommen!\n");
    }

    public static double fahrkartenbestellungErfassen(Scanner tastatur) {
        int i;
        boolean customerWantsToPay = false;
        int choosenTicket;
        double finalZuZahlenderBetrag = 0;
        double zuZahlenderBetrag = 0;
        for (i = 0; !customerWantsToPay; i++) {
// Ticketauswahl Print
            String ticketList = "Kurzstrecke AB [2,00 EUR] (1)\n" +
                    "Einzelfahrschein AB [3,00 EUR] (2)\n" +
                    "Tageskarte AB [8,80 EUR] (3)\n" +
                    "4-Fahrten-Karte AB [9,40 EUR] (4)";

            System.out.println(ticketList);
            if (i > 0) {
                System.out.print("Bezahlen (9)\n");
            }

            System.out.print("\nIhre Wahl: ");
            choosenTicket = tastatur.nextInt();

            //verify that choosenticket is 1,2,3 or 4
            while (choosenTicket > 4 && choosenTicket != 9) {
                System.out.println(">> falsche Eingabe <<");
                System.out.print("Ihre Wahl: ");
                choosenTicket = tastatur.nextInt();
            }

            switch (choosenTicket) {
                case 1:
                    zuZahlenderBetrag += 2.0;
                    break;
                case 2:
                    zuZahlenderBetrag += 3.0;
                    break;
                case 3:
                    zuZahlenderBetrag += 8.80;
                case 4:
                    zuZahlenderBetrag += 9.40;
                    break;
                case 9:
                    customerWantsToPay = true;
                    break;
            }

            if(customerWantsToPay) {
                break;
            }

            // Anzahl der Tickets eingeben
            System.out.print("Anzahl der Tickets: ");
            int anzahlTickets = tastatur.nextInt();

            //check if number is 1-10, if not set to 1
            while (anzahlTickets < 1 || anzahlTickets > 10) {
                System.out.println(">> Wählen Sie bitte eine Anzahl von 1 bis 10 Tickets aus. <<");
                System.out.print("Anzahl der Tickets: ");
                anzahlTickets = tastatur.nextInt();
            }

            // Gesamtbetrag berechnen
            zuZahlenderBetrag = zuZahlenderBetrag * anzahlTickets;
            finalZuZahlenderBetrag = finalZuZahlenderBetrag + zuZahlenderBetrag;
            zuZahlenderBetrag = 0;
        }


        // Geldeinwurf
        return finalZuZahlenderBetrag;
    }

    public static double fahrkartenBezahlen(Scanner tastatur, double zuZahlenderBetrag) {

        double nochZuZahlen;
        double eingeworfeneMuenze;
        double eingezahlterGesamtbetrag = 0.0;
        nochZuZahlen = 0.0;
        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
            nochZuZahlen = zuZahlenderBetrag - eingezahlterGesamtbetrag;
            System.out.println("Noch zu zahlen: " + String.format("%.2f", nochZuZahlen));
            System.out.print("Eingabe (mind. 5 Cent, höchstens 20 Euro): ");
            eingeworfeneMuenze = tastatur.nextDouble();

            //validate input to be 5c, 10c, 20c, 50c, 1€, 2€, 5€, 10€, 20€
            double[] validCoins = {0.05, 0.10, 0.20, 0.50, 1, 2, 5, 10, 20};
            double finalEingeworfeneMuenze = eingeworfeneMuenze;
            if (DoubleStream.of(validCoins).anyMatch(x -> x == finalEingeworfeneMuenze)) {
                eingezahlterGesamtbetrag = eingezahlterGesamtbetrag + eingeworfeneMuenze;
            } else {
                System.out.println(">> Kein gültiges Zahlungsmittel");
            }
        }
        return eingezahlterGesamtbetrag;
    }

    public static void fahrkartenAusgeben() {
        int i;
        System.out.println("\nFahrschein wird ausgegeben");
        for (i = 0; i < 8; i++) {
            System.out.print("=");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n\n");
    }

    public static void verabschiedung() {
        System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
                + "Wir wünschen Ihnen eine gute Fahrt.");
    }

    public static void rueckgeldAusgeben(double zuZahlenderBetrag, double eingezahlterGesamtbetrag) {
        double rueckgabebetrag;
        rueckgabebetrag = eingezahlterGesamtbetrag - zuZahlenderBetrag;
        if (rueckgabebetrag > 0.0) {
            System.out.println("Der Rückgabebetrag in Höhe von " + String.format("%.2f", rueckgabebetrag) + " Euro");
            System.out.println("wird in folgenden Münzen ausgezahlt:");
            muenzRueckgabe(rueckgabebetrag);
        }
    }

    public static void muenzRueckgabe(double rueckgabebetrag) {
        while (rueckgabebetrag >= 2.0) { // 2-Euro-Münzen
            System.out.println("2 Euro");
            rueckgabebetrag = rueckgabebetrag - 2;
        }
        while (rueckgabebetrag >= 1.0) { // 1-Euro-Münzen
            System.out.println("1 Euro");
            rueckgabebetrag = rueckgabebetrag - 1;
        }
        while (rueckgabebetrag >= 0.5) { // 50-Cent-Münzen
            System.out.println("50 Cent");
            rueckgabebetrag = rueckgabebetrag - 0.5;
        }
        while (rueckgabebetrag >= 0.2) { // 20-Cent-Münzen
            System.out.println("20 Cent");
            rueckgabebetrag = rueckgabebetrag - 0.2;
        }
        while (rueckgabebetrag >= 0.1) { // 10-Cent-Münzen
            System.out.println("10 Cent");
            rueckgabebetrag = rueckgabebetrag - 0.1;
        }
        while (rueckgabebetrag >= 0.05) { // 5-Cent-Münzen
            System.out.println("5 Cent");
            rueckgabebetrag = rueckgabebetrag - 0.05;
        }
    }
}
