package com.example.attendenceapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.attendenceapp.R;

import java.util.List;

public abstract class RecyclerBuilder<T> extends RecyclerView.Adapter<RecyclerBuilder.BaseViewHolder> {
    private Context context;
    private List<T> list;
    public abstract void  OnBind(BaseViewHolder holder,T model,int position,View view);
    public abstract int getLayoutId();
    @NonNull
    @Override
    public RecyclerBuilder.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewDataBinding viewBinding = DataBindingUtil.inflate(LayoutInflater.from(context),getLayoutId(),parent,false);
        return new BaseViewHolder(viewBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBuilder.BaseViewHolder holder, int position) {
            OnBind(holder,list.get(position),position,holder.itemView);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() :0;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        public void dataBind(int id,T model){
            ViewDataBinding binding = DataBindingUtil.bind(itemView);
            binding.setVariable(id,model);
            binding.executePendingBindings();
        }
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }
}
