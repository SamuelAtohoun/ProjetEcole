package projetEcole;

import Observer.PaymentObserver;

public class Parent extends Utilisateur implements PaymentObserver {

	public Parent() {
	}

	@Override
	public void onChangement(String message) {
		System.out.println("Notification pour " + getEmail() + " : " + message);
	}

	public String getRole() {
		return "PARENT";
	}
}