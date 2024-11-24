package helpingObject;

import sample.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class playerSell implements Serializable {
    public Player player;
    public List<Player> playerList = new ArrayList<Player>();

    public playerSell(Player player){
        this.player = player;
    }
}
