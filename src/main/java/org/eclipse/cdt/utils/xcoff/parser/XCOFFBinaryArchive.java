/*******************************************************************************
 * Copyright (c) 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.utils.xcoff.parser;

import org.eclipse.cdt.core.IBinaryParser;
import org.eclipse.cdt.core.IBinaryParser.IBinaryArchive;
import org.eclipse.cdt.core.IBinaryParser.IBinaryFile;
import org.eclipse.cdt.core.IBinaryParser.IBinaryObject;
import org.eclipse.cdt.utils.BinaryFile;
import org.eclipse.cdt.utils.xcoff.AR;
import org.eclipse.core.runtime.IPath;

import java.io.IOException;
import java.util.ArrayList;

/**
 * XCOFF32 binary archive
 *
 * @author vhirsl
 */
public class XCOFFBinaryArchive
        extends BinaryFile
        implements IBinaryArchive
{
    private ArrayList<IBinaryObject> children;

    /**
     * @param parser
     * @param path
     * @throws IOException
     */
    public XCOFFBinaryArchive(IBinaryParser parser, IPath path)
            throws IOException
    {
        super(parser, path, IBinaryFile.ARCHIVE);
        new AR(path.toOSString()).dispose(); // check file type
        children = new ArrayList<IBinaryObject>(5);
    }

    /* (non-Javadoc)
     * @see org.eclipse.cdt.core.IBinaryParser.IBinaryArchive#getObjects()
     */
    @Override
    public IBinaryObject[] getObjects()
    {
        if (hasChanged()) {
            children.clear();
            AR ar = null;
            try {
                ar = new AR(getPath().toOSString());
                AR.MemberHeader[] headers = ar.getHeaders();
                for (int i = 0; i < headers.length; i++) {
                    IBinaryObject bin = new XCOFFBinaryObject(getBinaryParser(), getPath(), headers[i]);
                    children.add(bin);
                }
            }
            catch (IOException e) {
                //e.printStackTrace();
            }
            if (ar != null) {
                ar.dispose();
            }
            children.trimToSize();
        }
        return children.toArray(new IBinaryObject[0]);
    }
}
