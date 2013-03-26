package br.org.tiktak.dashboard.listener;

import bancosys.tec.persist.dao.BaseDAO;
import bancosys.tec.persist.dao.BeanNotFoundException;
import bancosys.tec.persist.persister.listener.AbstractPersisterListener;
import bancosys.tec.persist.persister.listener.PersisterListener;
import bancosys.tec.security.impl.domain.MetaCredential;
import bancosys.tec.security.impl.domain.User;

/**
 * {@link PersisterListener} de {@link User} que adiciona a um usuário sendo inserido as credenciais mínimos de acesso ao sistema.
 * 
 * @author jrenaut
 */
public class UserCredentialPersisterListener extends AbstractPersisterListener<User> {

    private BaseDAO<MetaCredential> metaCredentialDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeInsert(User bean) {
        super.beforeInsert(bean);

        try {
            MetaCredential meta = this.metaCredentialDAO.findByNaturalKey("JMINE_WEB_APP_USER");
            bean.getCredentials().addAll(meta.getCredentials());
        } catch (BeanNotFoundException e) {
            throw new IllegalStateException("A meta credential JMINE_WEB_APP_USER deveria existir.");
        }
    }

    /**
     * @param metaCredentialDAO the metaCredentialDAO to set
     */
    public void setMetaCredentialDAO(BaseDAO<MetaCredential> metaCredentialDAO) {
        this.metaCredentialDAO = metaCredentialDAO;
    }
}
