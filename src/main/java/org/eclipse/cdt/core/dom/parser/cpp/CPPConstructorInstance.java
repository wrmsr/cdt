/*******************************************************************************
 * Copyright (c) 2007, 2010 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Bryan Wilkinson (QNX) - Initial API and implementation
 * Markus Schorn (Wind River Systems)
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.cpp;

import org.eclipse.cdt.core.dom.ast.IType;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPClassType;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPConstructor;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPFunctionType;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPTemplateArgument;

/**
 * Instantiation of a constructor template
 */
public class CPPConstructorInstance
        extends CPPMethodInstance
        implements ICPPConstructor
{

    public CPPConstructorInstance(ICPPConstructor orig, ICPPClassType owner,
            CPPTemplateParameterMap tpmap, ICPPTemplateArgument[] args, ICPPFunctionType type, IType[] exceptionSpec)
    {
        super(orig, owner, tpmap, args, type, exceptionSpec);
    }
}
