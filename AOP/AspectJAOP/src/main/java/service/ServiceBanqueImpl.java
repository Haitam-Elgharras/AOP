package service;

import java.util.HashMap;
import java.util.Map;

public class ServiceBanqueImpl implements IServiceBanque {

    private Map<Long, Compte> comptes = new HashMap<>();
    @Override
    public void addCompte(Compte compte) {
        // if we use execution

        // before will be here
        comptes.put(compte.getCode(), compte);
        // after will be here
    }

    @Override
    public void verser(Long code, double montant) {
        Compte compte = comptes.get(code);
        compte.setSolde(compte.getSolde() + montant);
    }

    @Override
    public void retirer(Long code, double montant) {
        // TODO: check if the account has enough money with an aspect
        Compte compte = comptes.get(code);
        compte.setSolde(compte.getSolde() - montant);
    }

    @Override
    public Compte consulter(Long code) {
        return comptes.get(code);
    }
}
