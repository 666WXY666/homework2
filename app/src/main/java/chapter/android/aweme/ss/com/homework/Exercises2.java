package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 * @author 王兴宇
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_count);
        super.onCreate(savedInstanceState);
        TextView tvCeter = findViewById(R.id.tv_center);
        RelativeLayout rl = findViewById(R.id.RL);
        //如果本身的主布局不算的话就要-1
        tvCeter.setText(String.valueOf(getAllChildViewCount(rl)-1));
        //如果算本身的主布局的话：
        //tvCeter.setText(String.valueOf(getAllChildViewCount(rl)));
    }

    public int getAllChildViewCount(View view) {
        int count = 0;

        if (null == view) {
            return 0;
        }

        if (view instanceof ViewGroup) {
            count++;
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View next = ((ViewGroup) view).getChildAt(i);
                if (next instanceof ViewGroup) {
                    count += getAllChildViewCount(next);
                }
                else {
                    count++;
                }
            }
        }

        return count;
    }
}
