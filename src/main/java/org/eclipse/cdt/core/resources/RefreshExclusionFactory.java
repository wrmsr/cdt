/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.resources;

/**
 * Responsible for manufacturing a given type of RefreshExclusion. Called by the RefreshScopeManager when
 * loading persisted settings to instantiate exclusion objects.
 *
 * @author crecoskie
 * @since 5.3
 *
 */
public abstract class RefreshExclusionFactory
{

    /**
     * Creates a new RefreshExclusion.
     *
     * @return RefreshExclusion
     */
    abstract public RefreshExclusion createNewExclusion();

    /**
     * Creates a new ExclusionInstance
     *
     * @return ExclusionInstance
     */
    abstract public ExclusionInstance createNewExclusionInstance();

    /**
     * Returns the fully qualified classname of the type of the object that will be returned by
     * org.eclipse.cdt.core.resources.RefreshExclusionFactory.createNewExclusion()
     *
     * @return String
     */
    abstract public String getExclusionClassname();

    /**
     * Returns the fully qualified classname of the type of the object that will be returned by
     * org.eclipse.cdt.core.resources.RefreshExclusionFactory.createNewExclusionInstance()
     *
     * @return String
     */
    abstract public String getInstanceClassname();
}
