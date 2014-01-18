package org.sweetlemonade.eclipse.json;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.sweetlemonade.eclipse.json.preference.JsonPreferences;

/**
 * The activator class controls the plug-in life cycle
 */
public class JsonPlugin extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bimeg.eclipse.json"; //$NON-NLS-1$

	// The shared instance
	private static JsonPlugin plugin;

	private JsonPreferences mPreferences;
	private ColorManager mColorManager;

	/**
	 * The constructor
	 */
	public JsonPlugin()
	{
		//TODO make "remember location and size" for quick outline
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		plugin = this;
		mColorManager = new ColorManager();
		mPreferences = new JsonPreferences();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		mColorManager.dispose();
		mColorManager = null;
		mPreferences = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static JsonPlugin getDefault()
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	public static JsonPreferences getPreferences()
	{
		return plugin.mPreferences;
	}

	public static ColorManager getColorManager()
	{
		return plugin.mColorManager;
	}
}
