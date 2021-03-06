/*******************************************************************************
 *  Copyright (c) 2006, 2009 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.lrparser.tests;

import junit.framework.TestSuite;

import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.lrparser.gnu.GCCLanguage;
import org.eclipse.cdt.core.dom.lrparser.gnu.GPPLanguage;
import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.core.parser.ParserLanguage;
import org.eclipse.cdt.core.parser.tests.ast2.AST2CSpecTest;
import org.eclipse.cdt.core.parser.ParserException;

@SuppressWarnings({"restriction", "nls"})
public class LRCSpecTests extends AST2CSpecTest {

	public static TestSuite suite() {
    	return suite(LRCSpecTests.class);
    }
	
	public LRCSpecTests() { } 
	public LRCSpecTests(String name) { super(name); }

	
	@Override
	protected void parseCandCPP( String code, boolean checkBindings, int expectedProblemBindings ) throws ParserException {
		parse(code, ParserLanguage.C,   checkBindings, expectedProblemBindings);
		parse(code, ParserLanguage.CPP, checkBindings, expectedProblemBindings);
	}
	
	@Override
	protected IASTTranslationUnit parse( String code, ParserLanguage lang, boolean checkBindings, int expectedProblemBindings ) throws ParserException {
		ILanguage language = lang.isCPP() ? getCPPLanguage() : getCLanguage();
		ParseHelper.Options options = new ParseHelper.Options();
		options.setCheckSyntaxProblems(true);
		options.setCheckPreprocessorProblems(true);
		options.setCheckBindings(checkBindings);
		options.setExpectedProblemBindings(expectedProblemBindings);
		return ParseHelper.parse(code, language, options);
    }
	
	@Override
	protected IASTTranslationUnit parse(String code, ParserLanguage lang, String[] problems) throws ParserException {
		ILanguage language = lang.isCPP() ? getCPPLanguage() : getCLanguage();
		ParseHelper.Options options = new ParseHelper.Options();
		options.setProblems(problems);
		options.setCheckSyntaxProblems(true);
		options.setCheckPreprocessorProblems(true);
		return ParseHelper.parse(code, language, options);
	}
	
	protected ILanguage getCLanguage() {
		return GCCLanguage.getDefault();
	}
	
	protected ILanguage getCPPLanguage() {
		return GPPLanguage.getDefault();
	}

	
	@Override // example code needs to be nested in a function body
	public void test5_1_2_3s15() throws Exception {
		String code =
			"//#include <stdio.h>\n" +
			"int foo() { \n" +
			"int sum;\n" +
			"char *p;\n" +
			"sum = sum * 10 - '0' + (*p++ = getchar());\n" +
			"sum = (((sum * 10) - '0') + ((*(p++)) = (getchar())));\n" +
			"} \n";
		
		parseCandCPP(code, false, 0);
	}	


	
//	@Override
//	public void test6_7_2_1s17() throws Exception { // what the heck is offsetof
//		try {
//			super.test6_7_2_1s17();
//			fail();
//		} catch(AssertionFailedError _) { }
//	}
	
	
	public void testBug276360() throws Exception {
		String code =
			"int foo(int*[]) {}	";
		
		parseCandCPP(code, false, 0);
	}
	
}
