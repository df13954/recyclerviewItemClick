package home.ldrong.com.recycleviewitemclick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.ldrong.com.recycleviewitemclick.R;
import home.ldrong.com.recycleviewitemclick.entity.UserCard;

/**
 * Created by Administrator on 2016/7/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MoreClickHolder> {
    private static final String TAG = "MyAdapter";
    private final LayoutInflater inflater;
    private Context mContext;
    private List<UserCard> mList;


    public MyAdapter(Context mContext, List<UserCard> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public MyAdapter.MoreClickHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_recycleview, parent, false);

        return new MoreClickHolder(itemView, new IMoreClickListener() {
            @Override
            public void onButtonClick() {
                //按钮的点击事件
                Log.e(TAG, "按钮的点击事件");
                showToastMessage("按钮的点击事件");
            }

            @Override
            public void onImageClick() {
                //图片的点击事件
                Log.e(TAG, "图片的点击事件");
                showToastMessage("图片的点击事件");
            }
        });
    }

    @Override
    public void onBindViewHolder(MoreClickHolder holder, int position) {
        //给每个item填充数据
        final UserCard entity = mList.get(position);
        holder.bind(entity,position);
        holder.llRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //整个item 的点击事件
                showToastMessage(entity.getName() + ":" + entity.getSex());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MoreClickHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_text)
        TextView tvText;
        @BindView(R.id.btn)
        Button btn;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.ll_rootview)
        LinearLayout llRootView;
        IMoreClickListener mMoreClickListener;

        public MoreClickHolder(View itemView, IMoreClickListener moreClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mMoreClickListener = moreClickListener;
            btn.setOnClickListener(this);
            ivIcon.setOnClickListener(this);
        }

        public void bind(UserCard userCard, int position) {
            btn.setText(userCard.getName()+ "");    //这里有个技巧，setText里面 变量 + “”，就算变量空指针，程序不会闪退
            tvText.setText("" + userCard.getSex());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn://按钮的回调
                    mMoreClickListener.onButtonClick();
                    break;
                case R.id.iv_icon://图片回调
                    mMoreClickListener.onImageClick();
                    break;
            }
        }


    }

    private void showToastMessage(String msg) {
        Toast.makeText(mContext, "" + msg, Toast.LENGTH_SHORT).show();
    }


    private interface IMoreClickListener {
        public void onButtonClick();

        public void onImageClick();
    }

}
