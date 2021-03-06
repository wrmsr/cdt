/*******************************************************************************
 * Copyright (c) 2013, 2013 Andrew Gvozdev and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Andrew Gvozdev - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.utils.envvar;

import org.eclipse.cdt.core.envvar.IEnvironmentVariable;

/**
 * A class to describe changes to environment variables defined by user
 * on CDT Environment page in Preferences.
 *
 * @since 5.5
 */
public interface IEnvironmentChangeEvent
{
    /**
     * @return an array of environment variables before the changes. If there are no variables,
     *    returns an empty array.
     */
    public IEnvironmentVariable[] getOldVariables();

    /**
     * @return an array of environment variables after the changes. If there are no variables,
     *    returns an empty array.
     */
    public IEnvironmentVariable[] getNewVariables();
}
