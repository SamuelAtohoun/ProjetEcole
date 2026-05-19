package factory;

public class CarteBancaire extends MoyenPaiement {
	@Override
	public String getLibelle() {
		return "Carte bancaire";
	}
}