package br.org.tiktak.dashboard;

import jmine.tec.test.testcase.AbstractBOStaticAnalysisTest;

/**
 * Static Analisys Test
 */
public class StaticAnalisysTest extends AbstractBOStaticAnalysisTest {

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    protected String getSpringMainXMLFilename() {
        return "core-test-beans.xml";
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    protected String getControllerSpringId() {
        return "simpleController";
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    protected String[] getResourcesBaseReferencia() {
        return new String[]{};
    }
    

    /**
     * @return boolean
     */
    @Override
    protected boolean failOnSchemaWarnings() {
        return false;
    }
    
}
