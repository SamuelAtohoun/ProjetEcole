package Observer;

public interface Subject {
	void addObserver(PaymentObserver observer);

	void removeObserver(PaymentObserver observer);

	void notifierObservers(String message);
}