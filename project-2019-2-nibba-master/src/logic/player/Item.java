package logic.player;

import java.util.Random;

public class Item {

	private String name;
	private static String[] itemNameList = { "Warp Scroll", "Attack Barrier", "BlackHole Escape", "Pirate Pass",
			"Level Up", "Heal Full HP", "Resouce Bonus", };
	public static String[] instantItemNameList = { "Level Up", "Heal Full HP", "Resouce Bonus" };

	public Item(String name) {
		this.name = name;
	}

	public static String randomItemPerFrame() {
		Random random = new Random();
		return itemNameList[random.nextInt(itemNameList.length)];
	}

	public String getName() {
		return this.name;
	}

	public static void activeInstantEffect(Player player, Item item) {
		switch (item.getName()) {
		case "Level Up":
			player.levelUp();
			break;
		case "Heal Full Hp":
			player.addHp(player.getMaxExp());
			break;
		case "Resouce Bonus":
			player.addResource(player.getResource() / 2);
			break;
		}
	}
}
