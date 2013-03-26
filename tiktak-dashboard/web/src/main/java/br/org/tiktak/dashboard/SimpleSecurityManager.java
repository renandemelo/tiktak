package br.org.tiktak.dashboard;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;

import javax.security.auth.Subject;

import bancosys.tec.security.SecurityException;
import bancosys.tec.security.authorization.UrlPermission;
import bancosys.tec.security.impl.AbstractSecurityManager;
import bancosys.tec.security.impl.SecurityPrincipal;
import bancosys.tec.security.impl.web.WebSecurityContext;
import bancosys.tec.security.impl.web.WebSecurityManager;

/**
 * Simple security filter
 * 
 * @author takeshi
 */
public class SimpleSecurityManager extends AbstractSecurityManager<WebSecurityContext> implements WebSecurityManager {
    public static final String USER_IN_SESSION_KEY = SimpleSecurityManager.class.getName() + ".USER";

    /**
     * {@inheritDoc}
     */
    public Subject loadSubject(WebSecurityContext context) throws SecurityException {
        return (Subject) context.getRequest().getSession().getAttribute(USER_IN_SESSION_KEY);
    }

    /**
     * {@inheritDoc}
     */
    public Subject login(String username, String password, WebSecurityContext context) throws SecurityException {
        HashSet<Object> pubCredentials = new HashSet<Object>();
        UrlPermission permission = new UrlPermission("");
        pubCredentials.add(permission);
        return new Subject(false, Collections.<Principal> singleton(new SecurityPrincipal(username)), pubCredentials, pubCredentials);
    }

    /**
     * {@inheritDoc}
     */
    public void logout(WebSecurityContext context) {
        context.getRequest().getSession().removeAttribute(USER_IN_SESSION_KEY);
        context.getRequest().getSession().invalidate();
    }

    /**
     * {@inheritDoc}
     */
    public void storeSubject(WebSecurityContext context, Subject subject) {
        context.getRequest().getSession().setAttribute(USER_IN_SESSION_KEY, subject);
    }

}
