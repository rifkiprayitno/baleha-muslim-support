package id.baleha.promuslim.view.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import id.baleha.promuslim.R;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private ImageView compassImage;
    private float degreeStart = 0f;
    TextView tvDegree;

    float[] mGravity;
    float[] mGeomagnetic;
    float azimut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        compassImage = (ImageView) findViewById(R.id.compass_image);
        tvDegree = (TextView) findViewById(R.id.DegreeTV);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(
                Sensor.TYPE_ORIENTATION), sensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float degree = Math.round(sensorEvent.values[0]);

        tvDegree.setText("Heading: "+Float.toString(degree)+" degrees");

        RotateAnimation ra = new RotateAnimation(
                degreeStart,
                -degree,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        ra.setDuration(120);
        ra.setFillAfter(true);
        compassImage.startAnimation(ra);
        degreeStart = -degree;
    }
/*

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) mGravity = sensorEvent.values;

        if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) mGeomagnetic = sensorEvent.values;

        if(mGravity != null && mGeomagnetic != null){
            float R[] = new float[9];
            float I[] = new float[9];

            if (SensorManager.getRotationMatrix(R,I, mGravity, mGeomagnetic)){
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);

                azimut = orientation[0];
            }
        }
    }
*/

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}