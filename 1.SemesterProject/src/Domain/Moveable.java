package Domain;

/**
 * Moveable interface
 */
interface Moveable {
    /**
     * Called to move to a new room
     * @param nameCurrentRoom the name of the room to move to
     */
    public abstract void move (String nameCurrentRoom); 
}
