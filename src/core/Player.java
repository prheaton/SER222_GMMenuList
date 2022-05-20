package core;

import java.util.Random;

public class Player {

    private String playerName;
    private int health;
    private Action[] actions;
    private int index;

    public Player(String pName, int estimatedMoves) {
        this.playerName = pName;
        this.health = 20;
        this.index = 0;
        actions = new Action[estimatedMoves];
    }

    public String getPlayerName() { return this.playerName; }



    public Action[] getPlayerActions() {
        return this.actions;
    }

    public Action getPlayerAction(int pMove) {
        return this.actions[pMove];
    }

    public void performAttack(int pTurn){
        this.actions[this.index] = new AttackAction(pTurn);
        index++;
    }

    public void useItem(int pTurn, String pItem) {
        this.actions[this.index] = new UseItemAction(pTurn, pItem);
        index++;
    }

    public int getRoll(int pIndex) {
        if(this.actions[pIndex] == null || this.actions[pIndex].getMoveInfo().compareToIgnoreCase("Attack") != 0)  {
            return -1;
        }
        return ((AttackAction) actions[pIndex]).getAttackRoll();
        // AttackAction temp = (AttackAction) actions[pIndex];
        // return temp.getAttackRoll();
    }


    @Override
    public String toString() {

        String returner = "Player: " + this.getPlayerName() + " health " + this.health + '\n';
        for(int i = 0; i < this.index; i++) {
            returner += this.actions[i].toString();
        }

        return returner;
    }


    private class AttackAction implements Action {

        private int attackTurnNumber;
        private int attackRoll;

        public AttackAction(int pTurnNum) {
            this.attackTurnNumber = pTurnNum;
            // make dice roll
            Random rnd = new Random();
            this.attackRoll = rnd.nextInt(20) + 1;
            System.out.printf("Roll is %d\n", this.attackRoll);
        }

        public int getAttackRoll(){
            return this.attackRoll;
        }


        @Override
        public int getTurnNumber() {
            return attackTurnNumber;
        }

        @Override
        public String getMoveInfo() {
            return "Attack";
        }

        @Override
        public String toString(){
            return ("turn: " + attackTurnNumber + " " + this.getMoveInfo() + " roll: " + this.attackRoll + '\n');
        }


    }

    private class UseItemAction implements Action {

        private int itemTurnNumber;
        private String itemUsed;

        public UseItemAction(int pTurnNum, String pItem) {
            this.itemTurnNumber = pTurnNum;
            this.itemUsed = pItem;
            System.out.println("Item used: " + this.itemUsed);
        }

        @Override
        public int getTurnNumber() {
            return itemTurnNumber;
        }

        @Override
        public String getMoveInfo() {
            return itemUsed;
        }

        @Override
        public String toString(){
            return ("turn: " + itemTurnNumber + " " + this.getMoveInfo() + '\n');
        }
    }



}
