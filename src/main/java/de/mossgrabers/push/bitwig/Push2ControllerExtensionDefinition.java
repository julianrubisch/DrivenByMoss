// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2018
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.push.bitwig;

import de.mossgrabers.framework.bitwig.BitwigSetupFactory;
import de.mossgrabers.framework.bitwig.configuration.SettingsUI;
import de.mossgrabers.framework.bitwig.daw.HostProxy;
import de.mossgrabers.framework.controller.IControllerSetup;
import de.mossgrabers.push.PushControllerSetup;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.UsbDeviceInfo;
import com.bitwig.extension.controller.api.ControllerHost;

import java.util.List;
import java.util.UUID;


/**
 * Definition class for the Push 2 controller extension.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class Push2ControllerExtensionDefinition extends PushControllerExtensionDefinition
{
    private static final UUID  EXTENSION_ID = UUID.fromString ("15176AA0-C476-11E6-9598-0800200C9A66");

    /** Push 2 USB Vendor ID. */
    private static final short VENDOR_ID    = 0x2982;
    /** Push 2 USB Product ID. */
    private static final short PRODUCT_ID   = 0x1967;


    /** {@inheritDoc} */
    @Override
    public String getHardwareModel ()
    {
        return "Push 2";
    }


    /** {@inheritDoc} */
    @Override
    public UUID getId ()
    {
        return EXTENSION_ID;
    }


    /** {@inheritDoc} */
    @Override
    public void listAutoDetectionMidiPortNames (final AutoDetectionMidiPortNamesList list, final PlatformType platformType)
    {
        switch (platformType)
        {
            case WINDOWS:
                this.addDeviceDiscoveryPair ("Ableton Push 2", list);
                break;

            case LINUX:
                this.addDeviceDiscoveryPair ("Ableton Push 2 MIDI 1", list);
                break;

            case MAC:
                this.addDeviceDiscoveryPair ("Ableton Push 2 Live Port", list);
                break;
        }
    }


    /** {@inheritDoc} */
    @Override
    public void listUsbEndpoints (final List<UsbDeviceInfo> endpoints)
    {
        endpoints.add (new UsbDeviceInfo (VENDOR_ID, PRODUCT_ID));
    }


    /** {@inheritDoc} */
    @Override
    protected IControllerSetup getControllerSetup (final ControllerHost host)
    {
        return new PushControllerSetup (new HostProxy (host), new BitwigSetupFactory (host), new SettingsUI (host.getPreferences ()), true);
    }


    /** {@inheritDoc} */
    @Override
    public boolean isUsingBetaAPI ()
    {
        return true;
    }
}