package logic;

import java.util.Arrays;

import gui.Dice;
import gui.DiceButton;
import gui.DicisionBox;
import gui.InformationPane;
import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.fixedSpaceObject.AlienShip;
import logic.fixedSpaceObject.BlackHole;
import logic.fixedSpaceObject.FixedSpaceObject;
import logic.fixedSpaceObject.Planet;
import logic.fixedSpaceObject.SpaceLab;
import logic.fixedSpaceObject.SpacePirateShip;
import logic.fixedSpaceObject.SpaceRuins;
import logic.fixedSpaceObject.SpaceStation;
import logic.fixedSpaceObject.WarpGate;
import logic.player.BotPlayer;
import logic.player.Item;
import logic.player.Player;
import sharedObject.RenderableHolder;

public class GameThread extends AnimationTimer {

	private GameManager gameManager;
	private Player currentPlayer;
	private int currentPlayerNumber;
	private int currentPhase;
	private int travelLength;
	private Dice dice1, dice2;
	private DiceButton diceButton;
	private int moveCount;
	private DicisionBox dicisionBox;
	private InformationPane Information;

	public GameThread(GameManager gameManager) {
		super();
		this.gameManager = gameManager;
		this.currentPlayer = gameManager.getPlayers().get(0);
		this.currentPlayerNumber = 1;
		this.currentPhase = 0;
		this.travelLength = 0;
		this.dice1 = new Dice(1);
		this.dice2 = new Dice(2);
		this.diceButton = new DiceButton("stop dice");
		this.Information = new InformationPane(currentPlayer, "NONE");
	}

	@Override
	public void handle(long arg0) {
		if (!Information.getType().equals("NONE")) {
			try {
				runShowInformation();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (!gameManager.isGameEnd()) {

			switch (currentPhase) {

			case 0:
				this.runStartPhase();
				break;

			case 1:
				this.runDicePhase();
				break;

			case 2:
				this.runWarpPhase();
				break;

			case 3:
				this.runTravelPhase();
				break;

			case 4:
				FixedSpaceObject currentSpaceObject = currentPlayer.getCurrentPoint().getFixedSpaceObject();

				if (currentSpaceObject instanceof Planet) {
					this.runLandingPlanetPhase(currentSpaceObject);
				}

				else if (currentSpaceObject instanceof AlienShip) {
					this.runAttackPhase((AttackAbility) currentSpaceObject);
					this.currentPhase = 5;
				} else if (currentSpaceObject instanceof BlackHole) {
					this.runLandingBlackHolePhase();
				} else if (currentSpaceObject instanceof SpaceLab) {
					RenderableHolder.bonusSound.play();
					Information = new InformationPane(currentPlayer, "Space Lab");
					this.gameManager.getGameScreen().getChildren().add(Information);
					System.out.println("Space Lab");
					SpaceLab.bonusPlanets(currentPlayer);
					this.currentPhase = 5;
				} else if (currentSpaceObject instanceof SpacePirateShip) {
					this.runLandingPirate();
				} else if (currentSpaceObject instanceof SpaceRuins) {
					this.runLandingSpaceRuins();
				} else if (currentSpaceObject instanceof WarpGate) {
					RenderableHolder.warpSound.play();
					Information = new InformationPane(currentPlayer, "Warp Gate");
					this.gameManager.getGameScreen().getChildren().add(Information);
					System.out.println("Warp Gate");
					this.currentPhase = 2;
				} else if (currentSpaceObject instanceof SpaceStation) {
					this.currentPhase = 5;
				}
				break;

			case 41:
				this.runCapturePhase();
				break;

			case 42:
				this.runConstructPhase();
				break;

			case 43:
				this.runUpgradePhase();
				break;

			case 5:
				this.runGetResPerTurnPhase();
				this.switchPlayerPhase();
				break;
			}
		} else {
			this.stop();
		}

	}

	private void runDicePhase() {
		if (currentPlayer instanceof BotPlayer) {
			InputUtility.setConfirmClick(true);
		}
		if (InputUtility.isConfirmClick()) {
			dice1.randomPerFrame();
			dice2.randomPerFrame();
			this.travelLength = dice1.getDicePoint() + dice2.getDicePoint();
			RenderableHolder.diceValueAudioes.get(this.travelLength - 2).play();
			System.out.println(dice1.getDicePoint());
			System.out.println(dice2.getDicePoint());
			System.out.println(travelLength);
			currentPhase = 3;
			gameManager.getGameScreen().getChildren().remove(diceButton);
			InputUtility.updateInputState();
		} else {
			dice1.randomPerFrame();
			dice2.randomPerFrame();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			InputUtility.updateInputState();
		}
	}

	private void runTravelPhase() {
		if (this.currentPlayer.getSpaceShip().getCurrentPoint().getAreaNumber() == 0)
			SpaceStation.bonusPlayer(this.currentPlayer);

		if (moveCount == travelLength) {
			gameManager.getGameScreen().getChildren().removeAll(dice1, dice2);
			this.moveCount = 0;
			this.currentPlayer.updateCurrentPoint();
			this.currentPhase = 4;
		} else {
			this.currentPlayer.getSpaceShip().move();
			System.out.println("move");
			this.currentPlayer.updateCurrentPoint();
			this.moveCount += 1;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void runGetResPerTurnPhase() {
		int sumRes = 0;
		for (int i = 0; i < currentPlayer.getCapturedPlanets().size(); i++) {
			sumRes += currentPlayer.getCapturedPlanets().get(i).getResourcePerTurn();
			currentPlayer.addResource(currentPlayer.getCapturedPlanets().get(i).getResourcePerTurn());
		}
		Information = new InformationPane(currentPlayer, "Get Resource", sumRes);
		this.gameManager.getGameScreen().getChildren().add(Information);
		System.out.println("Get Resource");
		///// removed

	}

	private void switchPlayerPhase() {
		if (currentPlayerNumber == gameManager.getPlayers().size()) {
			currentPlayerNumber = 1;
			gameManager.addRound();
			currentPlayer = gameManager.getPlayers().get(0);
		} else {
			currentPlayerNumber += 1;
			currentPlayer = gameManager.getPlayers().get(currentPlayerNumber - 1);
		}
		gameManager.getGameStatusBar().update(this);
		currentPhase = 0;
	}

	private void runAttackPhase(AttackAbility attacker) {
		if (currentPlayer.getItem().getName().equals("Attack Barrier")) {
			currentPlayer.setItem(new Item("null"));
			RenderableHolder.useItemSound.play();
			Information = new InformationPane(currentPlayer, "Barrier");
			this.gameManager.getGameScreen().getChildren().add(Information);
		} else {
			int reducedHp = attacker.attack(currentPlayer);
			RenderableHolder.damageSound.play();
			Information = new InformationPane(currentPlayer, "Attacked", reducedHp);
			this.gameManager.getGameScreen().getChildren().add(Information);
		}
		if (currentPlayer.isLose()) {
			currentPhase = 0;
		}
	}

	private void runUpgradeDicisionPhase() {
		if (!(currentPlayer instanceof BotPlayer)) {
			dicisionBox = new DicisionBox(currentPlayer, "Upgrade");
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					dicisionBox.showAndWait().ifPresent(response -> {
						if (response == dicisionBox.yes) {
							InputUtility.setConfirmClick(true);
						} else if (response == dicisionBox.no) {
							InputUtility.setCancelClick(true);
						}
					});
				}
			});
		}
		this.currentPhase = 43;
	}

	private void runCaptureDicisionPhase() {
		if (!(currentPlayer instanceof BotPlayer)) {
			dicisionBox = new DicisionBox(currentPlayer, "Capture");
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					dicisionBox.showAndWait().ifPresent(response -> {
						if (response == dicisionBox.yes) {
							InputUtility.setConfirmClick(true);
						} else if (response == dicisionBox.no) {
							InputUtility.setCancelClick(true);
						}
					});
				}
			});
		}
		this.currentPhase = 41;
	}

	private void runConstructDicisionPhase() {
		if (!(currentPlayer instanceof BotPlayer)) {
			dicisionBox = new DicisionBox(currentPlayer, "Construction");
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					dicisionBox.showAndWait().ifPresent(response -> {
						if (response == dicisionBox.yes) {
							InputUtility.setConfirmClick(true);
						} else if (response == dicisionBox.no) {
							InputUtility.setCancelClick(true);
						}
					});
				}
			});
		}
		this.currentPhase = 42;
	}

	private void runWarpPhase() {
		if (currentPlayer instanceof BotPlayer) {
			InputUtility.setSelectedPlanetNumber(((BotPlayer) currentPlayer).decideWarpDestination());
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (InputUtility.getSelectedPlanetNumber() != 0) {
			currentPlayer.setCurrentPoint(
					GameBoard.getInstace().getFixedPoints().get(InputUtility.getSelectedPlanetNumber()));
			currentPlayer.getSpaceShip().warp(currentPlayer.getCurrentPoint());
			this.currentPhase = 4;
			InputUtility.updateInputState();
		}
		InputUtility.updateInputState();
	}

	private void runStartPhase() {
		if (gameManager.checkGameEnd()) {
			Information = new InformationPane(currentPlayer, "Winner", gameManager.getWinnerName());
			this.gameManager.getGameScreen().getChildren().add(Information);
			System.out.println("Game end");
		} else if (currentPlayer.isLose()) {
			this.switchPlayerPhase();
		}

		else if (currentPlayer.isImprison()) {
			this.currentPhase = 5;

			Information = new InformationPane(currentPlayer, "Imprison", this.currentPlayer.getImprisonTurnLeft());
			this.gameManager.getGameScreen().getChildren().add(Information);
			this.currentPlayer.reduceImprisonTurnLeft();
		} else {
			if (currentPlayer.getItem().getName().equals("Warp Scroll")) {

				RenderableHolder.warpSound.play();
				currentPlayer.setItem(new Item("null"));
				Information = new InformationPane(currentPlayer, "Warp Gate");
				this.gameManager.getGameScreen().getChildren().add(Information);
				System.out.println("Warp Gate");
				currentPhase = 2;
			} else {
				gameManager.getGameScreen().getChildren().addAll(dice1, dice2, diceButton);
				currentPhase = 1;
			}
		}
	}

	private void runShowInformation() throws InterruptedException {
		Thread.sleep(1500);
		if (Information.getType().equals("Winner")) {
			Thread.sleep(1000);
			System.exit(0);
		}
		gameManager.getGameScreen().getChildren().remove(Information);
		Information = new InformationPane(currentPlayer, "NONE");
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	private void runLandingPlanetPhase(FixedSpaceObject currentSpaceObject) {
		Planet currentPlanet = (Planet) currentSpaceObject;
		if (currentPlanet.getOwner() == null) {
			if (currentPlayer.getResource() >= currentPlanet.getCapturedCost())
				this.runCaptureDicisionPhase();
			else
				this.currentPhase = 5;

		} else if (currentPlanet.getOwner().getNumber() == currentPlayer.getNumber()) {
			if (currentPlanet.getBuilding() == null) {
				if (currentPlayer.getResource() >= currentPlanet.getConstructionCost())
					this.runConstructDicisionPhase();
				else
					this.currentPhase = 5;

			} else {
				if (currentPlayer.getResource() >= currentPlanet.getBuilding().getUpgradeCost()
						&& currentPlanet.getBuilding().getLevel() < 3)
					this.runUpgradeDicisionPhase();
				else
					this.currentPhase = 5;
			}

		} else {
			currentPlanet.attack(currentPlayer);
			if (currentPlayer.getResource() >= currentPlanet.getCapturedCost())
				this.runCaptureDicisionPhase();
			else
				this.currentPhase = 5;
		}
	}

	private void runLandingBlackHolePhase() {
		if (currentPlayer.getItem().getName().equals("BlackHole Escape")) {
			RenderableHolder.useItemSound.play();
			currentPlayer.setItem(new Item("null"));
			Information = new InformationPane(currentPlayer, "BlackHole Escape");
			this.gameManager.getGameScreen().getChildren().add(Information);
			System.out.println("BlackHole Escape");
			///// removed
		} else {
			RenderableHolder.blackholeSound.play();
			BlackHole.imprisonPlayer(currentPlayer);
			Information = new InformationPane(currentPlayer, "Trapped BlackHole");
			this.gameManager.getGameScreen().getChildren().add(Information);
			System.out.println("Trapped BlackHole");
			///// removed
		}
		this.currentPhase = 5;
	}

	private void runLandingPirate() {
		if (currentPlayer.getItem().getName().equals("Pirate Pass")) {
			RenderableHolder.useItemSound.play();
			currentPlayer.setItem(new Item("null"));
			Information = new InformationPane(currentPlayer, "Pirate Pass");
			this.gameManager.getGameScreen().getChildren().add(Information);
			System.out.println("Use Pirate Pass");
			///// removed

		} else {
			RenderableHolder.pirateSound.play();
			int stealedRes = SpacePirateShip.steal(currentPlayer);
			Information = new InformationPane(currentPlayer, "Pirate Steal", stealedRes);
			this.gameManager.getGameScreen().getChildren().add(Information);
			System.out.println("Pirate Steal");
		}
		this.currentPhase = 5;
	}

	private void runLandingSpaceRuins() {
		Item gettedItem = SpaceRuins.giveItem();
		Information = new InformationPane(currentPlayer, "Item get", gettedItem.getName());
		this.gameManager.getGameScreen().getChildren().add(Information);
		System.out.println("Item get");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		///// removed

		if (Arrays.asList(Item.instantItemNameList).contains(gettedItem.getName())) {
			RenderableHolder.instantSound.play();
			Item.activeInstantEffect(currentPlayer, gettedItem);
		} else {
			RenderableHolder.getItemSound.play();
			currentPlayer.setItem(gettedItem);
		}
		this.currentPhase = 5;
	}

	private void runCapturePhase() {
		if (currentPlayer instanceof BotPlayer) {
			if (((BotPlayer) currentPlayer).dicideCapture())
				InputUtility.setConfirmClick(true);
			else
				InputUtility.setCancelClick(true);
		}
		if (InputUtility.isConfirmClick()) {
			RenderableHolder.captureSound.play();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Planet selecedPlanet = (Planet) currentPlayer.getCurrentPoint().getFixedSpaceObject();
			currentPlayer.capturedPlanet(selecedPlanet);
			currentPlayer.getPlayerStatusBar().update();
			currentPhase = 5;
			InputUtility.updateInputState();
		}
		if (InputUtility.isCancelClick()) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentPhase = 5;
			InputUtility.updateInputState();
		}
		InputUtility.updateInputState();
	}

	private void runConstructPhase() {
		if (currentPlayer instanceof BotPlayer) {
			if (((BotPlayer) currentPlayer).dicideConstruct())
				InputUtility.setConfirmClick(true);
			else
				InputUtility.setCancelClick(true);
		}
		if (InputUtility.isConfirmClick()) {
			RenderableHolder.constructSound.play();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			((Planet) currentPlayer.getCurrentPoint().getFixedSpaceObject()).constructBuilding();
			gameManager.getGameScreen().getChildren().add(
					((Planet) currentPlayer.getCurrentPoint().getFixedSpaceObject()).getBuilding().getBuildingObject());
			this.currentPhase = 5;
		}
		if (InputUtility.isCancelClick()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.currentPhase = 5;
		}
		InputUtility.updateInputState();

	}

	private void runUpgradePhase() {
		if (currentPlayer instanceof BotPlayer) {
			if (((BotPlayer) currentPlayer).dicideUpgrade())
				InputUtility.setConfirmClick(true);
			else
				InputUtility.setCancelClick(true);
		}
		if (InputUtility.isConfirmClick()) {
			RenderableHolder.upgradeSound.play();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			((Planet) currentPlayer.getCurrentPoint().getFixedSpaceObject()).getBuilding().upgrade();
			this.currentPhase = 5;
		}
		if (InputUtility.isCancelClick()) {
			this.currentPhase = 5;
		}
		InputUtility.updateInputState();
	}
}
