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

import org.eclipse.cdt.core.dom.ast.IASTComment;
import org.eclipse.cdt.core.dom.ast.IASTFileLocation;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.lrparser.gnu.GCCLanguage;
import org.eclipse.cdt.core.dom.lrparser.gnu.GPPLanguage;
import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.core.parser.ParserLanguage;
import org.eclipse.cdt.core.parser.tests.ast2.CommentTests;
import org.eclipse.cdt.core.parser.ParserException;

@SuppressWarnings("restriction")
public class LRCommentTests extends CommentTests {

	public static TestSuite suite() {
        return suite(LRCommentTests.class);
    }
	 
	
    @Override
    @SuppressWarnings("unused")
	protected IASTTranslationUnit parse( String code, ParserLanguage lang, boolean useGNUExtensions, boolean expectNoProblems )  throws ParserException {
    	ILanguage language = lang.isCPP() ? getCPPLanguage() : getCLanguage();
    	return ParseHelper.parse(code, language, expectNoProblems);
    }
    
    
    @Override
    @SuppressWarnings("unused")
	protected IASTTranslationUnit parse(String code, ParserLanguage lang,
			boolean useGNUExtensions, boolean expectNoProblems,
			int limitTrivialInitializers) throws ParserException {
		
    	ILanguage language = lang.isCPP() ? getCPPLanguage() : getCLanguage();
    	ParseHelper.Options options = new ParseHelper.Options();
    	options.setCheckSyntaxProblems(expectNoProblems);
    	options.setCheckPreprocessorProblems(expectNoProblems);
    	options.setLimitTrivialInitializers(limitTrivialInitializers);
    	return ParseHelper.commentParse(code, language);
    }

	protected ILanguage getCLanguage() {
    	return GCCLanguage.getDefault();
    }
	
	protected ILanguage getCPPLanguage() {
		return GPPLanguage.getDefault();
	}
	
	
	@SuppressWarnings("nls")
	public void testBug191266() throws Exception {
		String code =
			"#define MACRO 1000000000000  \n" +
			"int x = MACRO;  \n" +
			"//comment\n";
		
		IASTTranslationUnit tu = parse(code, ParserLanguage.C, false, false, 0);
		
		IASTComment[] comments = tu.getComments();
		assertEquals(1, comments.length);

		IASTFileLocation location = comments[0].getFileLocation();
		assertEquals(code.indexOf("//"), location.getNodeOffset());
		assertEquals("//comment".length(), location.getNodeLength());
	}
}
