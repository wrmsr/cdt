/*******************************************************************************
 * Copyright (c) 2014 Institute for Software, HSR Hochschule fuer Technik
 * Rapperswil, University of applied sciences.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Thomas Corbat (IFS) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser;

import org.eclipse.cdt.core.dom.ast.gnu.IGCCASTAttributeSpecifier;
import org.eclipse.cdt.core.dom.parser.cpp.ASTAttributeSpecifier;

/**
 * Represents a GCC attribute specifier, containing attributes.
 */
public class GCCASTAttributeSpecifier
        extends ASTAttributeSpecifier
        implements IGCCASTAttributeSpecifier
{
    @Override
    public GCCASTAttributeSpecifier copy(CopyStyle style)
    {
        return copy(new GCCASTAttributeSpecifier(), style);
    }

    @Override
    public GCCASTAttributeSpecifier copy()
    {
        return copy(CopyStyle.withoutLocations);
    }
}
