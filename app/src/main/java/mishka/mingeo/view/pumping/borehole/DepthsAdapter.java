package mishka.mingeo.view.pumping.borehole;

import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mishka.mingeo.R;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;
import mishka.mingeo.view.ZebraItemViewHolder;

public class DepthsAdapter extends RecyclerView.Adapter<DepthsAdapter.DepthViewHolder> {
    private List<BoreholeDepth> boreholeDepths;

    @Inject
    public DepthsAdapter() {
        this.boreholeDepths = new ArrayList<>();
    }

    @Override
    public DepthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.borehole_depth, parent, false);
        return new DepthViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DepthViewHolder holder, int position) {
        holder.setBoreholeDepth(boreholeDepths.get(position));
        holder.setPositionOddityDependingBackground(position);
    }

    @Override
    public int getItemCount() {
        return boreholeDepths.size();
    }

    public void addBoreholeDepth(BoreholeDepth boreholeDepth) {
        boreholeDepths.add(boreholeDepth);
        notifyItemInserted(boreholeDepths.size() - 1);
    }

    public void setBoreholeDepths(List<BoreholeDepth> boreholeDepths) {
        this.boreholeDepths = boreholeDepths;
        notifyDataSetChanged();
    }

    public void updateBoreholeDepths(List<BoreholeDepth> boreholeDepths) {
        this.boreholeDepths = boreholeDepths;
        notifyDataSetChanged();

    }

    static class DepthViewHolder extends ZebraItemViewHolder {

        @BindView(R.id.saved_depth)
        TextView savedDepth;

        @BindView(R.id.saved_depth_date)
        TextView savedDepthDate;

        BoreholeDepth boreholeDepth;

        public DepthViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setBoreholeDepth(BoreholeDepth boreholeDepth) {
            this.boreholeDepth = boreholeDepth;
            savedDepth.setText("" + boreholeDepth.getDepth());
            savedDepthDate.setText("" + boreholeDepth.getMonthDayDate());
        }
    }
}
