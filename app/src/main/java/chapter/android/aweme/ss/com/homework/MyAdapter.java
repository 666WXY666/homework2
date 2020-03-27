package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import chapter.android.aweme.ss.com.homework.model.Message;
import java.util.List;
import chapter.android.aweme.ss.com.homework.tips.ChatRoomActivity;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

/**
 * 适配器
 * @author 王兴宇
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Message> data;

    public MyAdapter(Context context, List<Message> data) {
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =View.inflate(context,R.layout.im_list_item,null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Message message=data.get(position);
        holder.tvTitle.setText(message.getTitle());
        switch (message.getIcon()){
            case "TYPE_ROBOT":
                holder.ivAvatar.setImageResource(R.drawable.session_robot);
                break;
            case "TYPE_GAME":
                holder.ivAvatar.setImageResource(R.drawable.icon_micro_game_comment);
                break;
            case "TYPE_SYSTEM":
                holder.ivAvatar.setImageResource(R.drawable.session_system_notice);
                break;
            case "TYPE_STRANGER":
                holder.ivAvatar.setImageResource(R.drawable.session_stranger);
                break;
            case "TYPE_USER":
                holder.ivAvatar.setImageResource(R.drawable.icon_girl);
                break;
            default:
                break;
        }
        if (message.isOfficial()){
            holder.robotNotice.setVisibility(View.VISIBLE);
        }
        else {
            holder.robotNotice.setVisibility(View.GONE);
        }
        holder.tvDescription.setText(message.getDescription());
        holder.tvTime.setText(message.getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView ivAvatar;
        private ImageView robotNotice;
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            robotNotice = itemView.findViewById(R.id.robot_notice);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvTime = itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChatRoomActivity.class);
                    //聊天室序号从1开始
                    int id=getLayoutPosition()+1;
                    Toast.makeText(context,"进入第"+id+"个聊天室："+data.get(getLayoutPosition()).getTitle(),Toast.LENGTH_SHORT).show();
                    //传送数据
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",getLayoutPosition());
                    bundle.putString("title",data.get(getLayoutPosition()).getTitle());
                    bundle.putString("description",data.get(getLayoutPosition()).getDescription());
                    bundle.putString("time",data.get(getLayoutPosition()).getTime());
                    bundle.putBoolean("isOfficial",data.get(getLayoutPosition()).isOfficial());
                    intent.putExtra("data",bundle);
                    Log.d("WXY","Item ID:"+getLayoutPosition()+",Send:"+data.get(getLayoutPosition()).toString());
                    context.startActivity(intent);
                }
            });
        }

    }
}
