package in.askdial.test1.RecylerAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.askdial.test1.POJO;
import in.askdial.test1.R;
import in.askdial.test1.SecondActivity;

/**
 * Created by Admin on 19-Nov-16.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<POJO> mData;
    private LayoutInflater mInflater;
    Context context;

    public Adapter(Context context, List<POJO> mData) {
        this.mData = mData;
        this.context= context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        POJO currentPojo= mData.get(position);
        holder.mImageHighlight.setImageResource(currentPojo.getText1());
        holder.mTextWord.setText(currentPojo.getText2());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTextWord;
        private ImageView mImageHighlight, mImageView;
        private int position;
        private POJO currentPOJO;



        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImageHighlight= (ImageView) itemView.findViewById(R.id.departmentimage);
            mTextWord= (TextView) itemView.findViewById(R.id.textMsgs);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            POJO content = mData.get(pos);
            int image = content.getText1();
            String msg=content.getText2();

            Intent i= new Intent(context, SecondActivity.class);
            i.putExtra("image", image );
            i.putExtra("msg", msg);

            context.startActivity(i);



        }
    }
}
