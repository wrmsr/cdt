/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Andrew Niefer (IBM Corporation) - initial API and implementation
 * Markus Schorn (Wind River Systems)
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.cpp;

import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTEqualsInitializer;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTInitializer;
import org.eclipse.cdt.core.dom.ast.IASTInitializerClause;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IType;
import org.eclipse.cdt.core.dom.ast.IValue;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTInitializerList;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPParameterPackType;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPTemplateArgument;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPTemplateNonTypeParameter;
import org.eclipse.cdt.core.dom.parser.ASTQueries;
import org.eclipse.cdt.core.dom.parser.Value;
import org.eclipse.cdt.core.dom.parser.cpp.semantics.CPPVisitor;

/**
 * Binding for a non-type template parameter.
 */
public class CPPTemplateNonTypeParameter
        extends CPPTemplateParameter
        implements ICPPTemplateNonTypeParameter
{
    private IType type;

    public CPPTemplateNonTypeParameter(IASTName name)
    {
        super(name);
    }

    @Override
    public IASTExpression getDefault()
    {
        IASTInitializerClause def = getDefaultClause();
        if (def instanceof IASTExpression) {
            return (IASTExpression) def;
        }

        return null;
    }

    public IASTInitializerClause getDefaultClause()
    {
        IASTName[] nds = getDeclarations();
        if (nds == null || nds.length == 0) {
            return null;
        }

        for (IASTName name : nds) {
            if (name != null) {
                IASTNode parent = name.getParent();
                assert parent instanceof IASTDeclarator;
                if (parent instanceof IASTDeclarator) {
                    IASTDeclarator dtor = ASTQueries.findOutermostDeclarator((IASTDeclarator) parent);
                    IASTInitializer initializer = dtor.getInitializer();
                    if (initializer instanceof IASTEqualsInitializer) {
                        return ((IASTEqualsInitializer) initializer).getInitializerClause();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ICPPTemplateArgument getDefaultValue()
    {
        IASTInitializerClause dc = getDefault();
        IASTExpression d = null;
        if (dc instanceof IASTExpression) {
            d = (IASTExpression) dc;
        }
        else if (dc instanceof ICPPASTInitializerList) {
            ICPPASTInitializerList list = (ICPPASTInitializerList) dc;
            switch (list.getSize()) {
                case 0:
                    return new CPPTemplateNonTypeArgument(Value.create(0), getType());
                case 1:
                    dc = list.getClauses()[0];
                    if (dc instanceof IASTExpression) {
                        d = (IASTExpression) dc;
                    }
            }
        }

        if (d == null) {
            return null;
        }

        IValue val = Value.create(d);
        IType t = getType();
        return new CPPTemplateNonTypeArgument(val, t);
    }

    @Override
    public IType getType()
    {
        if (type == null) {
            IASTNode parent = getPrimaryDeclaration().getParent();
            while (parent != null) {
                if (parent instanceof ICPPASTParameterDeclaration) {
                    type = CPPVisitor.createType((ICPPASTParameterDeclaration) parent, true);
                    break;
                }
                parent = parent.getParent();
            }
        }
        return type;
    }

    @Override
    public boolean isParameterPack()
    {
        return getType() instanceof ICPPParameterPackType;
    }

    @Override
    public boolean isStatic()
    {
        return false;
    }

    @Override
    public boolean isExtern()
    {
        return false;
    }

    @Override
    public boolean isAuto()
    {
        return false;
    }

    @Override
    public boolean isRegister()
    {
        return false;
    }

    @Override
    public IValue getInitialValue()
    {
        return null;
    }

    @Override
    public boolean isExternC()
    {
        return false;
    }

    @Override
    public boolean isMutable()
    {
        return false;
    }
}
