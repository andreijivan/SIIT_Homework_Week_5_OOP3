package ro.siit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class Phone implements phoneBehaviour {
    private final int batteryLife;
    private String color;
    private String material;

    public Phone(int batteryLife, String color, String material) {
        this.batteryLife = batteryLife;
        this.color = color;
        this.material = material;

    }

    public int getBatteryLife() {
        return batteryLife;
    }
}
