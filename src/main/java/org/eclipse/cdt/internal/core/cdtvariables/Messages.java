/*******************************************************************************
 * Copyright (c) 2009 Wind River Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Wind River Systems - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.internal.core.cdtvariables;

import org.eclipse.osgi.util.NLS;

class Messages
        extends NLS
{
    public static String EclipseVariablesVariableSupplier_illegal_variable;

    static {
        // Initialize resource bundle.
        NLS.initializeMessages(Messages.class.getName(), Messages.class);
    }

    private Messages()
    {
    }
}