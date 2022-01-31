package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {

	private static final RenderableHolder instance = new RenderableHolder();
	private List<Renderable> renderableObjects;
	private Comparator<Renderable> comparator;

	public static ArrayList<Image> fixedSpaceObjectImages = new ArrayList<>();
	public static ArrayList<Image> spaceShipImages = new ArrayList<>();
	public static ArrayList<Image> buildingImages = new ArrayList<>();
	public static ArrayList<AudioClip> audioClips = new ArrayList<>();
	public static ArrayList<AudioClip> diceValueAudioes = new ArrayList<>();
	public static AudioClip alienSound;
	public static AudioClip blackholeSound;
	public static AudioClip damageSound;
	public static AudioClip getItemSound;
	public static AudioClip instantSound;
	public static AudioClip loseSound;
	public static AudioClip pirateSound;
	public static AudioClip useItemSound;
	public static AudioClip warpSound;
	public static AudioClip constructSound;
	public static AudioClip captureSound;
	public static AudioClip upgradeSound;
	public static AudioClip bonusSound;
	public static ArrayList<Image> diceFaceImages = new ArrayList<>();
	public static Image gameSceneBg;

	public static String[] fixedSpaceObjectLoadingList = { "SpaceStation", "Mercury", "Venus", "PirateShip", "Earth",
			"Moon", "BlackHole", "Mars", "Ceres", "SpaceRuins1", "Jupiter", "Uranus", "SpaceLab", "Neptune", "Pluto",
			"SpaceRuins2", "Make-Make", "Eris", "WarpGate", "Kepler-176", "Gliese-65", "PirateShip", "Tau-Ceiti",
			"HD-40307" };
	public static String[] auidoClipLoadingList = { "2" };

	static {
		loadResource();
	}

	public RenderableHolder() {
		renderableObjects = new ArrayList<Renderable>();
		comparator = (Renderable o1, Renderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static void loadResource() {

		for (int i = 0; i < fixedSpaceObjectLoadingList.length; i++) {
			fixedSpaceObjectImages.add(new Image(ClassLoader
					.getSystemResource("img/planets/" + fixedSpaceObjectLoadingList[i] + ".png").toString()));
		}

		for (int i = 0; i < 4; i++)
			spaceShipImages.add(new Image(ClassLoader
					.getSystemResource("img/spaceShips/ship" + Integer.toString(i + 1) + ".png").toString()));
		for (int i = 1; i <= 3; i++)
			buildingImages.add(new Image(ClassLoader
					.getSystemResource("img/buildings/Building_" + Integer.toString(i) + ".png").toString()));
		for (int i = 2; i <= 12; i++) {
			diceValueAudioes
					.add(new AudioClip(ClassLoader.getSystemResource("audio/diceValues/" + i + ".wav").toString()));
			System.out.println(i);
		}

		for (int i = 0; i < 6; i++)
			diceFaceImages.add(new Image(
					ClassLoader.getSystemResource("img/diceFaces/dice" + Integer.toString(i + 1) + ".png").toString()));

		gameSceneBg = new Image(ClassLoader.getSystemResource("img/gameBg/bg1.jpg").toString());

		alienSound = new AudioClip(ClassLoader.getSystemResource("audio/events/alien.wav").toString());

		blackholeSound = new AudioClip(ClassLoader.getSystemResource("audio/events/blackhole.wav").toString());

		damageSound = new AudioClip(ClassLoader.getSystemResource("audio/events/damage.wav").toString());

		getItemSound = new AudioClip(ClassLoader.getSystemResource("audio/events/getItem.wav").toString());

		instantSound = new AudioClip(ClassLoader.getSystemResource("audio/events/instant.wav").toString());

		loseSound = new AudioClip(ClassLoader.getSystemResource("audio/events/lose.wav").toString());

		pirateSound = new AudioClip(ClassLoader.getSystemResource("audio/events/pirate.wav").toString());

		useItemSound = new AudioClip(ClassLoader.getSystemResource("audio/events/useItem.wav").toString());

		warpSound = new AudioClip(ClassLoader.getSystemResource("audio/events/warp.wav").toString());

		captureSound = new AudioClip(ClassLoader.getSystemResource("audio/events/capture.wav").toString());

		constructSound = new AudioClip(ClassLoader.getSystemResource("audio/events/construct.wav").toString());

		upgradeSound = new AudioClip(ClassLoader.getSystemResource("audio/events/upgrade.wav").toString());

		bonusSound = new AudioClip(ClassLoader.getSystemResource("audio/events/bonus.wav").toString());
	}

	public void add(Renderable renderableOject) {

		renderableObjects.add(renderableOject);
		Collections.sort(renderableObjects, comparator);

	}

	public List<Renderable> getRenderableObjects() {
		return renderableObjects;
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadFileFailedAlert(String fileName) {
		Alert loadFileFailedAlert = new Alert(AlertType.ERROR);
		loadFileFailedAlert.setTitle("loading error");
		loadFileFailedAlert.setHeaderText(null);
		loadFileFailedAlert.setContentText("Error with loading " + fileName);
		loadFileFailedAlert.showAndWait();
	}

	public static ArrayList<Image> getFixedSpaceObjectImages() {
		return fixedSpaceObjectImages;
	}

	public static ArrayList<Image> getBuildingImages() {
		return buildingImages;
	}

	public static ArrayList<AudioClip> getAudioClips() {
		return audioClips;
	}

	public static ArrayList<Image> getDiceFaceImages() {
		return diceFaceImages;
	}

}
