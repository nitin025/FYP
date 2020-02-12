package ai.movehack.roadaccidents;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("0Pm03W4GgO3CXaDoBYaMkXibbrI28LTPE31mz2Wk")
                .clientKey("Dhz6gjzLu9hBuqffQRbcwgHGQZIivefNOh5XGVui")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
