/*******************************************************************************
 * Copyright (c) 2007, 2008 Symbian Software Limited and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Bala Torati (Symbian) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.templateengine.process;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.cdt.core.templateengine.TemplateEngineUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory class for creating the Process Runners.
 */
public class ProcessRunnerFactory
{
    private static final String EXTENSION_POINT_PROCESSES = CCorePlugin.PLUGIN_ID + ".templateProcessTypes"; //$NON-NLS-1$
    private static final String ELEM_NAME = "name"; //$NON-NLS-1$
    private static final String ELEM_PROCESS_RUNNER = "processRunner"; //$NON-NLS-1$
    private static ProcessRunnerFactory instance;

    static {
        instance = new ProcessRunnerFactory();
    }

    private Map<String, ProcessRunner> processRunnerMap;

    private ProcessRunnerFactory()
    {
        initializeProcessRunners();
    }

    /**
     * Initialises the process runners.
     *
     */
    private synchronized void initializeProcessRunners()
    {
        processRunnerMap = new HashMap<String, ProcessRunner>();
        IExtensionPoint point = Platform.getExtensionRegistry().getExtensionPoint(EXTENSION_POINT_PROCESSES);
        IExtension[] extensions = point.getExtensions();
        for (int i = 0; i < extensions.length; i++) {
            IExtension extension = extensions[i];
            String prefix = extension.getNamespaceIdentifier() + "."; //$NON-NLS-1$
            IConfigurationElement[] configurationElements = extension.getConfigurationElements();
            for (int j = 0; j < configurationElements.length; j++) {
                IConfigurationElement element = configurationElements[j];
                String processType = element.getAttribute(ELEM_NAME);
                if (processType != null) {
                    try {
                        ProcessRunner runner = (ProcessRunner) element.createExecutableExtension(ELEM_PROCESS_RUNNER);
                        List<ProcessParameter> params = null;
                        IConfigurationElement[] elementChildren = element.getChildren();
                        for (int k = 0; k < elementChildren.length; k++) {
                            if (params == null) {
                                params = new ArrayList<ProcessParameter>();
                            }
                            params.add(new ProcessParameter(elementChildren[k]));
                        }
                        if (params != null) {
                            runner.setProcessParameters(params.toArray(new ProcessParameter[params.size()]));
                        }
                        processRunnerMap.put(prefix + processType, runner);
                    }
                    catch (CoreException e) {
                        TemplateEngineUtil.log(e);
                    }
                }
            }
        }
    }

    /**
     * @return the singleton {@link ProcessRunnerFactory}
     */
    public static ProcessRunnerFactory getDefault()
    {
        return instance;
    }

    /**
     * @param processType
     * @return the ProcessRunner based on the ProcessType.
     */
    public ProcessRunner getProcessRunner(String processType)
    {
        return processRunnerMap.get(processType);
    }
}
