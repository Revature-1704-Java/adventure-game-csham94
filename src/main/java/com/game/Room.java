package com.game;

import java.util.HashMap;

public class Room implements Death {
    private String description;
    private HashMap<String, String> interactives;

    public Room(String description) {
        this.description = description;
        interactives = new HashMap<String, String>();
    }

    public Room() {}

    public void setInteractives(String id, String description) {
        interactives.put(id, description);
    }

    public String getInteractives(String id) {
        return interactives.get(id);
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public boolean checkKey(String checkedKey){
        if (!interactives.containsKey(checkedKey)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean canKillYou() {
        return false;
    }
}