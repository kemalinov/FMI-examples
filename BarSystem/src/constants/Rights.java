package constants;

public enum Rights {

	TAKE_AN_ORDER(0), SERVICE_A_TABLE(1), GIVE_A_BILL(2), SHAKE_A_COCKTAIL(3), SERVICE_A_BAR(4), VIEW_REPORTS(5);

	private int value;

	private Rights(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}

// public static Integer = 2;
// public static Integer GIVE_A_BILL = 3; // has to do with a waiter
// public static Integer SHAKE_A_COCKTAIL = 4;
// public static Integer SERVICE_A_BAR = 5; // has to do with a barman
// public static Integer VIEW_REPORTS = 6; // managers
