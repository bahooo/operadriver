/*
Copyright 2008-2011 Opera Software ASA

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.opera.core.systems.scope.exceptions;

import org.openqa.selenium.WebDriverException;

/**
 * This event is to be used when the STP communication fails in an
 * unrecoverable fashion - connection has been closed.
 *
 * @author Jan Vidar Krey (janv@opera.com)
 */
@SuppressWarnings("serial")
public class CommunicationException extends WebDriverException {


	/**
     * Creates a new instance of <code>CommunicationException</code> without detail message.
     */
    public CommunicationException() {
    }


    /**
     * Constructs an instance of <code>CommunicationException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CommunicationException(String msg) {
        super(msg);
    }
}
