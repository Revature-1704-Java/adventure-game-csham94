package com.game;

public class DeathRoom extends Room implements Death{
    public boolean canKillYou() {
        return true;
    }
}