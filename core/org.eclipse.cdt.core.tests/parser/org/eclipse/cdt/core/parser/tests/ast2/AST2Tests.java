/**********************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 * IBM - Initial API and implementation
 **********************************************************************/
package org.eclipse.cdt.core.parser.tests.ast2;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.cdt.core.dom.ast.ASTFactory;
import org.eclipse.cdt.core.dom.ast.IASTBinaryExpression;
import org.eclipse.cdt.core.dom.ast.IASTCompositeTypeSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclarationStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFieldDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTIdExpression;
import org.eclipse.cdt.core.dom.ast.IASTName;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IVariable;

/**
 * Test the new AST.
 * 
 * @author Doug Schaefer
 */
public class AST2Tests extends TestCase {

	public void testBasicFunction() {
		StringBuffer buff = new StringBuffer();
		buff.append("int x;\n");
		buff.append("void f(int y) {\n");
		buff.append("   int z = x + y;\n");
		buff.append("}\n");
		
		IASTTranslationUnit tu = ASTFactory.parseString(buff);
		List declarations = tu.getDeclarations();
		
		// int x
		IASTSimpleDeclaration decl_x = (IASTSimpleDeclaration)declarations.get(0);
		IASTSimpleDeclSpecifier declspec_x = (IASTSimpleDeclSpecifier)decl_x.getDeclSpecifier();
		assertEquals(IASTSimpleDeclSpecifier.t_int, declspec_x.getType());
		IASTDeclarator declor_x = (IASTDeclarator)decl_x.getDeclarators().get(0);
		IASTName name_x = declor_x.getName();
		assertEquals("x", name_x.toString());
		// resolve the binding to get the variable object
		IVariable var_x = (IVariable)name_x.resolveBinding();
		
		// function - void f()
		IASTFunctionDefinition funcdef_f = (IASTFunctionDefinition)declarations.get(1);
		IASTSimpleDeclSpecifier declspec_f = (IASTSimpleDeclSpecifier)funcdef_f.getDeclSpecifier();
		assertEquals(IASTSimpleDeclSpecifier.t_void, declspec_f.getType());
		IASTFunctionDeclarator declor_f = (IASTFunctionDeclarator)funcdef_f.getDeclarator();
		assertEquals("f", declor_f.getName().toString());
		// parameter - int y
		IASTParameterDeclaration decl_y = (IASTParameterDeclaration)declor_f.getParameters().get(0);
		IASTSimpleDeclSpecifier declspec_y = (IASTSimpleDeclSpecifier)decl_y.getDeclSpecifier();
		assertEquals(IASTSimpleDeclSpecifier.t_int, declspec_y.getType());
		IASTDeclarator declor_y = decl_y.getDeclarator();
		assertEquals("y", declor_y.getName().toString());
		// int z
		IASTCompoundStatement body_f = (IASTCompoundStatement)funcdef_f.getBody();
		IASTDeclarationStatement declstmt_z = (IASTDeclarationStatement)body_f.getStatements().get(0);
		IASTSimpleDeclaration decl_z = (IASTSimpleDeclaration)declstmt_z.getDeclaration();
		IASTSimpleDeclSpecifier declspec_z = (IASTSimpleDeclSpecifier)decl_z.getDeclSpecifier();
		assertEquals(IASTSimpleDeclSpecifier.t_int, declspec_z.getType());
		IASTDeclarator declor_z = (IASTDeclarator)decl_z.getDeclarators().get(0);
		assertEquals("z", declor_z.getName().toString());
		// = x + y
		IASTBinaryExpression init_z = (IASTBinaryExpression)declor_z.getInitializer();
		assertEquals(IASTBinaryExpression.op_plus, init_z.getOperator());
		IASTIdExpression ref_x = (IASTIdExpression)init_z.getOperand1();
		IASTName name_ref_x = ref_x.getName();
		assertEquals("x", name_ref_x.toString());
		// make sure the variable referenced is the same one we declared above
		assertEquals(var_x, (IVariable)name_ref_x.resolveBinding());
		
		IASTIdExpression ref_y = (IASTIdExpression)init_z.getOperand2();
		assertEquals("y", ref_y.getName());
	}
	
	public void testSimpleStruct() {
		StringBuffer buff = new StringBuffer();
		buff.append("typedef struct {");
		buff.append("    int x;");
		buff.append("} S;");
		
		IASTTranslationUnit tu = ASTFactory.parseString(buff);
		IASTSimpleDeclaration decl = (IASTSimpleDeclaration)tu.getDeclarations().get(0);
		IASTCompositeTypeSpecifier type = (IASTCompositeTypeSpecifier)decl.getDeclSpecifier();
		// it's a typedef
		assertEquals(IASTSimpleDeclSpecifier.sc_typedef, type.getStorageClass());
		// this an anonymous struct
		assertNull(type.getName());
		// member - x
		IASTSimpleDeclaration decl_x = (IASTSimpleDeclaration)type.getMembers().get(0);
		IASTSimpleDeclSpecifier spec_x = (IASTSimpleDeclSpecifier)decl_x.getDeclSpecifier();
		// it's an int
		assertEquals(IASTSimpleDeclSpecifier.t_int, spec_x.getType());
		IASTFieldDeclarator tor_x = (IASTFieldDeclarator)decl_x.getDeclarators().get(0);
		assertEquals("x", tor_x.getName().toString());
		// declarator S
		IASTDeclarator tor_S = (IASTDeclarator)decl.getDeclarators().get(0);
		assertEquals("S", tor_S.getName().toString());
	}
}
