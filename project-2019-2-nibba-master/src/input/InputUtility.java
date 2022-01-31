package input;

public class InputUtility {

	private static boolean isConfirmClick = false;
	private static boolean isCancelClick = false;
	private static int SelectedPlanetNumber = 0;

	public static int getSelectedPlanetNumber() {
		return SelectedPlanetNumber;
	}

	public static void setSelectedPlanetNumber(int selectedPlanetNumber) {
		SelectedPlanetNumber = selectedPlanetNumber;
	}

	public static void confirmClick() {
		InputUtility.isConfirmClick = true;
	}

	public static void cancelClick() {
		InputUtility.isCancelClick = true;
	}

	public static void updateInputState() {
		InputUtility.isConfirmClick = false;
		InputUtility.isCancelClick = false;
		InputUtility.SelectedPlanetNumber = 0;
	}

	public static boolean isConfirmClick() {
		return isConfirmClick;
	}

	public static void setConfirmClick(boolean isConfirmClick) {
		InputUtility.isConfirmClick = isConfirmClick;
	}

	public static boolean isCancelClick() {
		return isCancelClick;
	}

	public static void setCancelClick(boolean isCancelClick) {
		InputUtility.isCancelClick = isCancelClick;
	}

}
