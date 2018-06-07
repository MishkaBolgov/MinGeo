package mishka.mingeo.view.pumping.borehole;

import android.arch.persistence.room.Insert;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mishka.mingeo.R;
import mishka.mingeo.data.datamanager.DataManager;
import mishka.mingeo.data.model.Borehole;
import mishka.mingeo.data.model.BoreholeDepth;

public class DepthsAdapter extends RecyclerView.Adapter<DepthsAdapter.DepthViewHolder> {
    private List<BoreholeDepth> boreholeDepths;
    private BoreholeFragment boreholeFragment;

    @Inject
    public DepthsAdapter() {
        this.boreholeDepths = new ArrayList<>();
    }

    public void setBoreholeFragment(BoreholeFragment boreholeFragment) {
        this.boreholeFragment = boreholeFragment;
    }

    @Override
    public DepthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.borehole_depth, parent, false);
        return new DepthViewHolder(view, boreholeFragment);
    }

    @Override
    public void onBindViewHolder(DepthViewHolder holder, int position) {
        holder.setBoreholeDepth(boreholeDepths.get(position));
    }

    @Override
    public int getItemCount() {
        return boreholeDepths.size();
    }

    public void addBoreholeDepth(BoreholeDepth boreholeDepth) {
        boreholeDepths.add(boreholeDepth);
        notifyDataSetChanged();
    }

    static class DepthViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.depth)
        EditText depth;

        BoreholeDepth boreholeDepth;

        BoreholeFragment fragment;


        public DepthViewHolder(View itemView, BoreholeFragment fragment) {
            super(itemView);
            this.fragment = fragment;
            ButterKnife.bind(this, itemView);
        }

        public void setBoreholeDepth(BoreholeDepth boreholeDepth) {
            this.boreholeDepth = boreholeDepth;
        }

        @OnClick(R.id.save_depth)
        void onSaveDepthClick() {
            boreholeDepth.setDepth(Integer.parseInt(depth.getText().toString()));
            fragment.onBoreholeDepthUpdate(boreholeDepth);
        }
    }
}
