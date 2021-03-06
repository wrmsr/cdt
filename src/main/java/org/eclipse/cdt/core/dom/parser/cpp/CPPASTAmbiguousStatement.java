/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * IBM - Initial API and implementation
 * Markus Schorn (Wind River Systems)
 * Sergey Prigogin (Google)
 * Thomas Corbat (IFS)
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.cpp;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTAttribute;
import org.eclipse.cdt.core.dom.ast.IASTAttributeSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTDeclarationStatement;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IScope;
import org.eclipse.cdt.core.parser.util.ArrayUtil;
import org.eclipse.cdt.core.dom.parser.ASTAmbiguousNode;
import org.eclipse.cdt.core.dom.parser.IASTAmbiguousStatement;
import org.eclipse.cdt.core.dom.parser.cpp.semantics.CPPSemantics;
import org.eclipse.cdt.core.dom.parser.cpp.semantics.CPPVisitor;

public class CPPASTAmbiguousStatement
        extends ASTAmbiguousNode
        implements IASTAmbiguousStatement
{
    private IASTStatement[] stmts = new IASTStatement[2];
    private int stmtsPos = -1;
    private IScope fScope;
    private IASTDeclaration fDeclaration;

    public CPPASTAmbiguousStatement(IASTStatement... statements)
    {
        for (IASTStatement s : statements) {
            addStatement(s);
        }
    }

    @Override
    protected void beforeResolution()
    {
        // Populate containing scope, so that it will not be affected by the alternative
        // branches.
        fScope = CPPVisitor.getContainingScope(this);
        if (fScope instanceof ICPPASTInternalScope) {
            ((ICPPASTInternalScope) fScope).populateCache();
        }
    }

    @Override
    protected void beforeAlternative(IASTNode alternative)
    {
        cleanupScope();
        if (alternative instanceof IASTDeclarationStatement) {
            if (fScope instanceof ICPPASTInternalScope) {
                fDeclaration = ((IASTDeclarationStatement) alternative).getDeclaration();
                CPPSemantics.populateCache((ICPPASTInternalScope) fScope, fDeclaration);
            }
        }
    }

    private void cleanupScope()
    {
        if (fScope instanceof ICPPASTInternalScope && fDeclaration != null) {
            ((ICPPASTInternalScope) fScope).removeNestedFromCache(fDeclaration);
        }
    }

    @Override
    protected void afterResolution(ASTVisitor resolver, IASTNode best)
    {
        beforeAlternative(best);
        fDeclaration = null;
        fScope = null;
    }

    @Override
    public IASTStatement copy()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public IASTStatement copy(CopyStyle style)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addStatement(IASTStatement s)
    {
        assertNotFrozen();
        if (s != null) {
            stmts = ArrayUtil.appendAt(IASTStatement.class, stmts, ++stmtsPos, s);
            s.setParent(this);
            s.setPropertyInParent(STATEMENT);
        }
    }

    @Override
    public IASTStatement[] getStatements()
    {
        stmts = ArrayUtil.trimAt(IASTStatement.class, stmts, stmtsPos);
        return stmts;
    }

    @Override
    public IASTNode[] getNodes()
    {
        return getStatements();
    }

    @Override
    public IASTAttribute[] getAttributes()
    {
        return IASTAttribute.EMPTY_ATTRIBUTE_ARRAY;
    }

    @Override
    public void addAttribute(IASTAttribute attribute)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public IASTAttributeSpecifier[] getAttributeSpecifiers()
    {
        return IASTAttributeSpecifier.EMPTY_ATTRIBUTE_SPECIFIER_ARRAY;
    }

    @Override
    public void addAttributeSpecifier(IASTAttributeSpecifier attributeSpecifier)
    {
        throw new UnsupportedOperationException();
    }
}
