/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entity.Firm;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author LARISSA
 */
@Stateless
public class FirmFacade extends AbstractFacade<Firm> {

    @PersistenceContext(unitName = "WebBuhgalterPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FirmFacade() {
        super(Firm.class);
    }
    
}
