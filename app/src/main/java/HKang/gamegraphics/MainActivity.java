package HKang.gamegraphics;

import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // call superclass method
        super.onCreate(savedInstanceState);

        // create OpenGL ES surface view
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new GLSurfaceRenderer());

        // set content view to OpenGL ES surface view
        setContentView(glSurfaceView);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native void surfaceCreated(AssetManager assetManager);
    public native void surfaceChanged(int width, int height);
    public native void drawFrame(float deltaTime);

    private class GLSurfaceRenderer implements GLSurfaceView.Renderer {

        // last time attribute for calculating delta time
        private long lastTime = System.currentTimeMillis();

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {

            // call native method
            surfaceCreated(getAssets());
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {

            // call native method
            surfaceChanged(width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {

            // calculate delta time
            long currentTime = System.currentTimeMillis();
            float deltaTime = (float) (currentTime - lastTime) / 1000.0f;
            lastTime = currentTime;

            // call native method
            drawFrame(deltaTime);
        }
    }
}
