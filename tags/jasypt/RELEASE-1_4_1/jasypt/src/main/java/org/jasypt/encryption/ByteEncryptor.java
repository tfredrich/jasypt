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
package org.jasypt.encryption;

/**
 * <p>
 * Common interface for all Encryptors which receive a 
 * byte array message and return a byte array result.
 * </p>
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez Garrido
 * 
 */
public interface ByteEncryptor {
    
    
    /**
     * Encrypt the input message
     * 
     * @param message the message to be encrypted
     * @return the result of encryption
     */
    public byte[] encrypt(byte[] message);

    /**
     * Decrypt an encrypted message
     * 
     * @param encryptedMessage the encrypted message to be decrypted
     * @return the result of decryption
     */
    public byte[] decrypt(byte[] encryptedMessage);
    
}
