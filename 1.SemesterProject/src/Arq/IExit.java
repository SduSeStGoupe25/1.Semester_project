package Arq;

/**
 *
 * Interface for a exit
 */
public interface IExit {

    /**
     * Called to get name1 which is the name on one of the room the exit
     * connects
     *
     * @return name1 as a String
     */
    String getName1();

    /**
     * Called to get name2 which is the name on one of the room the exit
     * connects
     *
     * @return name 2 as a String
     */
    String getName2();

    /**
     * Called check if the exit are locked
     *
     * @return true if the exit are locked ´, and false if not
     */
    boolean getLocked();

    /**
     * Called to get the lock ID. This is used to check if a item can unlock the
     * exit
     *
     * @return the lockID as a integer
     */
    int getlockID();
}
