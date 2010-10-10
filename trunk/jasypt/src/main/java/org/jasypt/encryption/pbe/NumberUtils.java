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
package org.jasypt.encryption.pbe;


/**
 * 
 * Utils for processing numbers in encryptors. Intended only for internal
 * use within jasypt.
 * 
 * @since 1.2
 * 
 * @author Daniel Fern&aacute;ndez
 *
 */
class NumberUtils {

    
    static byte[] byteArrayFromInt(final int number) {
        final byte b0 = (byte) (0xff & number);
        final byte b1 = (byte) (0xff & (number >> 8));
        final byte b2 = (byte) (0xff & (number >> 16));
        final byte b3 = (byte) (0xff & (number >> 24));
        return new byte[] {b3,b2,b1,b0};
    }
   
   
    static int intFromByteArray(final byte[] byteArray) {
        
        if (byteArray == null || byteArray.length == 0) {
            throw new IllegalArgumentException(
                    "Cannot convert an empty array into an int");
        }
        int result = (0xff & byteArray[0]);
        for (int i = 0; i < byteArray.length; i++) {
            result = (result << 8) | (0xff & byteArray[i]);
        }
        return result;
    }

    
    static byte[] processBigIntegerEncryptedByteArray(
            final byte[] byteArray, final int signum) {
        
        // Check size
        if (byteArray.length > 4) {
            
            final int initialSize = byteArray.length;
            
            final byte[] encryptedMessageExpectedSizeBytes = new byte[4];
            System.arraycopy(byteArray, (initialSize - 4), encryptedMessageExpectedSizeBytes, 0, 4);
            
            final byte[] processedByteArray = new byte[initialSize - 4];
            System.arraycopy(byteArray, 0, processedByteArray, 0, (initialSize - 4));
            
            final int expectedSize = 
                NumberUtils.intFromByteArray(encryptedMessageExpectedSizeBytes);

            // If expected and real sizes do not match, we will need to pad
            // (this happens because BigInteger removes 0x0's and -0x1's in
            // the leftmost side).
            if (processedByteArray.length != expectedSize) {

                // BigInteger can have removed, in the leftmost side:
                //      * 0x0's: for not being significative
                //      * -0x1's: for being translated as the "signum"
                final int sizeDifference = 
                    (expectedSize - processedByteArray.length);

                final byte[] paddedProcessedByteArray = new byte[expectedSize];
                for (int i = 0; i < sizeDifference; i++) {
                    paddedProcessedByteArray[i] = (signum >= 0)? (byte)0x0 : (byte)-0x1;
                }
                    

                // Finally, the encrypted message bytes are represented
                // as they supposedly were when they were encrypted.
                System.arraycopy(processedByteArray, 0, paddedProcessedByteArray, sizeDifference, processedByteArray.length);
                
                return paddedProcessedByteArray;
                
            }
            
            return processedByteArray;
            
        }
            
        return (byte[]) byteArray.clone();
        
    }
    
    
    private NumberUtils() {
        super();
    }
    
}
