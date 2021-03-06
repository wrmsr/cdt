/*******************************************************************************
 * Copyright (c) 2005, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * John Camelon (IBM Rational Software) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.c;

import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.parser.ASTEnumerator;

/**
 * C-specific enumerator
 */
public class CASTEnumerator
        extends ASTEnumerator
{

    public CASTEnumerator()
    {
        super();
    }

    public CASTEnumerator(IASTName name, IASTExpression value)
    {
        super(name, value);
    }

    @Override
    public CASTEnumerator copy()
    {
        return copy(CopyStyle.withoutLocations);
    }

    @Override
    public CASTEnumerator copy(CopyStyle style)
    {
        return copy(new CASTEnumerator(), style);
    }
}
