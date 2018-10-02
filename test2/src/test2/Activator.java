package test2;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.service.prefs.PreferencesService;
import org.osgi.service.prefs.Preferences;

public class Activator implements BundleActivator {

	private ServiceTracker<PreferencesService, ?> tracker;
	private PreferencesService service;
	private static final String COLOR = "color"; //NON-NLS-1

	@Override
	public void start(BundleContext context) throws Exception {
		tracker = new ServiceTracker<>(context, PreferencesService.class.getName(), null);
		tracker.open();
		// grab the service
		service = (PreferencesService) tracker.getService();
		Preferences preferences = service.getSystemPreferences();
		preferences.put(COLOR, "lavender");
		System.out.println("My favourite color is test: " + preferences.get(COLOR, ""));
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// clean up
		tracker.close();
		tracker = null;
		service = null;
	}

}
