/*
 * =============================================================================
 * 
 *   Copyright (c) 2007, The JASYPT team (http://www.jasypt.org)
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
package org.jasypt.intf.cli;

import java.util.Properties;

import org.apache.commons.lang.ArrayUtils;
import org.jasypt.intf.service.JasyptStatelessService;

public class JasyptPBEStringEncryptionCLI {
    
    private static final String[][] VALID_REQUIRED_ARGUMENTS =
        new String[][] {
            new String [] {
                ArgumentNaming.ARG_INPUT
            },
            new String [] {
                ArgumentNaming.ARG_PASSWORD,
                ArgumentNaming.ARG_PASSWORD_ENV_NAME
            }
        };
    
    private static final String[][] VALID_OPTIONAL_ARGUMENTS =
        new String[][] {
            new String [] {
                ArgumentNaming.ARG_VERBOSE
            },
            new String [] {
                ArgumentNaming.ARG_ALGORITHM,
                ArgumentNaming.ARG_ALGORITHM_ENV_NAME
            },
            new String [] {
                ArgumentNaming.ARG_KEY_OBTENTION_ITERATIONS,
                ArgumentNaming.ARG_KEY_OBTENTION_ITERATIONS_ENV_NAME
            },
            new String [] {
                ArgumentNaming.ARG_SALT_GENERATOR_CLASS_NAME,
                ArgumentNaming.ARG_SALT_GENERATOR_CLASS_NAME_ENV_NAME
            },
            new String [] {
                ArgumentNaming.ARG_PROVIDER_NAME,
                ArgumentNaming.ARG_PROVIDER_NAME_ENV_NAME
            },
            new String [] {
                ArgumentNaming.ARG_PROVIDER_CLASS_NAME,
                ArgumentNaming.ARG_PROVIDER_CLASS_NAME_ENV_NAME
            },
            new String [] {
                ArgumentNaming.ARG_STRING_OUTPUT_TYPE,
                ArgumentNaming.ARG_STRING_OUTPUT_TYPE_ENV_NAME
            }
        };
    
    
    public static void main(String[] args) {

        boolean verbose = ArgumentUtils.getVerbosity(args);

        try {
            
            String applicationName = null;
            String[] arguments = null;
            if (args[0] == null || args[0].indexOf("=") != -1) {
                applicationName = JasyptPBEStringEncryptionCLI.class.getName();
                arguments = args;
            } else {
                applicationName = args[0];
                arguments = (String[]) ArrayUtils.subarray(args, 1, args.length);
            }
            
            Properties argumentValues = 
                ArgumentUtils.getArgumentValues(
                        applicationName, arguments, 
                        VALID_REQUIRED_ARGUMENTS, VALID_OPTIONAL_ARGUMENTS);

            ArgumentUtils.showEnvironment(verbose);

            JasyptStatelessService service = new JasyptStatelessService();

            String input = argumentValues.getProperty(ArgumentNaming.ARG_INPUT);

            ArgumentUtils.showArgumentDescription(argumentValues, verbose);
            
            String result =
                service.encrypt(
                        input, 
                        argumentValues.getProperty(ArgumentNaming.ARG_PASSWORD),
                        argumentValues.getProperty(ArgumentNaming.ARG_PASSWORD_ENV_NAME),
                        null,
                        argumentValues.getProperty(ArgumentNaming.ARG_ALGORITHM),
                        argumentValues.getProperty(ArgumentNaming.ARG_ALGORITHM_ENV_NAME),
                        null,
                        argumentValues.getProperty(ArgumentNaming.ARG_KEY_OBTENTION_ITERATIONS),
                        argumentValues.getProperty(ArgumentNaming.ARG_KEY_OBTENTION_ITERATIONS_ENV_NAME),
                        null,
                        argumentValues.getProperty(ArgumentNaming.ARG_SALT_GENERATOR_CLASS_NAME),
                        argumentValues.getProperty(ArgumentNaming.ARG_SALT_GENERATOR_CLASS_NAME_ENV_NAME),
                        null,
                        argumentValues.getProperty(ArgumentNaming.ARG_PROVIDER_NAME),
                        argumentValues.getProperty(ArgumentNaming.ARG_PROVIDER_NAME_ENV_NAME),
                        null,
                        argumentValues.getProperty(ArgumentNaming.ARG_PROVIDER_CLASS_NAME),
                        argumentValues.getProperty(ArgumentNaming.ARG_PROVIDER_CLASS_NAME_ENV_NAME),
                        null,
                        argumentValues.getProperty(ArgumentNaming.ARG_STRING_OUTPUT_TYPE),
                        argumentValues.getProperty(ArgumentNaming.ARG_STRING_OUTPUT_TYPE_ENV_NAME),
                        null);
            
            ArgumentUtils.showOutput(result, verbose);

        } catch (Throwable t) {
            ArgumentUtils.showError(t, verbose);
        }
        
    }
    
    
    
    
    private JasyptPBEStringEncryptionCLI() {
        super();
    }
    
}
