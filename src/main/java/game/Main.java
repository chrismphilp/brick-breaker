package game;

import engine.GameEngine;
import engine.IGameLogic;

public class Main {
    public static void main(String[] args) {
        try {
            IGameLogic gameLogic = new BrickBreaker();
            GameEngine gameEng = new GameEngine("BrickBreaker", 600, 480, true, gameLogic);
            gameEng.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}
