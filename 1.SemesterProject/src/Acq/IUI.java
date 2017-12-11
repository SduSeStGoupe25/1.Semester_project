package Acq;

/**
 *
 * Interface UI
 */
public interface IUI {

    /**
     * Called to start the game
     *
     * @param args from the main method
     */
    void startApplication(String[] args);

    /**
     * Called to inject a instance of domainData to the UI
     *
     * @param domainData to be injected
     */
    void injectDomainData(IDomainData domainData);

    /**
     * Called to inject a instance of domainGame to the UI
     *
     * @param domainGame to be injected
     */
    void injectDomainGame(IGame domainGame);
}
