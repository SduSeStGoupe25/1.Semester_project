/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arq;

/**
 *
 * @author madsd
 */
public interface IUI {
    void startApplication(String[] args);
    void injectDomainData(IDomainData domainData);
    void injectDomainGame(IDomainGame domainGame);
}
