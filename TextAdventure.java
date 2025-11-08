import java.util.*;

public class TextAdventure {
  FancyConsole console;
  Scanner inScanner;
  Player ourHero;

  public TextAdventure() {
    console = new FancyConsole("Great Text Adventure!", 600, 600);
    inScanner = new Scanner(System.in);
    ourHero = new Player("Bob", 200, 0);
  }

  public void play() {
    String input;
    console.setImage("distantcity.jpg");

    System.out.println("What is your name?\n");
    input = inScanner.nextLine();
    ourHero.changeName(input);

    System.out.println("It is finally the day you set out on your adventure to find the lost treasure of Atlantis!");
    System.out.println("You have narrowed your search to two possible locations.");
    System.out.println("Press 1 to explore the underwater meadow or press 2 to explore the ancient sea ruins.");
    System.out.println("Good luck, " + ourHero.getName() + "!");
    int intput = inScanner.nextInt();
    if (intput == 1) {
      enterZone1();
    } else if (intput == 2) {
      enterZone2();
    } else {
      System.out.println("Incorrect input, try again.");
      intput = inScanner.nextInt();
    }
  }

  private void enterZone1() {
    console.setImage("image.png");

    System.out.println("You feel the cold and heavy pressure of the ocean; you are now in the meadow.");
    System.out.println("You start wandering through the meadow and find a cave with no signs of life.");
    System.out.println("You think you see something shining — do you investigate it (press 1) or leave it alone (press 2)?");
    int num = inScanner.nextInt();

    if (num == 1) {
      System.out.println("You enter the cave excited with dreams of treasure, yet you can't shake off a worried feeling in the back of your head.");
      System.out.println("OH NO! An army of tiny goblins rush at you! The only tools available are your scissors (press 1), hand-to-hand combat (2), or running (3).");
      int intput = inScanner.nextInt();

      if (intput == 1) {
        System.out.println("You are kicked out of the cave after receiving an all-out assault — they were tiny goblins, you could have just kicked them!");
        ourHero.setHealth(ourHero.getHealth() - 30);
        System.out.println("You rush to your boat.");
        if (ourHero.getHealth() <= 0) {
          gameEnd(false);
          return;
        } else {
          enterZone2();
        }
      } else if (intput == 2) {
        System.out.println("It was a great success! You defeated 15 tiny goblins and continue into the cave, looting their gold.");
        ourHero.setGold(ourHero.getGold() + 30);
        ourHero.defeatMonster(15);
        System.out.println("You find a map! It includes a description and diagram of the great plains, and directions on how to get there from here!");
        System.out.println("Do you go? (1 to go to the plains, 2 to leave to the ruins)");
        int next = inScanner.nextInt();
        if (next == 1) {
          enterZone3();
        } else if (next == 2) {
          enterZone2();
        } else {
          System.out.println("Incorrect input, try again.");
        }
      } else if (intput == 3) {
        enterZone2();
      } else {
        System.out.println("Incorrect input, try again.");
      }
    } else if (num == 2) {
      System.out.println("You chose to play it safe — that's no way to find treasure.");
      enterZone2();
    } else {
      System.out.println("Incorrect input, try again.");
    }
  }

  private void enterZone2() {
    console.setImage("image (1).png");
    System.out.println("You somehow feel a presence despite this city being destroyed over a millennia ago.");
    System.out.println("You feel confident in your strength and are ready to take on anything coming at you.");
    System.out.println("Do you forage for supplies (1) or go forth into the town square (2)?");
    int intput = inScanner.nextInt();
    boolean sword = false;

    if (intput == 1) {
      System.out.println("Good choice! You find a longsword and a first aid kit.");
      ourHero.setHealth(ourHero.getHealth() + 30);
      System.out.println("You now feel ready to explore the town square from a distance, you think you see someone.");
      sword = true;
    } else if (intput == 2) {
      System.out.println("Bold choice!");
    } else {
      System.out.println("Incorrect input, try again.");
    }

    System.out.println("As you approach, you feel a presence following you. When you turn around, you are met with a surprise attack!");
    System.out.println("(1) If you have the longsword, you may use it. (2) Hand-to-hand combat. (3) Run.");
    intput = inScanner.nextInt();
    if (intput == 1 && !sword) {
      System.out.println("You swing your bare hands uselessly — you didn’t actually pick up the sword!");
      ourHero.setHealth(ourHero.getHealth() - 20);
      enterZone2();
    } else if (intput == 1 && sword) {
      System.out.println("You slash the creature clean in half! You survive and continue deeper into the ruins.");
      ourHero.defeatMonster(1);
      enterZone3();
    } else if (intput == 2) {
      System.out.println("You fight bravely but are injured. You manage to escape toward the mountains.");
      ourHero.setHealth(ourHero.getHealth() - 20);
      enterZone3();
    } else if (intput == 3) {
      System.out.println("You flee back to the ocean meadow.");
      enterZone1();
    } else {
      System.out.println("Incorrect input, try again.");
    }
  }

  private void enterZone3() {
    console.setImage("image (6).png");
    System.out.println("You wander for what seems like hours down long winding corridors...");
    System.out.println("Eventually, you find what you’re looking for — a mountain of treasure!");
    ourHero.setGold(ourHero.getGold() + 100);
    System.out.println("You read an ancient tablet talking about another treasure deep in the countryside. You decide to explore.");
    enterZone4();
  }

  private void enterZone4() {
    System.out.println("Ahead of you stands a sprawling stretch of land, rolling hills as far as the eye can see.");
    System.out.println("You seem confused about where someone could hide treasure in such an open area.");
    System.out.println("Then you find it — a tree so big it dwarfs the surrounding hills.");
    System.out.println("You decide to explore, but a group of giant spiders block your way!");
    System.out.println("(1) Fight (2) Run toward the tree (3) Run away");
    int intput = inScanner.nextInt();

    if (intput == 1) {
      int chance = (int) (Math.random() * 3) + 1;
      if (chance == 1 || chance == 2) {
        ourHero.setHealth(ourHero.getHealth() - 50);
        ourHero.setGold(ourHero.getGold() - 10);
        System.out.println("Unlucky! You took heavy damage fighting the spiders.");
        if (ourHero.getHealth() <= 0) {
          gameEnd(false);
          return;
        }
      } else {
        System.out.println("Great job! You defeated the spiders!");
        ourHero.defeatMonster(7);
        System.out.println("You notice they were guarding a lot of food and gold. You dig in.");
        ourHero.setHealth(ourHero.getHealth() + 30);
        ourHero.setGold(ourHero.getGold() + 70);
      }
    } else if (intput == 2) {
      ourHero.setHealth(ourHero.getHealth() - 30);
      System.out.println("You lost a lot of health but made it to the tree!");
      ourHero.setGold(ourHero.getGold() + 60);
    } else if (intput == 3) {
      System.out.println("You retreat back to the ruined city.");
      enterZone2();
    } else {
      System.out.println("Incorrect input, try again.");
    }
    System.out.println("You continue to the volcano island.");
    enterZone5();
  }

  private void enterZone5() {
    console.setImage("image (4).png");
    System.out.println("Upon arriving on the island, the thick hot air makes it hard to breathe.");
    System.out.println("An army of skeletons rise out of the lava. They seem friendly — do you trust them (1) or attack (2)?");
    int intput = inScanner.nextInt();

    if (intput == 1) {
      System.out.println("They are friendly! They share food, gold, and a path to the next island.");
      ourHero.setHealth(ourHero.getHealth() + 100);
      ourHero.setGold(ourHero.getGold() + 50);
      enterZone6();
    } else if (intput == 2) {
      System.out.println("You brutally slaughtered all of the skeletons. As they sink into the lava, you notice a present they were going to give you!");
      System.out.println("You try to salvage as much as you can you only get half, but discover a path to the next island.");
      ourHero.setHealth(ourHero.getHealth() + 50);
      ourHero.setGold(ourHero.getGold() + 20);
      enterZone6();
    } else {
      System.out.println("Incorrect input, try again.");
    }
  }

  private void enterZone6() {
    console.setImage("image (8).png");
    int chance = (int) (Math.random() * 20) + 1;
    int badChance = chance - 1;
    boolean w = true;
    while(w){
    System.out.println("Pick a number 1 through 20 to choose one hole to dig into.");
    int intput = inScanner.nextInt();
    if (chance == intput) {
      System.out.println("You found it! A huge pile of gold!");
      ourHero.setGold(ourHero.getGold() + 200);
      gameEnd(true);
      w = false;
    } else if (badChance == intput) {
      System.out.println("Oh no! You accidentally dug into the island’s graveyard — a huge wave of zombies approach!");
      int zomChance = (int) (Math.random() * 3) + 1;
      if (zomChance == 3) {
        System.out.println("You narrowly won! You stole all their jewelry.");
        ourHero.setGold(ourHero.getGold() + 40);
        ourHero.defeatMonster(30);
      } else {
        System.out.println("You escaped with your life, but barely.");
        ourHero.setHealth(ourHero.getHealth() - 100);
        if (ourHero.getHealth() <= 0) {
          gameEnd(false);
          return;
        }
      }
    }
    else {
      System.out.println("Nothing here. Better luck next time!");
    }
  }
}

  private void gameEnd(boolean alive) {
    if (alive) {
      console.setImage("download (5).jpg");
      System.out.println("YOU DID IT! You have a wealth of " + ourHero.getGold() +
        ". You survived with " + ourHero.getHealth() + " health and defeated " +
        ourHero.getMonstersDefeated() + " monsters!");
    } else if(!alive) {
      console.setImage("image (9).png");
      System.out.println("YOU DIED! You had a wealth of " + ourHero.getGold() +
        " and defeated " + ourHero.getMonstersDefeated() + " monsters!");
      System.out.println("Try again?");
    }
    inScanner.close();
  }
}