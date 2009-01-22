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
package org.jasypt.exceptions;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 * Exception thrown when an error is raised during initialization of
 * an entity.
 * 
 * @since 1.0
 * 
 * @author Daniel Fern&aacute;ndez Garrido
 * 
 */
public class EncryptionInitializationException 
        extends NestableRuntimeException {
    
    private static final long serialVersionUID = 8929638240023639778L;

    public EncryptionInitializationException() {
        super();
    }

    public EncryptionInitializationException(Throwable t) {
        super(t);
    }
    
    public EncryptionInitializationException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public EncryptionInitializationException(String msg) {
        super(msg);
    }

}