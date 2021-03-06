/*******************************************************************************
 * Copyright (c) 2014 Google, Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Sergey Prigogin (Google) - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.cpp;

import org.eclipse.cdt.core.dom.ast.IEnumerator;
import org.eclipse.cdt.core.dom.ast.IType;

/**
 * Non API methods for C++ enumerators.
 */
public interface ICPPInternalEnumerator
        extends IEnumerator
{
    /**
     * Returns the internal type of the enumerator. The enumerator has this type between the opening
     * and the closing braces of the enumeration ([dcl.enum] 7.2-5).
     *
     * @param type the integral type of the enumerator's initializing value
     */
    IType getInternalType();
}
