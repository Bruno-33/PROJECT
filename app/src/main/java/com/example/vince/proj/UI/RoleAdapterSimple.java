package com.example.vince.proj.UI;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vince.proj.Battle;
import com.example.vince.proj.DB.Role;
import com.example.vince.proj.R;

import java.util.List;

/**
 * Created by vince on 2017/11/13.
 */

public class RoleAdapterSimple extends RecyclerView.Adapter<RoleAdapterSimple.ViewHolder> {

    private Context context; /*运行环境*/
    final private List<Role> roleLists;
    private RoleAdapterHelper roleAdapterHelper = new RoleAdapterHelper();
    private OnItemClickListener myOnItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView portraitField;
        public ViewHolder(View view){
            super(view);
            //nameField = (TextView) view.findViewById(R.id.name_main_list);
            portraitField = (ImageView) view.findViewById(R.id.role_image);
        }
    }
    public RoleAdapterSimple(List<Role> roleLists){
        this.roleLists = roleLists;
    }
    public RoleAdapterSimple(Context context,List<Role> roleLists){
        this.context = context;
        this.roleLists = roleLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.role, parent, false);
        //final ViewHolder holder = new ViewHolder(view);
        roleAdapterHelper.onCreateViewHolder(parent, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        roleAdapterHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        Role role = roleLists.get(position);
//        holder.nameField.setText(character.getName());
        holder.portraitField.setImageResource(role.getImageId());
        if(myOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    myOnItemClickListener.onClick(holder.getAdapterPosition());
                    Animation animation = AnimationUtils.loadAnimation(context,R.anim.beta);
                    holder.itemView.startAnimation(animation);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
                    myOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return roleLists.size();
    }

    public interface  OnItemClickListener{//接口
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.myOnItemClickListener=onItemClickListener;
    }
}