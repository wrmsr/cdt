/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom.lrparser.action.gnu;

import lpg.lpgjavaruntime.IToken;
import org.eclipse.cdt.core.dom.ast.IASTASMDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.INodeFactory;
import org.eclipse.cdt.core.dom.ast.gnu.IGNUASTCompoundStatementExpression;
import org.eclipse.cdt.core.dom.lrparser.action.AbstractParserAction;
import org.eclipse.cdt.core.dom.lrparser.action.ITokenStream;
import org.eclipse.cdt.core.dom.lrparser.action.ScopedStack;
import org.eclipse.cdt.core.dom.lrparser.action.TokenMap;
import org.eclipse.cdt.core.dom.lrparser.gcc.GCCParsersym;

import java.util.List;

public class GNUBuildASTParserAction
        extends AbstractParserAction
{

    private final INodeFactory nodeFactory;

    private final TokenMap tokenMap;

    public GNUBuildASTParserAction(ITokenStream parser, ScopedStack<Object> astStack, INodeFactory nodeFactory)
    {
        super(parser, astStack);

        this.nodeFactory = nodeFactory;
        this.tokenMap = new TokenMap(GCCParsersym.orderedTerminalSymbols, parser.getOrderedTerminalSymbols());
    }

    @Override
    protected IASTName createName(char[] image)
    {
        return nodeFactory.newName(image);
    }

    @Override
    protected boolean isCompletionToken(IToken token)
    {
        return tokenMap.mapKind(token.getKind()) == GCCParsersym.TK_Completion;
    }

    /**
     * Add support for GCC extended ASM declaration syntax.
     *
     *
     * asm_definition -- same as in C++ but its not in C99 spec so we put it here
     *     ::= 'asm' '(' 'stringlit' ')' ';'
     *
     * extended_asm_declaration
     *     ::= 'asm' 'volatile' '(' extended_asm_param_seq ')' ';'
     *       | 'asm' '(' extended_asm_param_seq ')' ';'
     *
     */
    public void consumeDeclarationASM()
    {
        List<IToken> tokens = stream.getRuleTokens();

        int firstToken = 2;
        if (tokenMap.mapKind(tokens.get(1).getKind()) == GCCParsersym.TK_volatile) {
            firstToken = 3;
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (IToken token : tokens.subList(firstToken, tokens.size() - 2)) {
            if (!first) {
                sb.append(' ');
            }
            sb.append(token.toString());
            first = false;
        }

        IASTASMDeclaration asm = nodeFactory.newASMDeclaration(sb.toString());
        setOffsetAndLength(asm);
        astStack.push(asm);
    }

    /**
     * primary_expression
     *     ::= '(' compound_statement ')'
     */
    public void consumeCompoundStatementExpression()
    {
        IASTCompoundStatement compoundStatement = (IASTCompoundStatement) astStack.pop();
        IGNUASTCompoundStatementExpression expr = nodeFactory.newGNUCompoundStatementExpression(compoundStatement);
        setOffsetAndLength(expr);
        astStack.push(expr);
    }
}
