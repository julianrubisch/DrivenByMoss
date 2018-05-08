// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017-2018
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.controller.kontrol.usb.mkii;

import de.mossgrabers.framework.controller.DefaultControllerDefinition;
import de.mossgrabers.framework.utils.OperatingSystem;
import de.mossgrabers.framework.utils.Pair;

import java.util.Collections;
import java.util.List;
import java.util.UUID;


/**
 * Definition class for the Native Instruments Kontrol Mk II controller extension.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class Kontrol2ControllerDefinition extends DefaultControllerDefinition
{
    private static final UUID []   EXTENSION_ID   =
    {
        UUID.fromString ("845377d1-89d5-4d54-9df7-b7a2d4c26db2"),
        UUID.fromString ("9adc174c-5957-4a5c-9698-83a91bd2b18b")
    };

    private static final String [] HARDWARE_MODEL =
    {
        "Komplete Kontrol S49 mk II",
        "Komplete Kontrol S61 mk II"
    };

    private static final short     VENDOR_ID      = 0x17cc;
    private static final short []  PRODUCT_ID     =
    {
        0x1610,
        0x1620
    };

    private short                  productID;


    /**
     * Constructor.
     *
     * @param modelIndex The index of the specific model (S25,
     */
    public Kontrol2ControllerDefinition (final int modelIndex)
    {
        super ("", "Jürgen Moßgraber", "1.00", EXTENSION_ID[modelIndex], HARDWARE_MODEL[modelIndex], "Native Instruments", 1, 0);
        this.productID = PRODUCT_ID[modelIndex];
    }


    /** {@inheritDoc} */
    @Override
    public List<Pair<String [], String []>> getMidiDiscoveryPairs (final OperatingSystem os)
    {
        return Collections.singletonList (this.addDeviceDiscoveryPair ("Komplete Kontrol - 2", null));
    }


    /** {@inheritDoc} */
    @Override
    public Pair<Short, Short> claimUSBDevice ()
    {
        return new Pair<> (Short.valueOf (VENDOR_ID), Short.valueOf (this.productID));
    }
}