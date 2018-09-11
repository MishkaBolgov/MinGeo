package mishka.mingeo.view.pumping.pumpinginfo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.view.ZebraItemViewHolder;

public class BoreholeSummaryAdapter extends RecyclerView.Adapter<BoreholeSummaryAdapter.BoreholeSummaryViewHolder> {

    private List<Borehole> boreholes;
    private BoreholeSummaryViewHolder.OnBoreholeClickListener listener;

//    @Inject
    public BoreholeSummaryAdapter() {
        boreholes = new ArrayList<>();
    }

    public void setBoreholes(List<Borehole> boreholes) {
        this.boreholes = boreholes;
        notifyDataSetChanged();
    }

    public void setListener(BoreholeSummaryViewHolder.OnBoreholeClickListener listener) {
        this.listener = listener;
    }

    @Override
    public BoreholeSummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new BoreholeSummaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BoreholeSummaryViewHolder holder, int position) {
        holder.setBorehole(boreholes.get(position));
        holder.setPosition(position+1);
        holder.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return boreholes.size();
    }

    public static class BoreholeSummaryViewHolder extends ZebraItemViewHolder{

        TextView boreholeName;

        private Borehole borehole;

        private int position;

        private OnBoreholeClickListener listener;

        public BoreholeSummaryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(position);
                }
            });
        }

        public void setBorehole(Borehole borehole) {
            this.borehole = borehole;
        }

        public void setPosition(int position) {
            this.position = position;
            boreholeName.setText("Скважина " + position);
            setPositionOddityDependingBackground(position);
        }

        public void setOnClickListener(OnBoreholeClickListener listener){
            this.listener = listener;
        }

        public interface OnBoreholeClickListener{
            void onClick(int position);
        }
    }
}
