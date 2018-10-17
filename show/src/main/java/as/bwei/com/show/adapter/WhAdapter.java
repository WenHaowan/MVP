package as.bwei.com.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import as.bwei.com.show.R;
import as.bwei.com.show.bean.WhBean;

/**
 * Created by HP on 2018/10/15.
 */

public class WhAdapter extends RecyclerView.Adapter<WhAdapter.MyViewHolder>{

    private Context context;
    private List<WhBean.DataBean> list;

    public WhAdapter(Context context, List<WhBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public WhAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_adapter, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(WhAdapter.MyViewHolder holder, int position) {

            Glide.with(context).load(list.get(position).getList().toString()).into(holder.image_view);
            holder.name.setText(list.get(position).getList().get(0).getTitle());
            holder.price.setText("优惠价"+list.get(position).getList().get(0).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image_view;
        private final TextView price;
        private final TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            image_view = (ImageView) itemView.findViewById(R.id.image_view);
            price = (TextView) itemView.findViewById(R.id.price);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
