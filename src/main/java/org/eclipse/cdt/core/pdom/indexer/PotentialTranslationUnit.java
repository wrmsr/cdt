/*******************************************************************************
 * Copyright (c) 2010 Wind River Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Markus Schorn - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.pdom.indexer;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.model.ICElement;
import org.eclipse.cdt.core.model.TranslationUnit;
import org.eclipse.core.resources.IFile;

/**
 * Used for modified files that are not below a source root. In case such a file
 * is part of the index it needs to be updated, otherwise it shall be ignored.
 */
public class PotentialTranslationUnit
        extends TranslationUnit
{
    public PotentialTranslationUnit(ICElement parent, IFile file)
    {
        super(parent, file, CCorePlugin.CONTENT_TYPE_CHEADER);
    }
}
