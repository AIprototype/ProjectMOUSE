package constants;

import java.util.ArrayList;

public class StringConstants {
    ArrayList<String> loadingHints;

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
    }

    public ArrayList<String> getLoadingHints() {
        return loadingHints;
    }
}
