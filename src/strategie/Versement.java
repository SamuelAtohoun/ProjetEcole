package strategie;

import java.util.Date;

public class Versement {
    private Date datePaiement;
    private double montant;

    public Versement(Date datePaiement, double montant) {
        this.datePaiement = datePaiement;
        this.montant = montant;
    }

    public Date getDatePaiement() { return datePaiement; }
    public double getMontant() { return montant; }
}