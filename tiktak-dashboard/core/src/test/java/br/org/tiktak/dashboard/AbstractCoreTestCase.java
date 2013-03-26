package br.org.tiktak.dashboard;

import jmine.tec.test.junit4.DBEnv;
import jmine.tec.test.junit4.DBTestCase;

/**
 * AbstractCore TestCase
 */
@DBEnv(refdb = "core-db.xml", spring = "core-test-beans.xml")
public abstract class AbstractCoreTestCase extends DBTestCase {

}
