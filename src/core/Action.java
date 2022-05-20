package core;

public interface Action {

    /**
     * Method to get the turn number this action was peformed
     * @return int : indicating the turn number this action took place
     */
    public int getTurnNumber();

    /**
     * Method to get the move information for this action
     * @return String : indicating the type of move performed
     */
    public String getMoveInfo();

}