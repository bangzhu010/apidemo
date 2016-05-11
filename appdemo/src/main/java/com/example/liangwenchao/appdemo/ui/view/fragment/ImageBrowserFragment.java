package com.example.liangwenchao.appdemo.ui.view.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.liangwenchao.appdemo.R;
import com.example.liangwenchao.appdemo.ui.base.fragment.BaseFragment;

/**
 * Created by admin on 2016/5/11.
 */
public class ImageBrowserFragment extends BaseFragment {

    public static final String IMAGE_BROWSER_FRAGMENY_TAG = "ImageBrowserFragment";

    private int[] images = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    //默认图片
    private int currentImage = 0;
    //默认透明度
    private float alpha = 1.0f;

    @Override
    public View inflateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_browser, null);
    }

    @Override
    public void initView(final View view) {
        final Button plus = (Button) view.findViewById(R.id.plus);
        final Button minus = (Button) view.findViewById(R.id.minus);
        final Button next = (Button) view.findViewById(R.id.next);

        final ImageView image1 = (ImageView) view.findViewById(R.id.iamge1);
        final ImageView image2 = (ImageView) view.findViewById(R.id.iamge2);
        image1.setImageResource(images[currentImage]);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image1.setImageResource(images[++currentImage % images.length]);
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == plus) {//可以直接写“==”
                    alpha += 0.05;
                }
                if (v == minus) {
                    alpha -= 0.05;
                }
                if (alpha >= 1.0) {
                    alpha = 1.0f;
                }
                if (alpha <= 0) {
                    alpha = 0.0f;
                }
                image1.setAlpha(alpha);

            }
        };
        plus.setOnClickListener(listener);
        minus.setOnClickListener(listener);
        image1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                BitmapDrawable bitmapDrawable = (BitmapDrawable) image1.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                //bitmap图片实际大小与第一个ImageView的缩放比例
                double scale = 1.0 * bitmap.getHeight() / image1.getHeight();
                //获取需要显示的图片的开始点
                int x = (int) (event.getX() * scale);
                int y = (int) (event.getY() * scale);

                if (x + 120 > bitmap.getWidth()) {
                    x = bitmap.getWidth() - 120;
                }
                if (y + 120 > bitmap.getHeight()) {
                    y = bitmap.getHeight()-120;
                }
                image2.setImageBitmap(Bitmap.createBitmap(bitmap,x,y,120,120));
                image2.setAlpha(alpha);

                return false;
            }
        });
    }

    @Override
    public void initData() {

    }
}
