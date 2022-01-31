package main;

import gui.HomePane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.GameManager;
import logic.GameThread;

public class main extends Application {

	private GameManager gameManager;
	private int playerNum;
	private AudioClip sound;

	@Override
	public void start(Stage window) throws InterruptedException {

		HomePane Home = new HomePane();
		setHomeSceneButton(Home, window);
		Scene Homescene = new Scene(Home, 800, 600);
		sound = new AudioClip(ClassLoader.getSystemResource("Background.mp3").toString());
		sound.setVolume(0.14);
		sound.setCycleCount(AudioClip.INDEFINITE);
		sound.play();
		window.setResizable(false);
		window.setTitle("Galactic Colonization");
		window.setScene(Homescene);
		window.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setHomeSceneButton(HomePane Home, Stage window) {
		Home.p1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				playerNum = 1;
				System.out.println("1 Player Selected!!");
				initializeGame(playerNum, window);
			}
		});
		Home.p2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				playerNum = 2;
				System.out.println("2 Player Selected!!");
				initializeGame(playerNum, window);
			}
		});
		Home.p3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				playerNum = 3;
				System.out.println("3 Player Selected!!");
				initializeGame(playerNum, window);
			}
		});
		Home.p4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				playerNum = 4;
				System.out.println("4 Player Selected!!");
				initializeGame(playerNum, window);
			}
		});
		Home.QuitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("Quit!!");
				Platform.exit();
			}
		});
	}

	private void initializeGame(int playerNum, Stage window) {
		this.gameManager = new GameManager(playerNum);
		Scene scene = new Scene(gameManager.getGameScreen());
		GameThread gameThread = new GameThread(gameManager);
		window.setScene(scene);
		gameThread.start();
	}
}
