package home.ldrong.com.recycleviewitemclick;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.ldrong.com.recycleviewitemclick.adapter.MyAdapter;
import home.ldrong.com.recycleviewitemclick.entity.UserCard;

/**
* recycleview item点击事件，响应不同控件的点击
* @author ldr
* created at 2016/7/15 21:48
*/
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MainActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;

        initView();

    }

    private void initView() {
        UserCard userCard1 = new UserCard();
        userCard1.setName("小明");
        userCard1.setSex("男");
        UserCard userCard2 = new UserCard();
        userCard2.setName("张三");
        userCard2.setSex("女");

        //一个空的ben
        UserCard userCard3 = new UserCard();

        List<UserCard> userCardList = new ArrayList<>();
        userCardList.add(userCard1);
        userCardList.add(userCard2);
        userCardList.add(userCard3);

        //初始化
        MyAdapter adapter = new MyAdapter(mContext, userCardList);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //添加分割线，颜色，和高度
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(Color.parseColor("#8c8c8c"))
                .size(1)
                .build()
        );

        recyclerView.setAdapter(adapter);

    }
}
