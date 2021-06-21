package com.softxpert.cars.Classes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softxpert.cars.Classes.REST.Models.Beans.Car;
import com.softxpert.cars.R;
import com.softxpert.cars.databinding.ListItemCarBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsAdapterViewHolder> {
    //The list of Objects that will populate the RecyclerView
    private List<Car> mData;
    private CarsAdapterOnClickHandler mClickHandler;
    Context context;

    @Inject
    public CarsAdapter() {
    }

    @NonNull
    @Override
    public CarsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new CarsAdapterViewHolder(ListItemCarBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarsAdapterViewHolder holder, int position) {
        Car car = mData.get(position);
        Picasso.get().load(car.getImageUrl()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.mBinding.ivCarImage);
        holder.mBinding.tvBrand.setText(car.getBrand());
        holder.mBinding.tvConstructionYear.setText(car.getConstractionYear());
        holder.mBinding.tvUsed.setText(car.isUsed() ? context.getString(R.string.used_car) : context.getString(R.string.new_car));

    }

    @Override
    public int getItemCount() {
        if (null == mData) return 0;
        return mData.size();
    }

    public void setData(List<Car> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public class CarsAdapterViewHolder extends RecyclerView.ViewHolder {
        //get an Instance for viewBinding
        ListItemCarBinding mBinding;

        public CarsAdapterViewHolder(ListItemCarBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            itemView.setOnClickListener(this::onItemClick);
        }

        public void onItemClick(View view) {
            int adapterPosition = getAdapterPosition();
            //Data passed when clicked
            Car car = mData.get(adapterPosition);
            if (mClickHandler != null)
                mClickHandler.onCarItemClick(car);
        }
    }

    //The interface that will be implemented later in parent activity
    public interface CarsAdapterOnClickHandler {
        void onCarItemClick(Car car);
    }

    public void setItemClickListener(CarsAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }
}