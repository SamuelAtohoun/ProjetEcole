package projetEcole;

import Observer.PaymentObserver;

public class Parent extends Utilisateur implements PaymentObserver {
	
	public Parent() {
		
	}

	@Override
	public void notificationParent(String message) {
		// TODO Auto-generated method stub
		
		System.out.println("Notification envoyée ");
		System.out.println(message);
		
	}
	
}