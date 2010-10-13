/*
 * =============================================================================
 * 
 *   Copyright (c) 2007-2008, The JASYPT team (http://www.jasypt.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.jasypt.digest.config;

import java.security.Provider;

import org.jasypt.commons.CommonUtils;
import org.jasypt.salt.SaltGenerator;

/**
 * <p>
 * Implementation for {@link DigesterConfig} which can retrieve configuration
 * values from environment variables or system properties.
 * </p>
 * <p>
 * The name of the environment variable or system property (JVM property) to
 * query for each parameter can be set with its corresponding 
 * <tt>setXEnvName</tt> or <tt>setXSysProperty</tt> method.
 * </p>
 * <p>
 * As this class extends {@link SimpleDigesterConfig}, parameter values
 * can be also set with the usual <tt>setX</tt> methods.
 * </p>
 * <p>
 * For any of the configuration parameters, if its value is not configured
 * in any way, a <tt>null</tt> value will be returned by the
 * corresponding <tt>getX</tt> method. 
 * </p>
 * 
 * @since 1.1
 * 
 * @author Daniel Fern&aacute;ndez
 * 
 */
public class EnvironmentDigesterConfig extends SimpleDigesterConfig {
    
    private String algorithmEnvName = null;
    private String iterationsEnvName = null;
    private String saltSizeBytesEnvName = null;
    private String saltGeneratorClassNameEnvName = null;
    private String providerNameEnvName = null;
    private String providerClassNameEnvName = null;
    private String invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
    private String invertPositionOfPlainSaltInEncryptionResultsEnvName = null;

    private String algorithmSysPropertyName = null;
    private String iterationsSysPropertyName = null;
    private String saltSizeBytesSysPropertyName = null;
    private String saltGeneratorClassNameSysPropertyName = null;
    private String providerNameSysPropertyName = null;
    private String providerClassNameSysPropertyName = null;
    private String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
    private String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
    

    /**
     * <p>
     * Creates a new <tt>EnvironmentDigesterConfig</tt> instance.
     * </p>
     */
    public EnvironmentDigesterConfig() {
        super();
    }



    /**
     * Retrieve the name of the environment variable which value has been
     * loaded as the algorithm.
     *   
     * @return the name of the variable
     */
    public String getAlgorithmEnvName() {
        return this.algorithmEnvName;
    }


    /**
     * Set the config object to use the specified environment variable to
     * load the value for the algorithm.
     * 
     * @param algorithmEnvName the name of the environment variable
     */
    public void setAlgorithmEnvName(String algorithmEnvName) {
        this.algorithmEnvName = algorithmEnvName;
        if (algorithmEnvName == null) {
            super.setAlgorithm(null);
        } else {
            this.algorithmSysPropertyName = null;
            super.setAlgorithm(System.getenv(algorithmEnvName));
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the algorithm.
     *   
     * @return the name of the property
     */
    public String getAlgorithmSysPropertyName() {
        return this.algorithmSysPropertyName;
    }


    /**
     * Set the config object to use the specified JVM system property to
     * load the value for the algorithm.
     * 
     * @param algorithmSysPropertyName the name of the property
     */
    public void setAlgorithmSysPropertyName(String algorithmSysPropertyName) {
        this.algorithmSysPropertyName = algorithmSysPropertyName;
        if (algorithmSysPropertyName == null) {
            super.setAlgorithm(null);
        } else {
            this.algorithmEnvName = null;
            super.setAlgorithm(System.getProperty(algorithmSysPropertyName));
        }
    }


    /**
     * Retrieve the name of the environment variable which value has been
     * loaded as the iteration count.
     *   
     * @return the name of the variable
     */
    public String getIterationsEnvName() {
        return this.iterationsEnvName;
    }


    /**
     * Set the config object to use the specified environment variable to
     * load the value for the iteration count.
     * 
     * @param iterationsEnvName the name of the environment variable
     */
    public void setIterationsEnvName(String iterationsEnvName) {
        this.iterationsEnvName = iterationsEnvName;
        if (iterationsEnvName == null) {
            super.setIterations((String)null);
        } else {
            this.iterationsSysPropertyName = null;
            super.setIterations(System.getenv(iterationsEnvName));
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the iteration count.
     *   
     * @return the name of the property
     */
    public String getIterationsSysPropertyName() {
        return this.iterationsSysPropertyName;
    }


    /**
     * Set the config object to use the specified JVM system property to
     * load the value for the iteration count.
     * 
     * @param iterationsSysPropertyName the name of the property
     */
    public void setIterationsSysPropertyName(String iterationsSysPropertyName) {
        this.iterationsSysPropertyName = iterationsSysPropertyName;
        if (iterationsSysPropertyName == null) {
            super.setIterations((String)null);
        } else {
            this.iterationsEnvName = null;
            super.setIterations(System.getProperty(iterationsSysPropertyName));
        }
    }


    /**
     * Retrieve the name of the environment variable which value has been
     * loaded as the salt size in bytes.
     *   
     * @return the name of the variable
     */
    public String getSaltSizeBytesEnvName() {
        return this.saltSizeBytesEnvName;
    }


    /**
     * Set the config object to use the specified environment variable to
     * load the value for the salt size in bytes.
     * 
     * @param saltSizeBytesEnvName the name of the environment variable
     */
    public void setSaltSizeBytesEnvName(String saltSizeBytesEnvName) {
        this.saltSizeBytesEnvName = saltSizeBytesEnvName;
        if (saltSizeBytesEnvName == null) {
            super.setSaltSizeBytes((String)null);
        } else {
            this.saltSizeBytesSysPropertyName = null;
            super.setSaltSizeBytes(System.getenv(saltSizeBytesEnvName));
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the salt size in bytes.
     *   
     * @return the name of the property
     */
    public String getSaltSizeBytesSysPropertyName() {
        return this.saltSizeBytesSysPropertyName;
    }


    /**
     * Set the config object to use the specified JVM system property to
     * load the value for the salt size in bytes.
     * 
     * @param saltSizeBytesSysPropertyName the name of the property
     */
    public void setSaltSizeBytesSysPropertyName(String saltSizeBytesSysPropertyName) {
        this.saltSizeBytesSysPropertyName = saltSizeBytesSysPropertyName;
        if (saltSizeBytesSysPropertyName == null) {
            super.setSaltSizeBytes((Integer)null);
        } else {
            this.saltSizeBytesEnvName = null;
            super.setSaltSizeBytes(
                    System.getProperty(saltSizeBytesSysPropertyName));
        }
    }


    /**
     * Retrieve the name of the environment variable which value has been
     * loaded as the salt generator class name.
     * 
     * @since 1.3
     *   
     * @return the name of the variable
     */
    public String getSaltGeneratorClassNameEnvName() {
        return this.saltGeneratorClassNameEnvName;
    }


    /**
     * <p>
     * Set the config object to use the specified environment variable to
     * load the value for the salt generator class name.
     * </p>
     * <p>
     * The salt generator class name which is set here must have a no-argument
     * constructor, so that it can be instantiated and passed to the digester.
     * </p>
     * 
     * @since 1.3
     * 
     * @param saltGeneratorClassNameEnvName the name of the environment variable
     */
    public void setSaltGeneratorClassNameEnvName(String saltGeneratorClassNameEnvName) {
        this.saltGeneratorClassNameEnvName = saltGeneratorClassNameEnvName;
        if (saltGeneratorClassNameEnvName == null) {
            super.setSaltGenerator(null);
        } else {
            this.saltGeneratorClassNameSysPropertyName = null;
            String saltGeneratorClassName = System.getenv(saltGeneratorClassNameEnvName);
            super.setSaltGeneratorClassName(saltGeneratorClassName);
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the salt generator class name.
     * 
     * @since 1.3
     *   
     * @return the name of the property
     */
    public String getSaltGeneratorClassNameSysPropertyName() {
        return this.saltGeneratorClassNameSysPropertyName;
    }


    /**
     * <p>
     * Set the config object to use the specified JVM system property to
     * load the value for the salt generator class name.
     * </p>
     * <p>
     * The salt generator class name which is set here must have a no-argument
     * constructor, so that it can be instantiated and passed to the digester.
     * </p>
     * 
     * @since 1.3
     * 
     * @param saltGeneratorClassNameSysPropertyName the name of the property
     */
    public void setSaltGeneratorClassNameSysPropertyName(String saltGeneratorClassNameSysPropertyName) {
        this.saltGeneratorClassNameSysPropertyName = saltGeneratorClassNameSysPropertyName;
        if (saltGeneratorClassNameSysPropertyName == null) {
            super.setSaltGenerator(null);
        } else {
            this.saltGeneratorClassNameEnvName = null;
            String saltGeneratorClassName = 
                System.getProperty(saltGeneratorClassNameSysPropertyName);
            super.setSaltGeneratorClassName(saltGeneratorClassName);
        }
    }


    /**
     * Retrieve the name of the environment variable which value has been
     * loaded as the provider name.
     * 
     * @since 1.3
     *   
     * @return the name of the variable
     */
    public String getProviderNameEnvName() {
        return this.providerNameEnvName;
    }


    /**
     * <p>
     * Set the config object to use the specified environment variable to
     * load the value for the provider name.
     * </p>
     * 
     * @since 1.3
     * 
     * @param providerNameEnvName the name of the environment variable
     */
    public void setProviderNameEnvName(String providerNameEnvName) {
        this.providerNameEnvName = providerNameEnvName;
        if (providerNameEnvName == null) {
            super.setProviderName(null);
        } else {
            this.providerNameSysPropertyName = null;
            super.setProviderName(System.getenv(providerNameEnvName));
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the provider name.
     * 
     * @since 1.3
     *   
     * @return the name of the property
     */
    public String getProviderNameSysPropertyName() {
        return this.providerNameSysPropertyName;
    }


    /**
     * Set the config object to use the specified JVM system property to
     * load the value for the provider name.
     * 
     * @since 1.3
     * 
     * @param providerNameSysPropertyName the name of the property
     */
    public void setProviderNameSysPropertyName(String providerNameSysPropertyName) {
        this.providerNameSysPropertyName = providerNameSysPropertyName;
        if (providerNameSysPropertyName == null) {
            super.setProviderName(null);
        } else {
            this.providerNameEnvName = null;
            super.setProviderName(System.getProperty(providerNameSysPropertyName));
        }
    }


    /**
     * Retrieve the name of the environment variable which value has been
     * loaded as the provider class name.
     * 
     * @since 1.3
     *   
     * @return the name of the variable
     */
    public String getProviderClassNameEnvName() {
        return this.providerClassNameEnvName;
    }


    /**
     * <p>
     * Set the config object to use the specified environment variable to
     * load the value for the provider class name.
     * </p>
     * <p>
     * The provider class name which is set here must have a no-argument
     * constructor, so that it can be instantiated and passed to the digester.
     * </p>
     * 
     * @since 1.3
     * 
     * @param providerClassNameEnvName the name of the environment variable
     */
    public void setProviderClassNameEnvName(String providerClassNameEnvName) {
        this.providerClassNameEnvName = providerClassNameEnvName;
        if (providerClassNameEnvName == null) {
            super.setProvider(null);
        } else {
            this.providerClassNameSysPropertyName = null;
            String providerClassName = System.getenv(providerClassNameEnvName);
            super.setProviderClassName(providerClassName);
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the provider class name.
     * 
     * @since 1.3
     *   
     * @return the name of the property
     */
    public String getProviderClassNameSysPropertyName() {
        return this.providerClassNameSysPropertyName;
    }


    /**
     * <p>
     * Set the config object to use the specified JVM system property to
     * load the value for the provider class name.
     * </p>
     * <p>
     * The provider class name which is set here must have a no-argument
     * constructor, so that it can be instantiated and passed to the digester.
     * </p>
     * 
     * @since 1.3
     * 
     * @param providerClassNameSysPropertyName the name of the property
     */
    public void setProviderClassNameSysPropertyName(String providerClassNameSysPropertyName) {
        this.providerClassNameSysPropertyName = providerClassNameSysPropertyName;
        if (providerClassNameSysPropertyName == null) {
            super.setProvider(null);
        } else {
            this.providerClassNameEnvName = null;
            String providerClassName = System.getProperty(providerClassNameSysPropertyName);
            super.setProviderClassName(providerClassName);
        }
    }

    
    


    /**
     * Retrieve the name of the environment variable which value has been
     * loaded as the value for the invertPositionOfSaltInMessageBeforeDigesting
     * property.
     * 
     * @since 1.7
     *   
     * @return the name of the variable
     */
    public String getInvertPositionOfSaltInMessageBeforeDigestingEnvName() {
        return this.invertPositionOfSaltInMessageBeforeDigestingEnvName;
    }



    /**
     * <p>
     * Set the config object to use the specified environment variable to
     * load the value for the invertPositionOfSaltInMessageBeforeDigesting
     * property.
     * </p>
     * 
     * @since 1.7
     * 
     * @param invertPositionOfSaltInMessageBeforeDigestingEnvName the name of the environment variable
     */
    public void setInvertPositionOfSaltInMessageBeforeDigestingEnvName(String invertPositionOfSaltInMessageBeforeDigestingEnvName) {
        this.invertPositionOfSaltInMessageBeforeDigestingEnvName = invertPositionOfSaltInMessageBeforeDigestingEnvName;
        if (invertPositionOfSaltInMessageBeforeDigestingEnvName == null) {
            super.setInvertPositionOfSaltInMessageBeforeDigesting(null);
        } else {
            this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
            super.setInvertPositionOfSaltInMessageBeforeDigesting(
                    CommonUtils.getStandardBooleanValue(
                            System.getenv(invertPositionOfSaltInMessageBeforeDigestingEnvName)));
        }
    }
    
    

    



    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the value for the invertPositionOfSaltInMessageBeforeDigesting
     * property.
     * 
     * @since 1.7
     *   
     * @return the name of the property
     */
    public String getInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName() {
        return this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName;
    }



    /**
     * <p>
     * Set the config object to use the specified JVM system property to
     * load the value for the invertPositionOfSaltInMessageBeforeDigesting
     * property.
     * </p>
     * 
     * @since 1.7
     * 
     * @param invertPositionOfSaltInMessageBeforeDigestingSysPropertyName the name of the property
     */
    public void setInvertPositionOfSaltInMessageBeforeDigestingSysPropertyName(String invertPositionOfSaltInMessageBeforeDigestingSysPropertyName) {
        this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = invertPositionOfSaltInMessageBeforeDigestingSysPropertyName;
        if (invertPositionOfSaltInMessageBeforeDigestingSysPropertyName == null) {
            super.setInvertPositionOfSaltInMessageBeforeDigesting(null);
        } else {
            this.invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
            super.setInvertPositionOfSaltInMessageBeforeDigesting(
                    CommonUtils.getStandardBooleanValue(
                            System.getProperty(invertPositionOfSaltInMessageBeforeDigestingSysPropertyName)));
        }
    }
    
    

    
    


    /**
     * Retrieve the name of the environment variable which value has been
     * loaded as the value for the invertPositionOfPlainSaltInEncryptionResults
     * property.
     * 
     * @since 1.7
     *   
     * @return the name of the variable
     */
    public String getInvertPositionOfPlainSaltInEncryptionResultsEnvName() {
        return this.invertPositionOfPlainSaltInEncryptionResultsEnvName;
    }



    /**
     * <p>
     * Set the config object to use the specified environment variable to
     * load the value for the invertPositionOfPlainSaltInEncryptionResults
     * property.
     * </p>
     * 
     * @since 1.7
     * 
     * @param invertPositionOfPlainSaltInEncryptionResultsEnvName the name of the environment variable
     */
    public void setInvertPositionOfPlainSaltInEncryptionResultsEnvName(String invertPositionOfPlainSaltInEncryptionResultsEnvName) {
        this.invertPositionOfPlainSaltInEncryptionResultsEnvName = invertPositionOfPlainSaltInEncryptionResultsEnvName;
        if (invertPositionOfPlainSaltInEncryptionResultsEnvName == null) {
            super.setInvertPositionOfPlainSaltInEncryptionResults(null);
        } else {
            this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
            super.setInvertPositionOfPlainSaltInEncryptionResults(
                    CommonUtils.getStandardBooleanValue(
                            System.getenv(invertPositionOfPlainSaltInEncryptionResultsEnvName)));
        }
    }



    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the value for the invertPositionOfPlainSaltInEncryptionResults
     * property.
     * 
     * @since 1.7
     *   
     * @return the name of the property
     */
    public String getInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName() {
        return this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName;
    }



    /**
     * <p>
     * Set the config object to use the specified JVM system property to
     * load the value for the invertPositionOfPlainSaltInEncryptionResults
     * property.
     * </p>
     * 
     * @since 1.7
     * 
     * @param invertPositionOfPlainSaltInEncryptionResultsSysPropertyName the name of the property
     */
    public void setInvertPositionOfPlainSaltInEncryptionResultsSysPropertyName(String invertPositionOfPlainSaltInEncryptionResultsSysPropertyName) {
        this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = invertPositionOfPlainSaltInEncryptionResultsSysPropertyName;
        if (invertPositionOfPlainSaltInEncryptionResultsSysPropertyName == null) {
            super.setInvertPositionOfPlainSaltInEncryptionResults(null);
        } else {
            this.invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
            super.setInvertPositionOfPlainSaltInEncryptionResults(
                    CommonUtils.getStandardBooleanValue(
                            System.getProperty(invertPositionOfPlainSaltInEncryptionResultsSysPropertyName)));
        }
    }

    
    
    
    public void setAlgorithm(String algorithm) {
        this.algorithmEnvName = null;
        this.algorithmSysPropertyName = null;
        super.setAlgorithm(algorithm);
    }



    public void setIterations(Integer iterations) {
        this.iterationsEnvName = null;
        this.iterationsSysPropertyName = null;
        super.setIterations(iterations);
    }


    public void setIterations(String iterations) {
        this.iterationsEnvName = null;
        this.iterationsSysPropertyName = null;
        super.setIterations(iterations);
    }



    public void setSaltSizeBytes(Integer saltSizeBytes) {
        this.saltSizeBytesEnvName = null;
        this.saltSizeBytesSysPropertyName = null;
        super.setSaltSizeBytes(saltSizeBytes);
    }


    public void setSaltSizeBytes(String saltSizeBytes) {
        this.saltSizeBytesEnvName = null;
        this.saltSizeBytesSysPropertyName = null;
        super.setSaltSizeBytes(saltSizeBytes);
    }

    
    
    public void setSaltGenerator(SaltGenerator saltGenerator) {
        this.saltGeneratorClassNameEnvName = null;
        this.saltGeneratorClassNameSysPropertyName = null;
        super.setSaltGenerator(saltGenerator);
    }


    public void setSaltGeneratorClassName(String saltGeneratorClassName) {
        this.saltGeneratorClassNameEnvName = null;
        this.saltGeneratorClassNameSysPropertyName = null;
        super.setSaltGeneratorClassName(saltGeneratorClassName);
    }


    public void setProviderName(String providerName) {
        this.providerNameEnvName = null;
        this.providerNameSysPropertyName = null;
        super.setProviderName(providerName);
    }

    
    public void setProvider(Provider provider) {
        this.providerClassNameEnvName = null;
        this.providerClassNameSysPropertyName = null;
        super.setProvider(provider);
    }


    public void setProviderClassName(String providerClassName) {
        this.providerClassNameEnvName = null;
        this.providerClassNameSysPropertyName = null;
        super.setProviderClassName(providerClassName);
    }



    public void setInvertPositionOfPlainSaltInEncryptionResults(Boolean invertPositionOfPlainSaltInEncryptionResults) {
        this.invertPositionOfPlainSaltInEncryptionResultsEnvName = null;
        this.invertPositionOfPlainSaltInEncryptionResultsSysPropertyName = null;
        super.setInvertPositionOfPlainSaltInEncryptionResults(invertPositionOfPlainSaltInEncryptionResults);
    }



    public void setInvertPositionOfSaltInMessageBeforeDigesting(Boolean invertPositionOfSaltInMessageBeforeDigesting) {
        this.invertPositionOfSaltInMessageBeforeDigestingEnvName = null;
        this.invertPositionOfSaltInMessageBeforeDigestingSysPropertyName = null;
        super.setInvertPositionOfSaltInMessageBeforeDigesting(invertPositionOfSaltInMessageBeforeDigesting);
    }

    


    
}
