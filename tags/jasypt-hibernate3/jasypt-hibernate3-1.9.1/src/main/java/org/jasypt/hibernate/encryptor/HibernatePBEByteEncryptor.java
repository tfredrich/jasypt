/*
 * =============================================================================
 * 
 *   Copyright (c) 2007-2010, The JASYPT team (http://www.jasypt.org)
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
package org.jasypt.hibernate.encryptor;

import org.jasypt.encryption.pbe.PBEByteEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.salt.SaltGenerator;

/**
 * <p>
 * Placeholder class for <tt>PBEByteEncryptor</tt> objects which are
 * eligible for use from Hibernate. 
 * </p>
 * <p>
 * This class acts as a wrapper on a <tt>PBEByteEncryptor</tt>, allowing
 * to be set a <b>registered name</b> (see {@link #setRegisteredName(String)})
 * and performing the needed registry operations against the 
 * {@link HibernatePBEEncryptorRegistry}.
 * </p>
 * <p>
 * <b>It is not mandatory that a <tt>PBEByteEncryptor</tt> be explicitly set
 * with {@link #setEncryptor(PBEByteEncryptor)}</b>. If not, a
 * <tt>StandardPBEByteEncryptor</tt> object will be created internally
 * and it will be configurable with the 
 * {@link #setPassword(String)}/{@link #setPasswordCharArray(char[])},
 * {@link #setAlgorithm(String)}, {@link #setKeyObtentionIterations(int)},
 * {@link #setSaltGenerator(SaltGenerator)}
 * and  {@link #setConfig(PBEConfig)} methods.
 * </p>
 * <p>
 * This class is mainly intended for use from Spring Framework or some other
 * IoC container (if you are not using a container of this kind, please see 
 * {@link HibernatePBEEncryptorRegistry}). The steps to be performed are 
 * the following:
 * <ol>
 *   <li>Create an object of this class (declaring it).</li>
 *   <li>Set its <tt>registeredName</tt> and, either its 
 *       wrapped <tt>encryptor</tt> or its <tt>password</tt>, 
 *       <tt>algorithm</tt>, <tt>keyObtentionIterations</tt>,
 *       <tt>saltGenerator</tt> and <tt>config</tt> properties.</li>
 *   <li>Declare a <i>typedef</i> in a Hibernate mapping giving its
 *       <tt>encryptorRegisteredName</tt> parameter the same value specified
 *       to this object in <tt>registeredName</tt>.</li>
 * </ol>
 * </p>
 * <p>
 * This in a Spring config file would look like:
 * </p>
 * <p>
 * <pre> 
 *  ...
 *  &lt;-- Optional, as the hibernateEncryptor could be directly set an     -->
 *  &lt;-- algorithm and password.                                          -->
 *  &lt;bean id="byteEncryptor"
 *    class="org.jasypt.encryption.pbe.StandardPBEByteEncryptor">
 *    &lt;property name="algorithm">
 *        &lt;value>PBEWithMD5AndDES&lt;/value>
 *    &lt;/property>
 *    &lt;property name="password">
 *        &lt;value>XXXXX&lt;/value>
 *    &lt;/property>
 *  &lt;/bean>
 *  
 *  &lt;bean id="hibernateEncryptor"
 *    class="org.jasypt.hibernate.encryptor.HibernatePBEByteEncryptor">
 *    &lt;property name="registeredName">
 *        &lt;value><b>myHibernateByteEncryptor</b>&lt;/value>
 *    &lt;/property>
 *    &lt;property name="encryptor">
 *        &lt;ref bean="byteEncryptor" />
 *    &lt;/property>
 *  &lt;/bean>
 *  ...
 * </pre>
 * </p>
 * <p>
 * And then in the Hibernate mapping file:
 * </p>
 * <p>
 * <pre>
 *    &lt;typedef name="encrypted" class="org.jasypt.hibernate.type.EncryptedBinaryType">
 *      &lt;param name="encryptorRegisteredName"><b>myHibernateByteEncryptor</b>&lt;/param>
 *    &lt;/typedef>
 * </pre>
 * </p>
 * <p>
 * An important thing to note is that, when using <tt>HibernatePBEByteEncryptor</tt>
 * objects this way to wrap <tt>PBEByteEncryptor</tt>s, <u>it is not
 * necessary to deal with {@link HibernatePBEEncryptorRegistry}</u>, 
 * because <tt>HibernatePBEByteEncryptor</tt> objects get automatically registered
 * in the encryptor registry when their {@link #setRegisteredName(String)}
 * method is called.
 * </p>
 * 
 * @since 1.2
 * 
 * @author Daniel Fern&aacute;ndez
 * 
 * @deprecated Will be removed in 1.11. Package org.jasypt.hibernate.connectionprovider
 *             has been renamed as org.jasypt.hibernate3.connectionprovider.
 * 
 */
public final class HibernatePBEByteEncryptor {

    private String registeredName = null;
    private PBEByteEncryptor encryptor = null;
    private boolean encryptorSet = false;
    
    
    
    /**
     * Creates a new instance of <tt>HibernatePBEByteEncryptor</tt> It also
     * creates a <tt>StandardPBEByteEncryptor</tt> for internal use, which
     * can be overriden by calling <tt>setEncryptor(...)</tt>. 
     */
    public HibernatePBEByteEncryptor() {
        super();
        this.encryptor = new StandardPBEByteEncryptor();
        this.encryptorSet = false;
    }


    /*
     * For internal use only, by the Registry, when a PBEByteEncryptor
     * is registered programmatically.
     */
    HibernatePBEByteEncryptor(final String registeredName, 
            final PBEByteEncryptor encryptor) {
        this.encryptor = encryptor;
        this.registeredName = registeredName;
        this.encryptorSet = true;
    }


    /**
     * Returns the encryptor which this object wraps.
     * 
     * @return the encryptor.
     */
    public PBEByteEncryptor getEncryptor() {
        return this.encryptor;
    }
    
    
    /**
     * Sets the <tt>PBEByteEncryptor</tt> to be held (wrapped) by this
     * object. This method is optional and can be only called once.
     * 
     * @param encryptor the encryptor.
     */
    public void setEncryptor(final PBEByteEncryptor encryptor) {
        if (this.encryptorSet) {
            throw new EncryptionInitializationException(
                    "An encryptor has been already set: no " +
                    "further configuration possible on hibernate wrapper");
        }
        this.encryptor = encryptor;
        this.encryptorSet = true;
    }


    /**
     * Sets the password to be used by the internal encryptor, if a specific
     * encryptor has not been set with <tt>setEncryptor(...)</tt>.
     * 
     * @param password the password to be set for the internal encryptor
     */
    public void setPassword(final String password) {
        if (this.encryptorSet) {
            throw new EncryptionInitializationException(
                    "An encryptor has been already set: no " +
                    "further configuration possible on hibernate wrapper");
        }
        final StandardPBEByteEncryptor standardPBEByteEncryptor =
            (StandardPBEByteEncryptor) this.encryptor;
        standardPBEByteEncryptor.setPassword(password);
    }


    /**
     * Sets the password to be used by the internal encryptor (as a char[]), if a specific
     * encryptor has not been set with <tt>setEncryptor(...)</tt>.
     *
     * @since 1.8
     * @param password the password to be set for the internal encryptor
     */
    public void setPasswordCharArray(final char[] password) {
        if (this.encryptorSet) {
            throw new EncryptionInitializationException(
                    "An encryptor has been already set: no " +
                    "further configuration possible on hibernate wrapper");
        }
        final StandardPBEByteEncryptor standardPBEByteEncryptor =
            (StandardPBEByteEncryptor) this.encryptor;
        standardPBEByteEncryptor.setPasswordCharArray(password);
    }


    /**
     * Sets the algorithm to be used by the internal encryptor, if a specific
     * encryptor has not been set with <tt>setEncryptor(...)</tt>.
     * 
     * @param algorithm the algorithm to be set for the internal encryptor
     */
    public void setAlgorithm(final String algorithm) {
        if (this.encryptorSet) {
            throw new EncryptionInitializationException(
                    "An encryptor has been already set: no " +
                    "further configuration possible on hibernate wrapper");
        }
        final StandardPBEByteEncryptor standardPBEByteEncryptor =
            (StandardPBEByteEncryptor) this.encryptor;
        standardPBEByteEncryptor.setAlgorithm(algorithm);
    }
    

    /**
     * Sets the key obtention iterations to be used by the internal encryptor, 
     * if a specific encryptor has not been set with <tt>setEncryptor(...)</tt>.
     * 
     * @param keyObtentionIterations to be set for the internal encryptor
     */
    public void setKeyObtentionIterations(final int keyObtentionIterations) {
        if (this.encryptorSet) {
            throw new EncryptionInitializationException(
                    "An encryptor has been already set: no " +
                    "further configuration possible on hibernate wrapper");
        }
        final StandardPBEByteEncryptor standardPBEByteEncryptor =
            (StandardPBEByteEncryptor) this.encryptor;
        standardPBEByteEncryptor.setKeyObtentionIterations(
                keyObtentionIterations);
    }
    

    /**
     * Sets the salt generator to be used by the internal encryptor, 
     * if a specific encryptor has not been set with <tt>setEncryptor(...)</tt>.
     * 
     * @param saltGenerator the salt generator to be set for the internal
     *                      encryptor.
     */
    public void setSaltGenerator(final SaltGenerator saltGenerator) {
        if (this.encryptorSet) {
            throw new EncryptionInitializationException(
                    "An encryptor has been already set: no " +
                    "further configuration possible on hibernate wrapper");
        }
        final StandardPBEByteEncryptor standardPBEByteEncryptor =
            (StandardPBEByteEncryptor) this.encryptor;
        standardPBEByteEncryptor.setSaltGenerator(saltGenerator);
    }


    /**
     * Sets the PBEConfig to be used by the internal encryptor, 
     * if a specific encryptor has not been set with <tt>setEncryptor(...)</tt>.
     * 
     * @param config the PBEConfig to be set for the internal encryptor
     */
    public void setConfig(final PBEConfig config) {
        if (this.encryptorSet) {
            throw new EncryptionInitializationException(
                    "An encryptor has been already set: no " +
                    "further configuration possible on hibernate wrapper");
        }
        final StandardPBEByteEncryptor standardPBEByteEncryptor =
            (StandardPBEByteEncryptor) this.encryptor;
        standardPBEByteEncryptor.setConfig(config);
    }


    /**
     * Encrypts a message, delegating to wrapped encryptor.
     * 
     * @param message the message to be encrypted.
     * @return the encryption result.
     */
    public byte[] encrypt(final byte[] message) {
        if (this.encryptor == null) {
            throw new EncryptionInitializationException(
                    "Encryptor has not been set into Hibernate wrapper");
        }
        return this.encryptor.encrypt(message);
    }

    
    /**
     * Decypts a message, delegating to wrapped encryptor
     * 
     * @param encryptedMessage the message to be decrypted.
     * @return the result of decryption.
     */
    public byte[] decrypt(final byte[] encryptedMessage) {
        if (this.encryptor == null) {
            throw new EncryptionInitializationException(
                    "Encryptor has not been set into Hibernate wrapper");
        }
        return this.encryptor.decrypt(encryptedMessage);
    }
    

    
    /**
     * Sets the registered name of the encryptor and adds it to the registry.
     * 
     * @param registeredName the name with which the encryptor will be
     *                       registered.
     */
    public void setRegisteredName(final String registeredName) {
        if (this.registeredName != null) {
            // It had another name before, we have to clean
            HibernatePBEEncryptorRegistry.getInstance().
                    unregisterHibernatePBEByteEncryptor(this.registeredName);
        }
        this.registeredName = registeredName;
        HibernatePBEEncryptorRegistry.getInstance().
                registerHibernatePBEByteEncryptor(this);
    }

    /**
     * Returns the name with which the wrapped encryptor is registered at
     * the registry.
     * 
     * @return the registered name.
     */
    public String getRegisteredName() {
        return this.registeredName;
    }
    
}
