package helpingObject;

import sample.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class playerBuy implements Serializable {
    public Player player;
    public String clubName;
    public List<Player> playerList = new ArrayList<Player>();


}
