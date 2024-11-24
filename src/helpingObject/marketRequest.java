package helpingObject;

import sample.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class marketRequest implements Serializable {
    public String clubName;
    public List<Player> playerList = new ArrayList<Player>();

    public marketRequest(String clubName){
        this.clubName = clubName;
    }

    public marketRequest(){

    }
}
