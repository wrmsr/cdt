/*******************************************************************************
 * Copyright (c) 2005, 2012 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * QNX - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.pdom.db;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import java.io.IOException;

/**
 * @author Doug Schaefer
 */
public class DBStatus
        extends Status
{
    /**
     * @param exception
     */
    public DBStatus(IOException exception)
    {
        super(IStatus.ERROR, CCorePlugin.PLUGIN_ID, 0, "IOException", exception); //$NON-NLS-1$
    }

    public DBStatus(String msg)
    {
        super(IStatus.ERROR, CCorePlugin.PLUGIN_ID, 0, "Error", null); //$NON-NLS-1$
    }
}
