/*******************************************************************************
 * Copyright (c) 2009 Wind River Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Markus Schorn - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.c;

import org.eclipse.cdt.core.dom.ast.IBasicType;
import org.eclipse.cdt.core.dom.ast.IBasicType.Kind;
import org.eclipse.cdt.core.dom.ast.IType;
import org.eclipse.cdt.core.dom.parser.ArithmeticConversion;

public class CArithmeticConversion
        extends ArithmeticConversion
{
    private static CArithmeticConversion sInstance = new CArithmeticConversion();

    public static IType convertCOperandTypes(int operator, IType t1, IType t2)
    {
        return sInstance.convertOperandTypes(operator, t1, t2);
    }

    public static IType promoteCType(IType type)
    {
        return sInstance.promoteType(type);
    }

    private CArithmeticConversion() {}

    @Override
    protected IBasicType createBasicType(Kind kind, int modifiers)
    {
        return new CBasicType(kind, modifiers);
    }
}
