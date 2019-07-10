package cr.codingale.restfulapi;

import android.util.Log;

import com.google.android.things.pio.PeripheralManager;

import nz.geek.android.things.drivers.adc.I2cAdc;

public class PotentiometerModel {
    private static PotentiometerModel instance = null;
    PeripheralManager service;
    // Potentiometer
    private I2cAdc mADC;
    private int mChannel = 0;

    public static PotentiometerModel getInstance() {
        if (instance == null) instance = new PotentiometerModel();
        return instance;
    }

    private PotentiometerModel() {
        service = PeripheralManager.getInstance();
        try {
            I2cAdc.I2cAdcBuilder builder = I2cAdc.builder();
            mADC = builder.address(0).fourSingleEnded().withConversionRate(100).build();
            mADC.startConversions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double getReading() {
        int value = getInstance().mADC.readChannel(getInstance().mChannel);
        double volt = value * 3.3 / 255;
        String voltageString = String.format("%.2f", volt);
        Log.d(getInstance().getClass().getSimpleName(), "getReading: " + voltageString);
        return volt;
    }
}
