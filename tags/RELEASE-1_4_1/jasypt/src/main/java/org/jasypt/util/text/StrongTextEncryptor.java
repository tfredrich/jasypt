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
package org.jasypt.util.text;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * <p>
 * Utility class for easily performing high-strength encryption of texts.
 * </p>
 * <p>
 * This class internally holds a {@link StandardPBEStringEncryptor} 
 * configured this way:
 * <ul>
 *   <li>Algorithm: <tt>PBEWithMD5AndTripleDES</tt>.</li>
 *   <li>Key obtention iterations: <tt>1000</tt>.</li>
 * </ul>
 * </p>
 * <p>
 * The required steps to use it are:
 * <ol>
 *   <li>Create an instance (using <tt>new</tt>).</li>
 *   <li>Set a password (using <tt>{@link #setPassword(String)}</tt>).</li>
 *   <li>Perform the desired <tt>{@link #encrypt(String)}</tt> or 
 *       <tt>{@link #decrypt(String)}</tt> operations.</li> 
 * </ol> 
 * </p>
 * <p>
 * To use this class, you may need to download and install the
 * <a href="http://java.sun.com/javase/downloads" target="_blank"><i>Java
 * Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy 
 * Files</i></a>. 
 * </p>
 * <p>
 * This class is <i>thread-safe</i>.
 * </p>
 * 
 * @since 1.2 (class existed in org.jasypt.util package since 1.0)
 * 
 * @author Daniel Fern&aacute;ndez Garrido
 * 
 */
public final class StrongTextEncryptor implements TextEncryptor {

    
    // The internal encryptor 
    private final StandardPBEStringEncryptor encryptor;
    
    

    /**
     * Creates a new instance of <tt>StrongTextEncryptor</tt>.
     */
    public StrongTextEncryptor() {
        super();
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
    }

    
    /**
     * Sets a password.
     * 
     * @param password the password to be set.
     */
    public void setPassword(String password) {
        this.encryptor.setPassword(password);
    }

    
    /**
     * Encrypts a message.
     * 
     * @param message the message to be encrypted.
     * @see StandardPBEStringEncryptor#encrypt(String)
     */
    public String encrypt(String message) {
        return this.encryptor.encrypt(message);
    }
    

    /**
     * Decrypts a message.
     * 
     * @param encryptedMessage the message to be decrypted.
     * @see StandardPBEStringEncryptor#decrypt(String)
     */
    public String decrypt(String encryptedMessage) {
        return this.encryptor.decrypt(encryptedMessage);
    }
    
}
