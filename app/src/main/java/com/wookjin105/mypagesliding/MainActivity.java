package com.wookjin105.mypagesliding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    boolean isPageOpen = false;     //페이지가 현재 열려있는 지를 알기 위한 상태(플래그)

    Animation translateLeftAnim;
    Animation translateRightAnim;
    Button button;
    LinearLayout page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

    /* 슬라이딩으로 보여질 레이아웃 객체 */
        page = (LinearLayout) findViewById(R.id.page);

    /* 애니메이션 객체를 로딩 */
        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animationListener);
        translateRightAnim.setAnimationListener(animationListener);

    }

    /* 애니메이션을 적용하겠다 (현재 상태에 맞춰서) */
    public void onButtonClicked(View v){

        if (isPageOpen){    //열려있는 상태

            page.startAnimation(translateRightAnim);

        }else{  //닫혀있는 상태

            page.setVisibility(View.VISIBLE);
            page.startAnimation(translateLeftAnim);

        }
    }

    /* 애니메이션의 리스너를 정의하는 파트 */
    public class SlidingPageAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        /* 애니메이션이 끝날 때 호출되는 메서드를 재정의 했음 */
        @Override
        public void onAnimationEnd(Animation animation) {
            
            if (isPageOpen){
                page.setVisibility(View.INVISIBLE);
                button.setText("보여주기");
                isPageOpen = false;
            }else{
                button.setText("감추기");
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
