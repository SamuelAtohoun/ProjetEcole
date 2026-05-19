package factory;

public class Virement extends MoyenPaiement {
	@Override
	public String getLibelle() {
		return "Virement bancaire";
	}
}