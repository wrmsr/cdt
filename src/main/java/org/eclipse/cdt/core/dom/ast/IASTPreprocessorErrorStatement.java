/*******************************************************************************
 * Copyright (c) 2004, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * John Camelon (IBM) - Initial API and implementation
 * Emanuel Graf (IFS)
 *******************************************************************************/
package org.eclipse.cdt.core.dom.ast;

/**
 * This interface represent a preprocessor #error statement.
 *
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IASTPreprocessorErrorStatement
        extends
        IASTPreprocessorStatement
{
    /**
     * The Error Message.
     *
     * @return the Message
     */
    public char[] getMessage();
}
