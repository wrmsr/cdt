/*******************************************************************************
 * Copyright (c) 2000, 2006 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * QNX Software Systems - Initial API and implementation
 *******************************************************************************/

package org.eclipse.cdt.utils.debug;

/**
 * DebugUnknownType
 *
 */
public class DebugUnknownType
        extends DebugType
{

    String name;

    /**
     *
     */
    public DebugUnknownType(String unknown)
    {
        super();
        name = unknown;
    }

    public String getName()
    {
        return name;
    }
}
