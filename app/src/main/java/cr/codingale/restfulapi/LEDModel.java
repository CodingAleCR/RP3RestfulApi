package cr.codingale.restfulapi;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

public class LEDModel {
    private static LEDModel instance = null;
    PeripheralManager service;
    private Gpio mLedGpio;
    private final String PIN_LED = "BCM18";

    public static LEDModel getInstance() {
        if (instance == null) instance = new LEDModel();

        return instance;
    }

    private LEDModel() {
        service = PeripheralManager.getInstance();
        try {
            mLedGpio = service.openGpio(PIN_LED);
            mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Boolean setState(boolean state) {
        try {
            getInstance().mLedGpio.setValue(state);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static boolean getState() {
        boolean value = false;
        try {
            value = getInstance().mLedGpio.getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
