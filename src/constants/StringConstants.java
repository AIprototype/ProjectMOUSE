package constants;

import java.util.ArrayList;

public class StringConstants {
    ArrayList<String> loadingHints;
    ArrayList<String> monsterChatList;


    public StringConstants() {
        loadingHints = new ArrayList<>();
        loadingHints.add("Use the arrow keys to the move the player, up arrow to jump");
        loadingHints.add("Use space-bar to fire energy bolts, be sure not to waste them :)");
        loadingHints.add("Try to destroy the cloning containers scattered through out the map. This slows down their research !");
        loadingHints.add("Be careful when jumping on certain platforms, (Hint: watch out for the blue ones !!)");
        loadingHints.add("Since the incident, the entire floor has been covered with hazardous chemical residues used. Be sure to never go near it !!");
        loadingHints.add("The test subjects that have failed the experiment have been zombified !!, destroy them and dont let them touch you !!");
        loadingHints.add("There are certain collectibles that gives you additional points scattered, Gotta bag'em all !!");
        loadingHints.add("Some enemies take 2 hits to kill, yea they are tough !!");
        loadingHints.add("Be sure to check out options for wall jumping.");
        loadingHints.add("Wall Jump: While jumping to right or left, keep pressing the respective directional keys. \nOnce you reach a wall, press the jump button again to jump in the opposite direction.");
        loadingHints.add("Wall Jump: Continue this until you reach a platform, make sure you keep pressing your\n directional key(Right/Left arrow key) while performing continuous wall jumps");

        monsterChatList = new ArrayList<>();
        monsterChatList.add("Did you pick out your clothes in the dark?");
        monsterChatList.add("Gaaahhh, that bugs the snot out of me!");
        monsterChatList.add("A hero never loses. A hero never dies.");
        monsterChatList.add("IT'S WAY TOO HOT HERE");
        monsterChatList.add("I get this uncontrollable\n urge to eat you");
        monsterChatList.add("I CAN'T FEEL MY TOES..");
        monsterChatList.add("EEK! Did you touch me?");
        monsterChatList.add("Y'all are stupid!");
        monsterChatList.add("Oh, no. I dropped the LIFT KEY!");
        monsterChatList.add("Lalalala… Lalalala… I am a loner…");
        monsterChatList.add("I'm a cool guy, I have a girlfriend!");
        monsterChatList.add("Don't try this at home kids…");
        monsterChatList.add("Glad to be alive through CS4303");
        monsterChatList.add("The red stuff boiling below is candy, trust me.");
        monsterChatList.add("You look different...");
        monsterChatList.add("I wonder, how many bolts you got?");
    }

    public ArrayList<String> getLoadingHints() {
        return loadingHints;
    }

    public ArrayList<String> getMonsterChatList() {
        return monsterChatList;
    }
}
