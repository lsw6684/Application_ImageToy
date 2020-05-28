package com.example.hw9;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    ImageButton zoom, reduce, rotate, colour;
    MyGraphicView graphicView;
    static float scaleX = 1, scaleY = 1;
    static float angle = 0;
    static float satur = 1;
    static float color = 1;
    private Toast t1, t2, t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.lay);
        graphicView = (MyGraphicView) new MyGraphicView(this);
        pictureLayout.addView(graphicView);
        clickIcons();
        t1 = Toast.makeText(this, "피카아!!!!", Toast.LENGTH_SHORT);
        t2 = Toast.makeText(this, "ㅍㅋ..", Toast.LENGTH_SHORT);
        t3 = Toast.makeText(this, "우웨엑!!", Toast.LENGTH_SHORT);
    }
    private void clickIcons() {
        zoom = (ImageButton) findViewById(R.id.zoom);
        zoom.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                graphicView.invalidate();
                t2.cancel();
                t3.cancel();
                t1.show();
            }
        });
        reduce = (ImageButton) findViewById(R.id.reduce);
        reduce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                graphicView.invalidate();
                t1.cancel();
                t3.cancel();
                t2.show();
            }
        });
        rotate = (ImageButton) findViewById(R.id.rotate);
        rotate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                angle = angle + 20;
                graphicView.invalidate();
                t2.cancel();
                t1.cancel();
                t3.show();
            }
        });
        colour = (ImageButton) findViewById(R.id.colour);
        colour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (satur == 0)
                    satur = 1;
                else
                    satur = 0;
                graphicView.invalidate();
            }
        });
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.rotate(angle, cenX, cenY);
            Paint paint = new Paint();
            float[] array = {color, 0, 0, 0, 0, 0, color, 0, 0, 0, 0, 0,
                    color, 0, 0, 0, 0, 0, 1, 0};
            ColorMatrix cm = new ColorMatrix(array);
            if (satur == 0)
                cm.setSaturation(satur);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.pikachu);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, paint);
            picture.recycle();

        }
    }

}
