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
package org.jasypt.encryption.pbe;

import java.security.Provider;

import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.salt.SaltGenerator;



/**
 * <p>
 * Pooled implementation of {@link PBEByteEncryptor} that in fact contains
 * an array of {@link StandardPBEByteEncryptor} objects which are used
 * to attend encrypt and decrypt requests in round-robin. This should
 * result in higher performance in multiprocessor systems.
 * </p>
 * <p>
 * Configuration of this class is equivalent to that of
 * {@link StandardPBEByteEncryptor}.
 * </p>
 * <p>
 * This class is <i>thread-safe</i>.
 * </p>
 * 
 * 
 * @since 1.7
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
public final class PooledStandardPBEByteEncryptor implements PBEByteEncryptor {

    
    private final int poolSize;
    private final StandardPBEByteEncryptor[] pool;
    private int roundRobin = 0;
    
    
    /*
     * Flag which indicates whether the encryptor has been initialized or not.
     * 
     * Once initialized, no further modifications to its configuration will
     * be allowed.
     */
    private boolean initialized = false;

    
    
    
    /**
     * Creates a new instance of <tt>PooledStandardPBEByteEncryptor</tt>.
     */
    public PooledStandardPBEByteEncryptor(final int poolSize) {
        super();
        if (poolSize < 1) {
            throw new IllegalArgumentException("Pool size must be > 0");
        }
        this.poolSize = poolSize;
        this.pool = new StandardPBEByteEncryptor[this.poolSize];
        this.pool[0] = new StandardPBEByteEncryptor();
    }

    
    
    /**
     * <p>
     * Sets a <tt>{@link org.jasypt.encryption.pbe.config.PBEConfig}</tt> object 
     * for the encryptor. If this config
     * object is set, it will be asked values for:
     * </p>
     * 
     * <ul>
     *   <li>Algorithm</li>
     *   <li>Security Provider (or provider name)</li>
     *   <li>Password</li>
     *   <li>Hashing iterations for obtaining the encryption key</li>
     *   <li>Salt generator</li>
     * </ul>
     * 
     * <p>
     * The non-null values it returns will override the default ones, 
     * <i>and will be overriden by any values specified with a <tt>setX</tt>
     * method</i>.
     * </p>
     * 
     * @param config the <tt>PBEConfig</tt> object to be used as the 
     *               source for configuration parameters.
     */
    public synchronized void setConfig(PBEConfig config) {
        this.pool[0].setConfig(config);
    }

    
    /**
     * <p>
     * Sets the algorithm to be used for encryption, like 
     * <tt>PBEWithMD5AndDES</tt>.
     * </p>
     * <p>
     * This algorithm has to be supported by your JCE provider (if you specify
     * one, or the default JVM provider if you don't) and, if it is supported,
     * you can also specify <i>mode</i> and <i>padding</i> for 
     * it, like <tt>ALGORITHM/MODE/PADDING</tt>.
     * </p>
     * 
     * @param algorithm the name of the algorithm to be used.
     */
    public synchronized void setAlgorithm(String algorithm) {
        this.pool[0].setAlgorithm(algorithm);
    }
    
    
    /**
     * <p>
     * Sets the password to be used.
     * </p>
     * <p>
     * <b>There is no default value for password</b>, so not setting
     * this parameter either from a 
     * {@link org.jasypt.encryption.pbe.config.PBEConfig} object or from
     * a call to <tt>setPassword</tt> will result in an
     * EncryptionInitializationException being thrown during initialization.
     * </p>
     * 
     * @param password the password to be used.
     */
    public synchronized void setPassword(String password) {
        this.pool[0].setPassword(password);
    }
    
    
    /**
     * <p>
     * Set the number of hashing iterations applied to obtain the
     * encryption key.
     * </p>
     * <p>
     * This mechanism is explained in 
     * <a href="http://www.rsasecurity.com/rsalabs/node.asp?id=2127" 
     * target="_blank">PKCS &#035;5: Password-Based Cryptography Standard</a>.
     * </p>
     * 
     * @param keyObtentionIterations the number of iterations
     */
    public synchronized void setKeyObtentionIterations(
            int keyObtentionIterations) {
        this.pool[0].setKeyObtentionIterations(keyObtentionIterations);
    }

    
    /**
     * <p>
     * Sets the salt generator to be used. If no salt generator is specified,
     * an instance of {@link org.jasypt.salt.RandomSaltGenerator} will be used. 
     * </p>
     * 
     * @param saltGenerator the salt generator to be used.
     */
    public synchronized void setSaltGenerator(SaltGenerator saltGenerator) {
        this.pool[0].setSaltGenerator(saltGenerator);
    }
    
    
    /**
     * <p>
     * Sets the name of the security provider to be asked for the
     * encryption algorithm. This security provider has to be registered 
     * beforehand at the JVM security framework. 
     * </p>
     * <p>
     * The provider can also be set with the {@link #setProvider(Provider)}
     * method, in which case it will not be necessary neither registering
     * the provider beforehand,
     * nor calling this {@link #setProviderName(String)} method to specify
     * a provider name.
     * </p>
     * <p>
     * Note that a call to {@link #setProvider(Provider)} overrides any value 
     * set by this method.
     * </p>
     * <p>
     * If no provider name / provider is explicitly set, the default JVM
     * provider will be used.
     * </p>
     * 
     * @param providerName the name of the security provider to be asked
     *                     for the encryption algorithm.
     */
    public synchronized void setProviderName(String providerName) {
        this.pool[0].setProviderName(providerName);
    }
    
    
    /**
     * <p>
     * Sets the security provider to be asked for the encryption algorithm.
     * The provider does not have to be registered at the security 
     * infrastructure beforehand, and its being used here will not result in
     * its being registered.
     * </p>
     * <p>
     * If this method is called, calling {@link #setProviderName(String)}
     * becomes unnecessary.
     * </p>
     * <p>
     * If no provider name / provider is explicitly set, the default JVM
     * provider will be used.
     * </p>
     * 
     * @param provider the provider to be asked for the chosen algorithm
     */
    public synchronized void setProvider(Provider provider) {
        this.pool[0].setProvider(provider);
    }

    
    
    
    /**
     * <p>
     *   Returns true if the encryptor has already been initialized, false if
     *   not.<br/> 
     *   Initialization happens:
     * </p>
     * <ul>
     *   <li>When <tt>initialize</tt> is called.</li>
     *   <li>When <tt>encrypt</tt> or <tt>decrypt</tt> are called for the
     *       first time, if <tt>initialize</tt> has not been called before.</li>
     * </ul>
     * <p>
     *   Once an encryptor has been initialized, trying to
     *   change its configuration will
     *   result in an <tt>AlreadyInitializedException</tt> being thrown.
     * </p>
     * 
     * @return true if the encryptor has already been initialized, false if
     *         not.
     */
    public boolean isInitialized() {
        return this.initialized;
    }

    
    /**
     * <p>
     * Initialize the encryptor.
     * </p>
     * <p>
     * This operation will consist in determining the actual configuration 
     * values to be used, and then initializing the encryptor with them.
     * <br/>
     * These values are decided by applying the following priorities:
     * </p>
     * <ol>
     *   <li>First, the default values are considered (except for password).
     *   </li>
     *   <li>Then, if a 
     *       <tt>{@link org.jasypt.encryption.pbe.config.PBEConfig}</tt> 
     *       object has been set with
     *       <tt>setConfig</tt>, the non-null values returned by its
     *       <tt>getX</tt> methods override the default values.</li>
     *   <li>Finally, if the corresponding <tt>setX</tt> method has been called
     *       on the encryptor itself for any of the configuration parameters, 
     *       the values set by these calls override all of the above.</li>
     * </ol>
     * <p>
     *   Once an encryptor has been initialized, trying to
     *   change its configuration will
     *   result in an <tt>AlreadyInitializedException</tt> being thrown.
     * </p>
     * 
     * @throws EncryptionInitializationException if initialization could not
     *         be correctly done (for example, no password has been set).
     */
    public synchronized void initialize() {
        
        // Double-check to avoid synchronization issues
        if (!this.initialized) {

            for (int i = 1; i < this.poolSize; i++) {
                this.pool[i] = this.pool[i - 1].cloneEncryptor();
            }
            
            this.initialized = true;
            
        }
            
    }


    /**
     * <p>
     * Encrypts a message using the specified configuration.
     * </p>
     * <p>
     * The mechanisms applied to perform the encryption operation are described
     * in <a href="http://www.rsasecurity.com/rsalabs/node.asp?id=2127" 
     * target="_blank">PKCS &#035;5: Password-Based Cryptography Standard</a>.
     * </p>
     * <p>
     * This encryptor uses a salt for each encryption
     * operation. The size of the salt depends on the algorithm
     * being used. This salt is used
     * for creating the encryption key and, if generated by a random generator,
     * it is also appended unencrypted at the beginning
     * of the results so that a decryption operation can be performed.
     * </p>
     * <p>
     * <b>If a random salt generator is used, two encryption results for 
     * the same message will always be different
     * (except in the case of random salt coincidence)</b>. This may enforce
     * security by difficulting brute force attacks on sets of data at a time
     * and forcing attackers to perform a brute force attack on each separate
     * piece of encrypted data.
     * </p>
     * 
     * @param message the byte array message to be encrypted
     * @return the result of encryption 
     * @throws EncryptionOperationNotPossibleException if the encryption 
     *         operation fails, ommitting any further information about the
     *         cause for security reasons.
     * @throws EncryptionInitializationException if initialization could not
     *         be correctly done (for example, no password has been set).
     */
    public byte[] encrypt(final byte[] message) 
            throws EncryptionOperationNotPossibleException {

        // Check initialization
        if (!isInitialized()) {
            initialize();
        }
        
        int poolPosition;
        synchronized(this) {
            poolPosition = this.roundRobin;
            this.roundRobin = (this.roundRobin + 1) % this.poolSize;
        }
        
        return this.pool[poolPosition].encrypt(message);
        
    }


    /**
     * <p>
     * Decrypts a message using the specified configuration.
     * </p>
     * <p>
     * The mechanisms applied to perform the decryption operation are described
     * in <a href="http://www.rsasecurity.com/rsalabs/node.asp?id=2127" 
     * target="_blank">PKCS &#035;5: Password-Based Cryptography Standard</a>.
     * </p>
     * <p>
     * If a random salt generator is used, this decryption operation will
     * expect to find an unencrypted salt at the 
     * beginning of the encrypted input, so that the decryption operation can be
     * correctly performed (there is no other way of knowing it).
     * </p>
     * 
     * @param encryptedMessage the byte array message to be decrypted
     * @return the result of decryption 
     * @throws EncryptionOperationNotPossibleException if the decryption 
     *         operation fails, ommitting any further information about the
     *         cause for security reasons.
     * @throws EncryptionInitializationException if initialization could not
     *         be correctly done (for example, no password has been set).
     */
    public byte[] decrypt(final byte[] encryptedMessage) 
            throws EncryptionOperationNotPossibleException {

        // Check initialization
        if (!isInitialized()) {
            initialize();
        }
        
        int poolPosition;
        synchronized(this) {
            poolPosition = this.roundRobin;
            this.roundRobin = (this.roundRobin + 1) % this.poolSize;
        }
        
        return this.pool[poolPosition].decrypt(encryptedMessage);
        
    }    
    
}

